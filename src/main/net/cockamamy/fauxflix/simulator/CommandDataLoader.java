package net.cockamamy.fauxflix.simulator;

import static java.util.Collections.*;
import static net.cockamamy.fauxflix.simulator.Command.*;

import java.io.*;
import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;
import net.cockamamy.fauxflix.util.csv.*;
import net.cockamamy.fauxflix.util.loader.*;
import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * Loads simulator command data from a CSV file with the following columns:
 * 
 * <li>
 * <ul>
 * Occurred Date
 * </ul>
 * <ul>
 * Customer Name
 * </ul>
 * <ul>
 * {@link CommandType}
 * </ul>
 * <ul>
 * Movie Title
 * </ul>
 * <ul>
 * {@link MediaType}
 * </ul>
 * </li>
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class CommandDataLoader extends
		AbstractDelimitedFileDataLoader<Command> {

	private static final String COMMAND_DATA_FILE_NAME = "commands.csv";

	private static final List<ColumnDefinition> COLUMNS_DEFINITIONS;

	static {

		List<ColumnDefinition> theDefinitions = new ArrayList<ColumnDefinition>();

		theDefinitions.add(new ColumnDefinition(OCCURRED_PROP_NAME,
				new DatePropertyConverter()));
		theDefinitions.add(new ColumnDefinition(CUSTOMER_PROP_NAME,
				new CustomerPropertyConverter()));
		theDefinitions.add(new ColumnDefinition(TYPE_PROP_NAME,
				new EnumPropertyConverter<CommandType>(CommandType.class)));
		theDefinitions.add(new ColumnDefinition(Command.MOVIE_PROP_NAME,
				new MoviePropertyConverter()));
		theDefinitions.add(new ColumnDefinition(Command.MEDIA_TYPE_PROP_NAME,
				new EnumPropertyConverter<MediaType>(MediaType.class)));

		COLUMNS_DEFINITIONS = unmodifiableList(theDefinitions);

	}

	/**
	 * 
	 * Default constructor
	 * 
	 * @since 1.0.0
	 * 
	 */
	public CommandDataLoader(File aDataSetDirectory) throws FileNotFoundException {

		super(aDataSetDirectory, COMMAND_DATA_FILE_NAME, COLUMNS_DEFINITIONS);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.AbstractCSVDataLoader#getDescription()
	 */
	@Override
	protected String getDescription() {

		return "Command";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.ObjectFactory#createObject(java.util.Map)
	 */
	public Command createObject(Map<String, Object> thePropertyValues) {

		assert thePropertyValues != null;

		CommandType aType = (CommandType) thePropertyValues.get(TYPE_PROP_NAME);
		Date anOccurred = (Date) thePropertyValues.get(OCCURRED_PROP_NAME);
		Customer aCustomer = (Customer) thePropertyValues
				.get(CUSTOMER_PROP_NAME);
		Movie aMovie = (Movie) thePropertyValues.get(Command.MOVIE_PROP_NAME);
		MediaType aMediaType = (MediaType) thePropertyValues
				.get(Command.MEDIA_TYPE_PROP_NAME);

		return aType.createCommand(anOccurred, aCustomer, aMovie, aMediaType);

	}

}
