package net.cockamamy.fauxflix.util.uow;

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
public interface UnitOfWork<C extends Command<C>> {

	/**
	 * 
	 * @return The list of commands that compose this unit of work
	 * 
	 * @since 1.0.0
	 * 
	 */
	Set<C> getCommands();

}
