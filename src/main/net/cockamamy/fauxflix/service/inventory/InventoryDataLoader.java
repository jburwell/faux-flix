package net.cockamamy.fauxflix.service.inventory;

import static java.util.Collections.*;
import static net.cockamamy.fauxflix.service.inventory.InventoryRecord.*;

import java.io.*;
import java.util.*;

import net.cockamamy.fauxflix.util.csv.*;
import net.cockamamy.fauxflix.util.loader.*;
import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * Loads inventory data in the {@link InventoryService} from a CSV file with the
 * following columns: <li>
 * <ul>
 * Movie Title
 * </ul>
 * <ul>
 * {@link MediaType}
 * </ul>
 * <ul>
 * Initial Quantity
 * </ul>
 * </li>
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class InventoryDataLoader extends AbstractDelimitedFileDataLoader<InventoryRecord> {

	private static final String INVENTORY_DATA_FILE_NAME = "inventory.csv";

	private static final List<ColumnDefinition> COLUMN_DEFINITIONS;
	static {

		List<ColumnDefinition> theDefinitions = new ArrayList<ColumnDefinition>();

		theDefinitions.add(new ColumnDefinition(
				InventoryRecord.MOVIE_PROP_NAME, new MoviePropertyConverter()));
		theDefinitions.add(new ColumnDefinition(
				InventoryRecord.MEDIA_TYPE_PROP_NAME,
				new EnumPropertyConverter<MediaType>(MediaType.class)));
		theDefinitions.add(new ColumnDefinition(TOTAL_PROP_NAME,
				new QuantityPropertyConverter()));

		COLUMN_DEFINITIONS = unmodifiableList(theDefinitions);

	}

	private final InventoryService myInventoryService;

	/**
	 * 
	 * @param anInventoryService
	 *            The inventory service into which the inventory data will be
	 *            loaded
	 * 
	 * @since 1.0.0
	 * 
	 */
	public InventoryDataLoader(InventoryService anInventoryService,
			File aDataSetDirectory) throws FileNotFoundException {

		super(aDataSetDirectory, INVENTORY_DATA_FILE_NAME, COLUMN_DEFINITIONS);

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

		return "Inventory";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.ObjectFactory#createObject(java.util.Map)
	 */
	public InventoryRecord createObject(Map<String, Object> thePropertyValues) {

		assert thePropertyValues != null;

		Movie aMovie = (Movie) thePropertyValues
				.get(InventoryRecord.MOVIE_PROP_NAME);
		MediaType aMediaType = (MediaType) thePropertyValues
				.get(InventoryRecord.MEDIA_TYPE_PROP_NAME);
		Quantity anInitialQuantity = (Quantity) thePropertyValues
				.get(TOTAL_PROP_NAME);

		return this.myInventoryService.addStock(aMovie, aMediaType,
				anInitialQuantity);

	}

}
