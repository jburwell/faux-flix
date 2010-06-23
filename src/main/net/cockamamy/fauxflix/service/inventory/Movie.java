package net.cockamamy.fauxflix.service.inventory;

import net.cockamamy.fauxflix.util.*;

/**
 * 
 * A movie in the Faux Flix catalog
 *
 * @author jburwell
 *
 * @since 1.0.0
 *
 */
public interface Movie extends Comparable<Movie>, Describable {

	/**
	 * 
	 * JavaBean-complaint name of the title property
	 * 
	 * @since 1.0.0
	 * 
	 */
	static final String TITLE_PROP_NAME = "title";
	
	/**
	 *
	 * @return The title of the movie
	 *
	 * @since 1.0.0
	 *
	 */
	String getTitle();
		
}
