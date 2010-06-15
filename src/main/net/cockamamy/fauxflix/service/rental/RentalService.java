package net.cockamamy.fauxflix.service.rental;

import java.util.*;

import net.cockamamy.fauxflix.service.*;
import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;

/**
 * 
 * Manages the lifecycle of movie rentals by Faux Flix rental store customers
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface RentalService extends Service<RentalChangedEvent> {

	/**
	 * 
	 * Finds a rental for the passed customer, <code>aCustomer</code>, movie,
	 * <code>aMovie</code>, and media type, <code>aMediaType</code>.
	 * 
	 * @param aCustomer
	 *            The customer for whom to find the rental
	 * @param aMovie
	 *            The movie for which to find the rental
	 * @param aMediaType
	 *            The media of the movie for which to find the rental
	 * 
	 * @return The rental matching the passed customer, <code>aCustomer</code>,
	 *         movie, <code>aMovie</code>, and media type,
	 *         <code>aMediaType</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Rental findRental(Customer aCustomer, Movie aMovie, MediaType aMediaType);

	/**
	 * 
	 * Finds all rentals for the passed customer, <code>aCustomer</code>, for
	 * all media types.
	 * 
	 * @param aCustomer
	 *            The customer for whom to find all rentals
	 * 
	 * @return All rentals for the passed customer, <code>aCustomer</code>, for
	 *         all media types.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Set<Rental> findRentals(Customer aCustomer);

	/**
	 *
	 * Finds all rentals in the system.
	 *
	 * @return All rentals in the system.
	 *
	 * @since 1.0.0
	 *
	 */
	Set<Rental> findAllRentals();

	/**
	 * 
	 * Rent the passed movie, <code>aMovie</code>, and media type,
	 * <code>aMediaType</code>, for the passed customer, <code>aCustomer</code>,
	 * on the passed request date, <code>aRequestDate</code>.
	 * 
	 * @param aCustomer
	 *            The customer for whom to rent the movie
	 * @param aMovie
	 *            The movie to rent
	 * @param aMediaType
	 *            The media type of the movie to rent
	 * @param aRequestDate
	 *            The date the rental was requested.
	 * 
	 * @return The rental of the passed movie, <code>aMovie</code>, and media
	 *         type, <code>aMediaType</code>, for the passed customer,
	 *         <code>aCustomer</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Rental rentMovie(Customer aCustomer, Movie aMovie, MediaType aMediaType,
			Date aRequestDate);

	/**
	 * 
	 * Returns the passed rental, <code>aRental</code>, to the Faux Flix rental
	 * store on the passed date, <code>aReturnDate</code>.
	 * 
	 * @param aRental
	 *            The rental to return
	 * @param aReturnDate
	 *            The date the return occurred
	 * 
	 * @since 1.0.0
	 * 
	 */
	void returnMovie(Rental aRental, Date aReturnDate);

	/**
	 * 
	 * Send a the next rental of the passed movie, aMovie, in the delivery
	 * queue.
	 * 
	 * @param aMovie
	 *            The movie to send
	 * 
	 * @return The rental of the passed movie, <code>aMovie</code>, sent.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Rental sendMovie(Movie aMovie);

	/**
	 * 
	 * Create queues managed by this service for the passed customer,
	 * <code>aCustomer</code>.
	 * 
	 * @param aCustomer
	 *            The customer for which to create queues
	 * 
	 * @since 1.0.0
	 * 
	 */
	void createCustomerQueues(Customer aCustomer);

	/**
	 * 
	 * Create queues managed by this service for the passed movie,
	 * <code>aMovie</code>.
	 * 
	 * @param aMovie
	 *            The movie for which to create queues
	 * 
	 * @since 1.0.0
	 * 
	 */
	void createMovieQueues(Movie aMovie);

}
