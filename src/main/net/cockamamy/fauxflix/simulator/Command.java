package net.cockamamy.fauxflix.simulator;

import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;

/**
 * 
 * A simulator command
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface Command {

	/**
	 * 
	 * JavaBean-complaint name of the type property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String TYPE_PROP_NAME = "type";

	/**
	 * 
	 * JavaBean-complaint name of the occurred property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String OCCURRED_PROP_NAME = "occurred";

	/**
	 * 
	 * JavaBean-complaint name of the customer property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String CUSTOMER_PROP_NAME = "customer";

	/**
	 * 
	 * JavaBean-complaint name of the movie property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String MOVIE_PROP_NAME = "movie";

	/**
	 * 
	 * JavaBean-complaint name of the media type property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String MEDIA_TYPE_PROP_NAME = "mediaType";

	/**
	 * 
	 * @return The type of command
	 * 
	 * @since 1.0.0
	 * 
	 */
	CommandType getType();

	/**
	 * 
	 * @return The date on which the actions of the command will occur
	 * 
	 * @since 1.0.0
	 * 
	 */
	Date getOccurred();

	/**
	 * 
	 * @return The movie for which the command will be executed
	 * 
	 * @since 1.0.0
	 * 
	 */
	Movie getMovie();

	/**
	 * 
	 * @return The customer for which the command will be executed
	 * 
	 * @since 1.0.0
	 * 
	 */
	Customer getCustomer();

	/**
	 * 
	 * @return The media type of the movie for which this command will be
	 *         executed
	 * 
	 * @since 1.0.0
	 * 
	 */
	MediaType getMediaType();

	/**
	 * 
	 * Executes the actions of this command
	 * 
	 * @return A message describing the result of this command
	 * 
	 * @since 1.0.0
	 * 
	 */
	String execute();

	/**
	 * 
	 * @return Additional commands that must be executed after this completion
	 *         of this command's execution
	 * 
	 * @since 1.0.0
	 * 
	 */
	Set<Command> getAdditionalCommands();

}
