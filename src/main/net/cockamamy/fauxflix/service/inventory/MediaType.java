package net.cockamamy.fauxflix.service.inventory;

import net.cockamamy.fauxflix.util.*;

/**
 * 
 * The types of media Faux Flix rental store rents
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public enum MediaType implements Describable {

	/**
	 * 
	 * Standard DVD media type
	 * 
	 * @since 1.0.0
	 * 
	 */
	DVD,

	/**
	 * 
	 * Blueray media type
	 * 
	 * @since 1.0.0
	 * 
	 */
	BLURAY,

	/**
	 * 
	 * Logically null media type
	 * 
	 * @since 1.0.0
	 * 
	 */
	NONE;

	public String describe() {

		return this.name().toLowerCase();
		
	}

}
