package net.cockamamy.fauxflix.service.inventory;

import static java.util.Collections.*;
import static net.cockamamy.fauxflix.service.inventory.Movie.*;

import java.io.*;
import java.util.*;

import net.cockamamy.fauxflix.util.csv.*;
import net.cockamamy.fauxflix.util.loader.*;
import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * Loads movie data in the {@link InventoryService} from a CSV file with the
 * following columns: <li>
 * <ul>
 * Movie Title
 * </ul>
 * </li>
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class MovieDataLoader extends AbstractDelimitedFileDataLoader<Movie> {

	private static final String MOVIE_DATA_FILE_NAME = "movies.csv";

	private static final List<ColumnDefinition> COLUMN_DEFINITIONS;
	static {

		List<ColumnDefinition> theDefinitions = new ArrayList<ColumnDefinition>();

		theDefinitions.add(new ColumnDefinition(TITLE_PROP_NAME,
				new StringPropertyConverter()));

		COLUMN_DEFINITIONS = unmodifiableList(theDefinitions);

	}

	private final InventoryService myInventoryService;

	/**
	 * 
	 * @param anInventoryService
	 * 
	 * @since 1.0.0
	 * 
	 */
	public MovieDataLoader(InventoryService anInventoryService,
			File aDataSetDirectory) throws FileNotFoundException {

		super(aDataSetDirectory, MOVIE_DATA_FILE_NAME, COLUMN_DEFINITIONS);

		assert anInventoryService != null;

		this.myInventoryService = anInventoryService;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.AbstractCSVDataLoader#getDescription()
	 */
	@Override
	protected String getDescription() {

		return "Movie";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.ObjectFactory#createObject(java.util.Map)
	 */
	public Movie createObject(Map<String, Object> thePropertyValues) {

		assert thePropertyValues != null;

		return this.myInventoryService.addMovie((String) thePropertyValues
				.get(TITLE_PROP_NAME));

	}

}
