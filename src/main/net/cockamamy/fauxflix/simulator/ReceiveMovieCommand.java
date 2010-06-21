package net.cockamamy.fauxflix.simulator;

import static java.lang.String.*;
import static java.util.Collections.*;
import static net.cockamamy.fauxflix.simulator.SimulatorCommandType.*;

import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;
import net.cockamamy.fauxflix.service.rental.*;
import net.cockamamy.fauxflix.util.uow.*;

/**
 * 
 * Performs the actions to represent the receipt of a movie back from a
 * customer.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class ReceiveMovieCommand extends AbstractSimulatorCommand {

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
	public ReceiveMovieCommand(Date anOccurred, Customer aCustomer,
			Movie aMovie, MediaType aMediaType, RentalService aRentalService) {

		super(anOccurred, aCustomer, aMovie, aMediaType, aRentalService);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#execute()
	 */
	public List<Message> execute() {

		Rental aRental = this.getRentalService().findRental(this.getCustomer(),
				this.getMovie(), this.getMediaType());

		assert aRental != null : format(
				"No rental found associated with %1$s for %2$s (%3$s)", this
						.getCustomer(), this.getMovie(), this.getMediaType());

		this.getRentalService().returnMovie(aRental, this.getOccurred());

		return emptyList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#getType()
	 */
	public SimulatorCommandType getType() {

		return RECEIVES;

	}

}
