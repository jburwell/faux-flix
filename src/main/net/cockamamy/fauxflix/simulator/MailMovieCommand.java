package net.cockamamy.fauxflix.simulator;

import static java.lang.String.*;
import static java.util.Calendar.*;
import static java.util.Collections.*;
import static net.cockamamy.fauxflix.simulator.CommandType.*;

import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;

/**
 * 
 * Performs the actions to represent a customer mailing a movie back to the Faux
 * Flix rental store including creating an additional
 * {@link ReceiveMovieCommand} that occurs on the day after this command
 * executes.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class MailMovieCommand extends AbstractCommand {

	private final Command myReceiveCommand;

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
	MailMovieCommand(Date anOccurred, Customer aCustomer, Movie aMovie,
			MediaType aMediaType) {

		super(anOccurred, aCustomer, aMovie, aMediaType);

		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(anOccurred);
		aCalendar.add(DAY_OF_WEEK, 1);

		this.myReceiveCommand = new ReceiveMovieCommand(aCalendar.getTime(),
				this.getCustomer(), this.getMovie(), this.getMediaType());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#execute()
	 */
	public String execute() {

		return format("%1$s mails back \"%2$s\" (%3$s)", this.getCustomer()
				.getName(), this.getMovie().getTitle(), this.getMediaType()
				.name().toLowerCase());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Command#getType()
	 */
	public CommandType getType() {

		return MAILS;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.simulator.AbstractCommand#getAdditionalCommands()
	 */
	@Override
	public Set<Command> getAdditionalCommands() {

		return unmodifiableSet(new HashSet<Command>(
				singletonList(this.myReceiveCommand)));

	}

}
