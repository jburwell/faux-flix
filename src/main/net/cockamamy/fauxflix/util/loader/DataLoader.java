package net.cockamamy.fauxflix.util.loader;

import java.util.*;

/**
 * 
 * Loads domain object data from a file
 * 
 * @author jburwell
 * 
 * @param <T>
 *            The type of domain object data loaded by this data loader
 * 
 * @since 1.0.0
 * 
 */
interface DataLoader<T> {

	/**
	 * 
	 * Loads data from the passed input file, <code>anInputFile</code>
	 * 
	 * @return The domain objects loaded from the passed data file,
	 *         <code>anInputFile</code>
	 * 
	 * @since 1.0.0
	 * 
	 */
	Set<T> loadData();

}
