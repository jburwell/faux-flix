package net.cockamamy.fauxflix.simulator;

import static java.util.Collections.*;
import static java.lang.String.*;

import java.text.*;
import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;
import net.cockamamy.fauxflix.service.rental.*;
import net.cockamamy.fauxflix.util.uow.*;

/**
 * 
 * Base {@link Command} implementation
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
abstract class AbstractSimulatorCommand implements SimulatorCommand {

	private final Date myOccurred;
	private final Customer myCustomer;
	private final MediaType myMediaType;
	private final Movie myMovie;
	private final RentalService myRentalService;

	/**
	 * 
	 * @param anOccurred
	 *            The date on which the command will occur
	 * @param aCustomer
	 *            The customer for whom the command will be executed
	 * @param aMovie
	 *            The movie for which the command will be executed
	 * @param aMediaType
	 *            The media type of the movie for which the command will be
	 *            executed
	 * 
	 * @since 1.0.0
	 * 
	 */
	AbstractSimulatorCommand(Date anOccurred, Customer aCustomer, Movie aMovie,
			MediaType aMediaType, RentalService aRentalService) {

		super();

		assert anOccurred != null : format(
				"%1$s requires a non-null occurred date.", this.getClass()
						.getName());
		assert aCustomer != null : format("%1$s requires a non-null customer.",
				this.getClass().getName());
		assert aMovie != null : format("%1$s requires a non-null movie.", this
				.getClass().getName());
		assert aMediaType != null : format(
				"%1$s requires a non-null media type.", this.getClass()
						.getName());

		this.myOccurred = anOccurred;
		this.myCustomer = aCustomer;
		this.myMovie = aMovie;
		this.myMediaType = aMediaType;
		this.myRentalService = aRentalService;

	}

	// BEGIN: Command implementation
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#getOccurred()
	 */
	public final Date getOccurred() {

		return this.myOccurred;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#getCustomer()
	 */
	public final Customer getCustomer() {

		return this.myCustomer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#getMediaType()
	 */
	public final MediaType getMediaType() {

		return this.myMediaType;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#getMovie()
	 */
	public final Movie getMovie() {

		return this.myMovie;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#getAdditionalCommands()
	 */
	public Set<SimulatorCommand> getAdditionalCommands() {

		return emptySet();

	}
	// END: Command implementation

	protected final RentalService getRentalService() {
		
		return this.myRentalService;
		
	}

	// BEGIN: Object implementation
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object thatObject) {

		boolean aReturnValue = false;

		if (thatObject != null
				&& this.getClass().equals(thatObject.getClass()) == true) {

			AbstractSimulatorCommand thatCommand = (AbstractSimulatorCommand) thatObject;

			if (this.myCustomer.equals(thatCommand.getCustomer()) == true
					&& this.myMediaType.equals(thatCommand.getMediaType()) == true
					&& this.myMovie.equals(thatCommand.getMovie()) == true
					&& this.myOccurred.equals(thatCommand.myOccurred) == true
					&& this.getType().equals(thatCommand.getType()) == true) {

				aReturnValue = true;

			}

		}

		return aReturnValue;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int aHashCode = 37;

		aHashCode = (17 * aHashCode) + this.getType().hashCode();
		aHashCode = (17 * aHashCode) + this.myCustomer.hashCode();
		aHashCode = (17 * aHashCode) + this.myMediaType.hashCode();
		aHashCode = (17 * aHashCode) + this.myMovie.hashCode();
		aHashCode = (17 * aHashCode) + this.myOccurred.hashCode();

		return aHashCode;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return format(
				"Simulator Command: (type: %1$s, occurred: %2$s, customer %3$s, movie: %4$s, mediaType: %5$s)",
				this.getType(), DateFormat.getDateInstance(DateFormat.SHORT)
						.format(this.myOccurred), this.myCustomer,
				this.myMovie, this.myMediaType);

	}
	// END: Object implementation

}
