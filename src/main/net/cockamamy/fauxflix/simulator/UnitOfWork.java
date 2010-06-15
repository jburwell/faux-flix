package net.cockamamy.fauxflix.simulator;

import java.util.*;

/**
 * 
 * An atomic unit of work represented as an list of {@link Command}s that must
 * all execute successfully.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface UnitOfWork {

	/**
	 * 
	 * Executes the commands that compose this unit of work
	 * 
	 * @since 1.0.0
	 * 
	 */
	void executeCommands();

	/**
	 * 
	 * @return The list of commands that compose this unit of work
	 * 
	 * @since 1.0.0
	 * 
	 */
	Set<Command> getCommands();

}
