package net.cockamamy.fauxflix.simulator;

import static java.util.Collections.*;
import static net.cockamamy.fauxflix.simulator.SimulatorCommandType.*;

import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;
import net.cockamamy.fauxflix.service.rental.*;
import net.cockamamy.fauxflix.util.uow.*;

/**
 * 
 * Performs the actions to represent a customer requesting a movie rental from
 * the Faux Flix rental store.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class RequestMovieCommand extends AbstractSimulatorCommand {

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
	RequestMovieCommand(Date anOccurred, Customer aCustomer, Movie aMovie,
			MediaType aMediaType, RentalService aRentalService) {

		super(anOccurred, aCustomer, aMovie, aMediaType, aRentalService);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#execute()
	 */
	public List<Message> execute() {

		this.getRentalService().rentMovie(this.getCustomer(), this.getMovie(), this
				.getMediaType(), this.getOccurred());

		return emptyList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#getType()
	 */
	public SimulatorCommandType getType() {

		return REQUESTS;

	}

}
