package net.cockamamy.fauxflix.service.inventory;

import static java.lang.String.*;

/**
 * 
 * An immutable value object representing an inventory quantity.
 * 
 * All operations result in the creation of a new <code>Quantity</code> object.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class Quantity {

	/**
	 * 
	 * Constant representing a quantity of zero (0).
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final Quantity ZERO = new Quantity(0L);

	private final long myAmount;

	/**
	 * 
	 * @param anAmount
	 *            The amount of the quantity
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Quantity(final long anAmount) {

		super();

		assert anAmount >= 0 : "A quantity must be greater than or equal to zero.";

		this.myAmount = anAmount;

	}

	/**
	 * 
	 * @return The amount of this quantity
	 * 
	 * @since 1.0.0
	 * 
	 */
	public long getAmount() {

		return this.myAmount;

	}

	/**
	 * 
	 * Decreases the amount of this quantity by one (1).
	 * 
	 * @return A new <code>Quantity</code> instance with the amount decreased by
	 *         one (1).
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Quantity decrease() {

		return decrease(1L);

	}

	/**
	 * 
	 * Decrease the amount of this quantity by the passed amount,
	 * <code>anAmount</code>.
	 * 
	 * @param anAmount
	 *            The amount by which to decrease the quantity
	 * 
	 * @return A new <code>Quantity</code> instance with the amount decreased by
	 *         the passed amount, <code>anAmount</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Quantity decrease(final long anAmount) {

		assert anAmount >= 0 : "The decrement amount must be greater than or equal to 0.";

		long aNewAmount = this.myAmount - anAmount;

		if (aNewAmount < 0) {

			aNewAmount = 0;

		}

		return new Quantity(aNewAmount);

	}

	/**
	 * 
	 * Increases the amount of this quantity by one (1).
	 * 
	 * @return A new <code>Quantity</code> instance with the amount increased by
	 *         one (1).
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Quantity increase() {

		return increase(1L);

	}

	/**
	 * 
	 * Increase the amount of this quantity by the passed amount,
	 * <code>anAmount</code>.
	 * 
	 * @param anAmount
	 *            The amount by which to increase the quantity
	 * 
	 * @return A new <code>Quantity</code> instance with the amount increase by
	 *         the passed amount, <code>anAmount</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Quantity increase(final long anAmount) {

		assert anAmount >= 0 : "The increment amount must be greater than or equal to 0.";

		return new Quantity(this.myAmount + anAmount);

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

			Quantity thatQuantity = (Quantity) thatObject;

			if (this.myAmount == thatQuantity.getAmount()) {

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

		aHashCode = (17 * aHashCode) + Long.valueOf(this.myAmount).intValue();

		return aHashCode;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return format("Quantity: %1$s", this.myAmount);

	}

}
