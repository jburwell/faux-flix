package net.cockamamy.fauxflix.service.rental;

import static java.lang.String.*;
import static java.util.Collections.*;
import static net.cockamamy.fauxflix.service.customer.MembershipLevel.*;
import static net.cockamamy.fauxflix.service.rental.RentalChangedEvent.ChangeReason.*;

import java.util.*;

import net.cockamamy.fauxflix.service.*;
import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;
import net.cockamamy.fauxflix.service.rental.RentalChangedEvent.*;
import net.cockamamy.fauxflix.util.*;

/**
 * 
 * Default rental service implementation that manages the movie delivery queues
 * and customer rental and request queues.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class RentalServiceImpl extends AbstractService<RentalChangedEvent>
		implements RentalService {

	private static Map<MembershipLevel, PermitRentalStrategy> PERMIT_RENTAL_STRATEGIES;
	static {

		Map<MembershipLevel, PermitRentalStrategy> theStrategies = new HashMap<MembershipLevel, PermitRentalStrategy>();

		theStrategies.put(STANDARD, new StandardPermitRentalStrategy());
		theStrategies.put(EXECUTIVE, new ExecutivePermitRentalStrategy());

		PERMIT_RENTAL_STRATEGIES = unmodifiableMap(theStrategies);

	}

	private final InventoryService myInventoryService;

	private final Map<Customer, Map<MediaType, Map<Movie, Rental>>> myRentals;

	private final Map<Customer, Queue<Rental>> myRequestQueues;

	private final Map<Movie, Queue<Rental>> myDeliveryQueues;

	/**
	 * 
	 * Default constructor that initializes all queues managed by this service
	 * 
	 * @since 1.0.0
	 * 
	 */
	RentalServiceImpl(InventoryService anInventoryService) {

		super();

		assert anInventoryService != null : format(
				"%1$s(InventoryService) requires a non-null %2$s reference.",
				this.getClass().getName(), InventoryService.class.getName());

		this.myInventoryService = anInventoryService;

		this.myRequestQueues = new HashMap<Customer, Queue<Rental>>();
		this.myDeliveryQueues = new HashMap<Movie, Queue<Rental>>();
		this.myRentals = new HashMap<Customer, Map<MediaType, Map<Movie, Rental>>>();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.rental.RentalService#findRental(net.cockamamy
	 * .fauxflix.service.customer.Customer,
	 * net.cockamamy.fauxflix.service.inventory.Movie,
	 * net.cockamamy.fauxflix.service.inventory.MediaType)
	 */
	public Rental findRental(Customer aCustomer, Movie aMovie,
			MediaType aMediaType) {

		assert aCustomer != null : format("findRental(Movie, MediaType) requires a non-null Customer.");
		assert aMovie != null : format("findRental(Movie, MediaType) requires a non-null Movie.");
		assert aMediaType != null : format("findRental(Movie, MediaType) requires a non-null MediaType.");

		return this.myRentals.get(aCustomer).get(aMediaType).get(aMovie);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.rental.RentalService#findRentals(net.cockamamy
	 * .fauxflix.service.customer.Customer)
	 */
	public Set<Rental> findRentals(Customer aCustomer) {

		assert aCustomer != null : "findRentals(Cusomter) requires a non-null Customer";

		Map<MediaType, Map<Movie, Rental>> theRentalMap = this.myRentals
				.get(aCustomer);
		Set<Rental> theRentals = new HashSet<Rental>();

		// Unwind the indexed rentals into a flattened list for the passed
		// customers ...
		for (Map.Entry<MediaType, Map<Movie, Rental>> anEntry : theRentalMap
				.entrySet()) {

			for (Map.Entry<Movie, Rental> aRentalEntry : anEntry.getValue()
					.entrySet()) {

				theRentals.add(aRentalEntry.getValue());

			}

		}

		return unmodifiableSet(theRentals);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.rental.RentalService#findAllRentals()
	 */
	public Set<Rental> findAllRentals() {

		Set<Rental> theRentals = new HashSet<Rental>();

		for (Map.Entry<Customer, Map<MediaType, Map<Movie, Rental>>> aCustomerEntry : this.myRentals
				.entrySet()) {

			for (Map.Entry<MediaType, Map<Movie, Rental>> aMediaTypeEntry : aCustomerEntry
					.getValue().entrySet()) {

				for (Map.Entry<Movie, Rental> aRentalEntry : aMediaTypeEntry
						.getValue().entrySet()) {

					theRentals.add(aRentalEntry.getValue());

				}

			}

		}

		return theRentals;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.rental.RentalService#rentMovie(net.cockamamy
	 * .fauxflix.service.customer.Customer,
	 * net.cockamamy.fauxflix.service.inventory.Movie,
	 * net.cockamamy.fauxflix.service.inventory.MediaType, java.util.Date)
	 */
	public Rental rentMovie(Customer aCustomer, Movie aMovie,
			MediaType aMediaType, Date aRequestDate) {
		
		Rental aRental = new RentalImpl(aCustomer, aMovie, aMediaType,
				aRequestDate);

		this.notifyListeners(new RentalChangedEvent(aRental, REQUESTED));

		ChangeReason aChangeReason = QUEUED_FOR_DELIVERY;

		PermitRentalStrategy aPermitRentalStrategy = PERMIT_RENTAL_STRATEGIES
				.get(aCustomer.getMembershipLevel());

		if (aPermitRentalStrategy.canRent(aMediaType, this.myRentals
				.get(aCustomer)) == true) {

			this.myRentals.get(aCustomer).get(aMediaType).put(aMovie, aRental);

			this.myDeliveryQueues.get(aMovie).offer(aRental);

			boolean anAllocatedFlag = this.myInventoryService.allocateStock(
					aMovie, aMediaType);
			if (anAllocatedFlag == true) {

				this.sendMovie(aMovie);

			} else {

				aChangeReason = QUEUED_OUT_OF_STOCK;

			}

		} else {

			this.myRequestQueues.get(aCustomer).offer(aRental);
			aChangeReason = QUEUED_LIMITED_EXCEEDED;

		}

		this.notifyListeners(new RentalChangedEvent(aRental, aChangeReason));

		return aRental;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.rental.RentalService#returnMovie(net.cockamamy
	 * .fauxflix.service.rental.Rental, java.util.Date)
	 */
	public void returnMovie(Rental aRental, Date aReturnDate) {

		assert aRental != null;
		assert aReturnDate != null;

		this.myInventoryService.returnStock(aRental.getMovie(), aRental
				.getMediaType());

		this.myRentals.get(aRental.getCustomer()).get(aRental.getMediaType())
				.remove(aRental.getMovie());

		this.notifyListeners(new RentalChangedEvent(aRental, RETURNED));

	}

	/**
	 * 
	 * Defines a strategy to determine whether or not a customer can rent a
	 * movie based on their current rentals and {@link MembershipLevel}.
	 * 
	 * @author jburwell
	 * 
	 * @since 1.0.0
	 * 
	 */
	private static interface PermitRentalStrategy {

		/**
		 * 
		 * Determines whether or not a customer can rent a movie based on their
		 * current rentals, <code>theCurrentRentals</code>.
		 * 
		 * @param aMediaType
		 *            The media type to rent
		 * @param theCurrentRentals
		 *            The current rentals
		 * 
		 * @return <strong>true</strong>: The customer can rent another movie.
		 *         <strong>false</strong>: The customer can <strong>not</strong>
		 *         rent another movie.
		 * 
		 * @since 1.0.0
		 * 
		 */
		public boolean canRent(MediaType aMediaType,
				Map<MediaType, Map<Movie, Rental>> theCurrentRentals);

	}

	/**
	 * 
	 * The permit rental strategy for the {@link MembershipLevel#STANDARD}
	 * {@link MembershipLevel}.
	 * 
	 * @author jburwell
	 * 
	 * @since 1.0.0
	 * 
	 */
	private static class StandardPermitRentalStrategy implements
			PermitRentalStrategy {

		/*
		 * (non-Javadoc)
		 * 
		 * @seenet.cockamamy.fauxflix.service.rental.RentalServiceImpl.
		 * PermitRentalStrategy#canRent(java.util.Map)
		 */
		public boolean canRent(MediaType aMediaType,
				Map<MediaType, Map<Movie, Rental>> theCurrentRentals) {

			assert theCurrentRentals != null : format(
					"%1$s.canRent requires a non-null theCurrentRentals value.",
					this.getClass().getName());

			boolean aReturnValue = false;

			int theTotalRentals = 0;
			Set<Map.Entry<MediaType, Map<Movie, Rental>>> theEntries = theCurrentRentals
					.entrySet();
			for (Map.Entry<MediaType, Map<Movie, Rental>> anEntry : theEntries) {

				theTotalRentals += anEntry.getValue().size();

			}

			if (theTotalRentals <= 2) {

				aReturnValue = true;

			}

			return aReturnValue;

		}

	}

	private static class ExecutivePermitRentalStrategy implements
			PermitRentalStrategy {

		public boolean canRent(MediaType aMediaType,
				Map<MediaType, Map<Movie, Rental>> theCurrentRentals) {

			boolean aReturnValue = false;

			Map<Movie, Rental> theRentals = theCurrentRentals.get(aMediaType);

			switch (aMediaType) {

			case DVD:

				if (theRentals.size() <= 3) {

					aReturnValue = true;

				}
				break;

			case BLURAY:

				if (theRentals.size() <= 2) {

					aReturnValue = true;

				}
				break;

			default:

				throw new IllegalStateException("Unsupported media type.");

			}

			return aReturnValue;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.rental.RentalService#sendMovie(net.cockamamy
	 * .fauxflix.service.inventory.Movie)
	 */
	public Rental sendMovie(Movie aMovie) {
		
		Rental aRental = this.myDeliveryQueues.get(aMovie).poll();

		if (aRental != null) {

			aRental.setSentDate(DateFactory.getInstance().getToday());
			this.notifyListeners(new RentalChangedEvent(aRental, SENT));

		}

		return aRental;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.rental.RentalService#createCustomerQueues
	 * (net.cockamamy.fauxflix.service.customer.Customer)
	 */
	public void createCustomerQueues(Customer aCustomer) {

		assert this.myRentals.containsKey(aCustomer) == false;
		assert this.myRequestQueues.containsKey(aCustomer) == false;

		this.myRentals.put(aCustomer,
				new HashMap<MediaType, Map<Movie, Rental>>());

		for (MediaType aMediaType : MediaType.values()) {

			this.myRentals.get(aCustomer).put(aMediaType,
					new HashMap<Movie, Rental>());

		}

		this.myRequestQueues.put(aCustomer, new LinkedList<Rental>());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.rental.RentalService#createMovieQueues
	 * (net.cockamamy.fauxflix.service.inventory.Movie)
	 */
	public void createMovieQueues(Movie aMovie) {

		assert aMovie != null : "createMovieQueues requires a non-null Movie instance.";
		assert this.myDeliveryQueues.containsKey(aMovie) == false : format(
				"A delivery queue already exists for %1$s", aMovie.getTitle());

		this.myDeliveryQueues.put(aMovie, new LinkedList<Rental>());

	}

}
