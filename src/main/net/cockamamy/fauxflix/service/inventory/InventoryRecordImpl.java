package net.cockamamy.fauxflix.service.inventory;

import static java.lang.String.*;

/**
 * 
 * An immutable {@link InventoryRecord} implementation.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class InventoryRecordImpl implements InventoryRecord {

	private final Movie myMovie;

	private final MediaType myMediaType;

	private final Quantity myAvailableQuantity;

	private final Quantity myTotalQuantity;

	/**
	 * 
	 * @param aMovie
	 *            The movie to which this inventory record is associated
	 * @param aMediaType
	 *            The media type of the movie in stock
	 * @param anAvailableQuantity
	 *            The number of copies that are currently in stock
	 * @param aTotalQuantity
	 *            The number of copies owned by the store.
	 * 
	 * @since 1.0.0
	 * 
	 */
	InventoryRecordImpl(Movie aMovie, MediaType aMediaType,
			Quantity anAvailableQuantity, Quantity aTotalQuantity) {

		super();

		assert aMovie != null : format("%1$s requires non-null movie.", this
				.getClass().getName());
		assert aMediaType != null : format(
				"%1$s requires non-null media type.", this.getClass().getName());
		assert anAvailableQuantity != null : format(
				"%1$s requires non-null available quantity.", this.getClass()
						.getName());
		assert aTotalQuantity != null : format(
				"%1$s requires non-null total quantity.", this.getClass()
						.getName());
		assert anAvailableQuantity.getAmount() <= aTotalQuantity.getAmount() : format(
				"%1$s requires total quantity is greater than or equal to the avaiable quantity.",
				this.getClass().getName());

		this.myMovie = aMovie;
		this.myMediaType = aMediaType;
		this.myAvailableQuantity = anAvailableQuantity;
		this.myTotalQuantity = aTotalQuantity;

	}

	/**
	 * 
	 * Copy constructor with a different available quantity
	 * 
	 * @param anInventoryRecord
	 *            The inventory record to copy
	 * @param anAvailableQuantity
	 *            The number of copies currently in stock
	 * 
	 * @since 1.0.0
	 * 
	 */
	InventoryRecordImpl(InventoryRecord anInventoryRecord,
			Quantity anAvailableQuantity) {

		this(anInventoryRecord.getMovie(), anInventoryRecord.getMediaType(),
				anAvailableQuantity, anInventoryRecord.getTotal());

	}

	/**
	 * 
	 * Convenience constructor that sets the available and total quantities to
	 * the same value, <code>anInitialQuantity</code>.
	 * 
	 * @param aMovie
	 *            The movie to which this inventory record is associated
	 * @param aMediaType
	 *            The media type of the movie in stock
	 * @param anInitialQuantity
	 *            The initial quantity for both the available and total
	 *            quantities
	 * 
	 * @since 1.0.0
	 * 
	 */
	InventoryRecordImpl(Movie aMovie, MediaType aMediaType,
			Quantity anInitialQuantity) {

		this(aMovie, aMediaType, anInitialQuantity, anInitialQuantity);

	}

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.service.inventory.InventoryRecord#getAvailable()
	 */
	public Quantity getAvailable() {

		return this.myAvailableQuantity;

	}

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.service.inventory.InventoryRecord#getMediaType()
	 */
	public MediaType getMediaType() {

		return this.myMediaType;

	}

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.service.inventory.InventoryRecord#getMovie()
	 */
	public Movie getMovie() {

		return this.myMovie;

	}

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.service.inventory.InventoryRecord#getTotal()
	 */
	public Quantity getTotal() {

		return this.myTotalQuantity;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object thatObject) {

		boolean aReturnValue = false;

		if (thatObject != null
				&& this.getClass().equals(thatObject.getClass()) == true) {

			InventoryRecordImpl thatInventoryRecord = (InventoryRecordImpl) thatObject;

			if (this.myMovie.equals(thatInventoryRecord.getMovie()) == true
					&& this.myMediaType.equals(thatInventoryRecord
							.getMediaType()) == true
					&& this.myAvailableQuantity.equals(thatInventoryRecord
							.getAvailable()) == true
					&& this.myTotalQuantity.equals(thatInventoryRecord
							.getTotal()) == true) {

				aReturnValue = true;

			}

		}

		return aReturnValue;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int aHashCode = 37;

		aHashCode = (17 * aHashCode) + this.myMovie.hashCode();
		aHashCode = (17 * aHashCode) + this.myMediaType.hashCode();
		aHashCode = (17 * aHashCode) + this.myAvailableQuantity.hashCode();
		aHashCode = (17 * aHashCode) + this.myTotalQuantity.hashCode();

		return aHashCode;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return format(
				"Inventory Record [movie: %1$s, mediaType: %2$s, available: %3$s, total: %4$s]",
				this.myMovie, this.myMediaType, this.myAvailableQuantity,
				this.myTotalQuantity);

	}

}
