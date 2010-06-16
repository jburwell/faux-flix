package net.cockamamy.fauxflix.service.inventory;

import static net.cockamamy.fauxflix.service.inventory.InventoryRecord.*;
import static net.cockamamy.fauxflix.util.CollectionUtilities.*;

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
public final class InventoryDataLoader extends
		AbstractDelimitedFileDataLoader<InventoryRecord> {

	private static final String INVENTORY_DATA_FILE_NAME = "inventory.csv";

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

		super(aDataSetDirectory, INVENTORY_DATA_FILE_NAME,
				buildUnmodifiableList(new ColumnDefinition(
						InventoryRecord.MOVIE_PROP_NAME,
						new MoviePropertyConverter(anInventoryService)),
						new ColumnDefinition(
								InventoryRecord.MEDIA_TYPE_PROP_NAME,
								new EnumPropertyConverter<MediaType>(
										MediaType.class)),
						new ColumnDefinition(TOTAL_PROP_NAME,
								new QuantityPropertyConverter())));

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
