package net.cockamamy.fauxflix.service.inventory;

import static net.cockamamy.fauxflix.service.ServiceLocator.*;
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

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.util.csv.PropertyConverter#convertValue(java.lang.String)
	 */
	public Movie convertValue(String aValue) {

		Movie aMovie = null;

		if (isNotBlank(aValue) == true) {

			InventoryService anInventoryService = findService(InventoryService.class);
			aMovie = anInventoryService.findMovie(aValue);

		}

		return aMovie;

	}

}
