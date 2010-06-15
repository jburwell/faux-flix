package net.cockamamy.fauxflix.service.inventory;

import net.cockamamy.fauxflix.service.*;

/**
 * 
 * Manages the physical inventory of the Faux Flix rental store based on the
 * media type of a movie.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface InventoryService extends Service<StockChangedEvent> {

	/**
	 * 
	 * Adds a movie with the passed title, <code>aTitle</code>, to the Faux Flix
	 * movie store.
	 * 
	 * Upon successful completion of this operation, an
	 * {@link StockChangedEvent} is emitted with a
	 * {@link StockChangedEvent.ChangeType} of
	 * {@link StockChangedEvent.ChangeType#CREATED}
	 * 
	 * @param aTitle
	 *            The title of the movie to be added
	 * 
	 * @return The movie added to the Faux Flix rental store with the passed
	 *         title, <code>aTitle</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Movie addMovie(String aTitle);

	/**
	 * 
	 * Finds a movie for the passed title, <code>aTitle</code>.
	 * 
	 * @param aTitle
	 *            The title of the movie to find
	 * 
	 * @return The movie matching the passed title, <code>aTitle</code>. If no
	 *         movie is found matching <code>aTitle</code>, this method returns
	 *         <code>null</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Movie findMovie(String aTitle);

	/**
	 * 
	 * Adds stock for a movie/media type to the Faux Flix rental store.
	 * 
	 * @param aMovie
	 *            The movie for which to add stock
	 * @param aMediaType
	 *            The media type of the movie for which to add stock
	 * @param anInitialQuantity
	 *            The quantity to add
	 * 
	 * @return The inventory of the passed movie, <code>aMovie</code>, and media
	 *         type, <code>aMediaType</code>, with the passed quantity,
	 *         <code>anInitialQuantity</code>, added.
	 * 
	 * @since 1.0.0
	 * 
	 */
	InventoryRecord addStock(Movie aMovie, MediaType aMediaType,
			Quantity anInitialQuantity);

	/**
	 * 
	 * If inventory is available, allocates one (1) movie/media type from stock
	 * 
	 * If inventory was available for allocation, an {@link StockChangedEvent}
	 * is emitted with a {@link StockChangedEvent.ChangeType} of
	 * {@link StockChangedEvent.ChangeType#REMOVED}
	 * 
	 * @param aMovie
	 *            The movie for which to allocate stock
	 * @param aMediaType
	 *            The media type of the movie to allocate stock
	 * 
	 * @return <code>true</code>: Stock was allocated. <code>false</code>: Stock
	 *         was <strong>not</strong> allocated.
	 * 
	 * @since 1.0.0
	 * 
	 */
	boolean allocateStock(Movie aMovie, MediaType aMediaType);

	/**
	 * 
	 * Returns one (1) movie/media type to stock.
	 * 
	 * If inventory was returned, an {@link StockChangedEvent} is emitted with a
	 * {@link StockChangedEvent.ChangeType} of
	 * {@link StockChangedEvent.ChangeType#ADDED}
	 * 
	 * @param aMovie
	 *            The movie to return to stock
	 * @param aMediaType
	 *            The media type of the movie to return to stock
	 * 
	 * @return <true>: Stock was returned. <false>Stock was <strong>not</strong>
	 *         returned.
	 * 
	 * @since 1.0.0
	 * 
	 */
	boolean returnStock(Movie aMovie, MediaType aMediaType);

}
