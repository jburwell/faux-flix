package net.cockamamy.fauxflix.service.inventory;

import static net.cockamamy.fauxflix.service.inventory.MediaType.*;
import static net.cockamamy.fauxflix.service.inventory.Quantity.*;
import static net.cockamamy.fauxflix.service.inventory.StockChangedEvent.ChangeType.*;

import java.util.*;

import net.cockamamy.fauxflix.service.*;

/**
 *
 * @author jburwell
 *
 * @since 1.0.0
 *
 */
final class InventoryServiceImpl extends AbstractService<StockChangedEvent>
		implements InventoryService {

	private Map<Movie, Map<MediaType, InventoryRecord>> myLedger;

	private Map<String, Movie> myMovies;

	/**
	 *
	 * Default constructor
	 *
	 * @since 1.0.0
	 *
	 */
	public InventoryServiceImpl() {

		super();

		this.myLedger = new HashMap<Movie, Map<MediaType, InventoryRecord>>();
		this.myMovies = new HashMap<String, Movie>();

	}

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.service.inventory.InventoryService#addMovie(java.lang.String)
	 */
	public Movie addMovie(String aTitle) {

		Movie aMovie = new MovieImpl(aTitle);

		this.myMovies.put(aMovie.getTitle(), aMovie);
		this.myLedger.put(aMovie, new HashMap<MediaType, InventoryRecord>());

		for (MediaType aMediaType : MediaType.values()) {

			this.myLedger.get(aMovie).put(aMediaType,
					new InventoryRecordImpl(aMovie, aMediaType, ZERO, ZERO));

		}

		this.notifyListeners(new StockChangedEvent(aMovie, NONE, CREATED, ZERO,
				ZERO));

		return aMovie;

	}

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.service.inventory.InventoryService#findMovie(java.lang.String)
	 */
	public Movie findMovie(String aTitle) {

		// TODO Implement the null object pattern -> Nullable interface
		// TODO Create DefaultValueMap
		return this.myMovies.get(aTitle);

	}

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.service.inventory.InventoryService#allocateStock(net.cockamamy.fauxflix.service.inventory.Movie, net.cockamamy.fauxflix.service.inventory.MediaType)
	 */
	public boolean allocateStock(Movie aMovie, MediaType aMediaType) {

		InventoryRecord theCurrentRecord = findInventoryRecord(aMovie,
				aMediaType);

		if (theCurrentRecord.getAvailable().getAmount() > 0) {

			InventoryRecord anUpdatedRecord = new InventoryRecordImpl(
					theCurrentRecord, theCurrentRecord.getAvailable()
							.decrease());

			this.myLedger.get(aMovie).put(aMediaType, anUpdatedRecord);

			this.notifyListeners(new StockChangedEvent(aMovie, aMediaType,
					REMOVED, theCurrentRecord.getAvailable(), anUpdatedRecord
							.getAvailable()));

			return true;

		}

		return false;

	}

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.service.inventory.InventoryService#addStock(net.cockamamy.fauxflix.service.inventory.Movie, net.cockamamy.fauxflix.service.inventory.MediaType, net.cockamamy.fauxflix.service.inventory.Quantity)
	 */
	public InventoryRecord addStock(Movie aMovie, MediaType aMediaType,
			Quantity anInitialQuantity) {

		assert aMovie != null;
		assert aMediaType != null;
		assert anInitialQuantity != null;
		assert this.myLedger.containsKey(aMovie) == true;

		InventoryRecord aRecord = new InventoryRecordImpl(aMovie, aMediaType,
				anInitialQuantity);
		this.myLedger.get(aMovie).put(aMediaType, aRecord);

		this.notifyListeners(new StockChangedEvent(aMovie, aMediaType, ADDED,
				ZERO, aRecord.getAvailable()));

		return aRecord;

	}

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.service.inventory.InventoryService#returnStock(net.cockamamy.fauxflix.service.inventory.Movie, net.cockamamy.fauxflix.service.inventory.MediaType)
	 */
	public boolean returnStock(Movie aMovie, MediaType aMediaType) {

		InventoryRecord theCurrentRecord = findInventoryRecord(aMovie,
				aMediaType);

		InventoryRecordImpl anUpdatedRecord = new InventoryRecordImpl(
				theCurrentRecord, theCurrentRecord.getAvailable().increase());
		this.myLedger.get(aMovie).put(aMediaType, anUpdatedRecord);

		this
				.notifyListeners(new StockChangedEvent(aMovie, aMediaType, ADDED,
						theCurrentRecord.getAvailable(), anUpdatedRecord
								.getAvailable()));

		return true;

	}

	private InventoryRecord findInventoryRecord(Movie aMovie,
			MediaType aMediaType) {

		assert this.myLedger.containsKey(aMovie) == true
				&& this.myLedger.get(aMovie).containsKey(aMediaType) == true;

		return this.myLedger.get(aMovie).get(aMediaType);

	}

}
