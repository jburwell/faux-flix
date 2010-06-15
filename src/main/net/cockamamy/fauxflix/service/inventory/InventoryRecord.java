package net.cockamamy.fauxflix.service.inventory;

/**
 * 
 * The available and total stock information for a movie by media type. This
 * representation allows inventory to be distinguished by physical media type
 * (i.e. The Usual Suspects (DVD) versus The Usual Suspects (Bluray)) without
 * loosing the logical fidelity of the movies offered by the Faux Flix rental
 * store.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface InventoryRecord {

	/**
	 * 
	 * JavaBean-complaint name of the movie property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String MOVIE_PROP_NAME = "movie";

	/**
	 * 
	 * JavaBean-complaint name of the media type property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String MEDIA_TYPE_PROP_NAME = "mediaType";

	/**
	 * 
	 * JavaBean-complaint name of the available quantity property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String AVAILABLE_PROP_NAME = "available";

	/**
	 * 
	 * JavaBean-complaint name of the total quantity property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String TOTAL_PROP_NAME = "total";

	/**
	 * 
	 * @return The movie associated with this inventory record
	 * 
	 * @since 1.0.0
	 * 
	 */
	Movie getMovie();

	/**
	 * 
	 * @return The media type associated with this inventory record.
	 * 
	 * @since 1.0.0
	 * 
	 */
	MediaType getMediaType();

	/**
	 * 
	 * @return The current quantity of the movie/media type in stock
	 * 
	 * @since 1.0.0
	 * 
	 */
	Quantity getAvailable();

	/**
	 * 
	 * @return The total quantity of the movie/media owned by the Faux Flix
	 *         rental store
	 * 
	 * @since 1.0.0
	 * 
	 */
	Quantity getTotal();

}
