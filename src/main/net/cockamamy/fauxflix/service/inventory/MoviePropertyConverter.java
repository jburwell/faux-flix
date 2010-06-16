package net.cockamamy.fauxflix.service.inventory;

import static java.lang.String.*;
import static net.cockamamy.fauxflix.util.StringUtilities.*;

import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * Converts a string value to a {@link Movie} object
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public class MoviePropertyConverter implements PropertyConverter<Movie> {

	private final InventoryService myInventoryService;

	public MoviePropertyConverter(InventoryService anInventoryService) {

		super();

		assert anInventoryService != null : format(
				"%1$s(InventoryService) requires a non-null InventoryService instance.",
				this.getClass().getName());

		this.myInventoryService = anInventoryService;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.PropertyConverter#convertValue(java.lang
	 * .String)
	 */
	public Movie convertValue(String aValue) {

		Movie aMovie = null;

		if (isNotBlank(aValue) == true) {

			aMovie = this.myInventoryService.findMovie(aValue);

		}

		return aMovie;

	}

}
