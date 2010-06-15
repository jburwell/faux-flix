package net.cockamamy.fauxflix.service.inventory;

import static java.lang.String.*;

import net.cockamamy.fauxflix.service.*;

/**
 * 
 * Event emitted when the operation of {@link InventoryService} causes a change
 * in the stock or movie catalog of the Faux Flix rental store.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class StockChangedEvent implements ServiceEvent {

	private final Movie myMovie;
	private final MediaType myMediaType;
	private final Quantity myOriginalQuantity;
	private final Quantity myNewQuantity;
	private final ChangeType myChangeType;

	/**
	 * 
	 * @param aMovie
	 *            The movie affected by the inventory change
	 * @param aMediaType
	 *            The media type of the movie affected by the inventory change
	 * @param aChangeType
	 *            The type of inventory change that occurred
	 * @param anOriginalQuantity
	 *            The quantity before the inventory change occurred
	 * @param aNewQuantity
	 *            The quantity after the inventory change occurred
	 * 
	 * @since 1.0.0
	 * 
	 */
	StockChangedEvent(Movie aMovie, MediaType aMediaType,
			ChangeType aChangeType, Quantity anOriginalQuantity,
			Quantity aNewQuantity) {

		super();

		assert aMovie != null : format("%1%s requires non-null movie.", this
				.getClass().getName());
		assert aMediaType != null : format(
				"%1%s requires non-null media type.", this.getClass().getName());
		assert aChangeType != null : format(
				"%1%s requires non-null change type.", this.getClass()
						.getName());
		assert anOriginalQuantity != null : format(
				"%1%s requires non-null original quantity.", this.getClass()
						.getName());
		assert aNewQuantity != null : format(
				"%1%s requires non-null new quantity.", this.getClass()
						.getName());

		this.myMovie = aMovie;
		this.myMediaType = aMediaType;
		this.myChangeType = aChangeType;
		this.myOriginalQuantity = anOriginalQuantity;
		this.myNewQuantity = aNewQuantity;

	}

	/**
	 * 
	 * @return The movie affected by the inventory change
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Movie getMovie() {

		return this.myMovie;

	}

	/**
	 * 
	 * @return The media type of the movie affected by the inventory change
	 * 
	 * @since 1.0.0
	 * 
	 */
	public MediaType getMediaType() {

		return this.myMediaType;

	}

	/**
	 * 
	 * @return The quantity before the inventory change occurred
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Quantity getOriginalQuantity() {

		return this.myOriginalQuantity;

	}

	/**
	 * 
	 * @return The quantity after the inventory change occurred
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Quantity getNewQuantity() {

		return this.myNewQuantity;

	}

	/**
	 * 
	 * @return The type of change that occurred to the store's inventory
	 * 
	 * @since 1.0.0
	 * 
	 */
	public ChangeType getChangeType() {

		return this.myChangeType;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object thatObject) {

		boolean aReturnValue = false;

		if (thatObject != null
				&& this.getClass().equals(thatObject.getClass()) == true) {

			StockChangedEvent thatEvent = (StockChangedEvent) thatObject;

			if (this.myMovie.equals(thatEvent.getMovie()) == true
					&& this.myChangeType.equals(thatEvent.getChangeType()) == true
					&& this.myMediaType.equals(thatEvent.getMediaType()) == true
					&& this.myNewQuantity.equals(thatEvent.getNewQuantity()) == true
					&& this.myOriginalQuantity.equals(thatEvent
							.getOriginalQuantity()) == true) {

				aReturnValue = true;

			}

		}

		return aReturnValue;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int aHashCode = 37;

		aHashCode = (17 * aHashCode) + this.myChangeType.hashCode();
		aHashCode = (17 * aHashCode) + this.myMediaType.hashCode();
		aHashCode = (17 * aHashCode) + this.myMovie.hashCode();
		aHashCode = (17 * aHashCode) + this.myNewQuantity.hashCode();
		aHashCode = (17 * aHashCode) + this.myOriginalQuantity.hashCode();

		return aHashCode;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return format(
				"StockChangeEvent [changeType: %1$s, mediaType: %2$s, movie: %3$s, newQuantity: %4$s, originalQuantity: %5$s]",
				this.myChangeType, this.myMediaType, this.myMovie,
				this.myNewQuantity, this.myOriginalQuantity);

	}

	/**
	 * 
	 * The type inventory change that occurred
	 * 
	 * @author jburwell
	 * 
	 * @since 1.0.0
	 * 
	 */
	public enum ChangeType {

		/**
		 * 
		 * Stock was added to the store's inventory
		 * 
		 * @since 1.0.0
		 * 
		 */
		ADDED,

		/**
		 * 
		 * Stock was removed from the store's inventory
		 * 
		 * @since 1.0.0
		 * 
		 */
		REMOVED,

		/**
		 * 
		 * A movie was added to the store's catalog
		 * 
		 * @since 1.0.0
		 * 
		 */
		CREATED;

	}

}
