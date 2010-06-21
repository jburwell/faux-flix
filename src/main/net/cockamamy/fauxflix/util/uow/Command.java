package net.cockamamy.fauxflix.util.uow;

import java.util.*;

/**
 * 
 * A simulator command
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface Command<C extends Command<C>> {

	/**
	 * 
	 * Executes the actions of this command
	 * 
	 * @return A message describing the result of this command
	 * 
	 * @since 1.0.0
	 * 
	 */
	List<Message> execute();

	/**
	 * 
	 * @return Additional commands that must be executed after this completion
	 *         of this command's execution
	 * 
	 * @since 1.0.0
	 * 
	 */
	Set<C> getAdditionalCommands();

}
