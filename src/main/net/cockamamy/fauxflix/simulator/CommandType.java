package net.cockamamy.fauxflix.simulator;

import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;
import net.cockamamy.fauxflix.service.rental.*;

/**
 * 
 * The types of commands supported by the simulator
 * 
 * <strong>N.B.</strong> The order of command type definition defines the order
 * in which commands should be executed.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public enum CommandType {

	/**
	 * 
	 * The Faux Flix rental store receives a rented movie from a customer
	 * 
	 * @since 1.0.0
	 * 
	 */
	RECEIVES {

		@Override
		public Command createCommand(Date anOccurred, Customer aCustomer,
				Movie aMovie, MediaType aMediaType, RentalService aRentalService) {

			return new ReceiveMovieCommand(anOccurred, aCustomer, aMovie,
					aMediaType, aRentalService);

		}

	},

	/**
	 * 
	 * A customer mails a rented movie back to the Faux Flix rental store
	 * 
	 * @since 1.0.0
	 * 
	 */
	MAILS {

		@Override
		public Command createCommand(Date anOccurred, Customer aCustomer,
				Movie aMovie, MediaType aMediaType, RentalService aRentalService) {

			return new MailMovieCommand(anOccurred, aCustomer, aMovie,
					aMediaType, aRentalService);

		}

	},

	/**
	 * 
	 * A customer requests a rental from the Faux Flix rental store
	 * 
	 * @since 1.0.0
	 * 
	 */
	REQUESTS {

		@Override
		public Command createCommand(Date anOccurred, Customer aCustomer,
				Movie aMovie, MediaType aMediaType, RentalService aRentalService) {

			return new RequestMovieCommand(anOccurred, aCustomer, aMovie,
					aMediaType, aRentalService);

		}

	};

	/**
	 * 
	 * Factory method that creates a {@link Command} associated with this
	 * <code>CommandType</code>.
	 * 
	 * @param anOccurred
	 *            The date on which the command will occur
	 * @param aCustomer
	 *            The customer for which the command will be executed
	 * @param aMovie
	 *            The movie upon which the command will be executed
	 * @param aMediaType
	 *            The media type of the movie upon which the command will be
	 *            executed
	 * 
	 * @return The command associated with the command type initialized with the
	 *         passed occurred date, <code>anOccurred</code>, customer,
	 *         <code>aCustomer</code>, movie, <code>aMovie</code>, and media
	 *         type, <code>aMediaType</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public abstract Command createCommand(Date anOccurred, Customer aCustomer,
			Movie aMovie, MediaType aMediaType, RentalService aRentalService);

}
