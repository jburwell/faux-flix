package net.cockamamy.fauxflix.service.rental;

import static java.lang.String.*;

import net.cockamamy.fauxflix.service.*;

/**
 * 
 * Event emitted when a {@link Rental} is changed by the {@link RentalService}.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class RentalChangedEvent implements ServiceEvent {

	private final Rental myChangedRental;
	private final ChangeReason myReason;

	/**
	 * 
	 * @param aChangedRental
	 *            The rental that was changed
	 * @param aReason
	 *            The reason for the rental change
	 * 
	 * @since 1.0.0
	 * 
	 */
	public RentalChangedEvent(Rental aChangedRental, ChangeReason aReason) {

		super();

		this.myChangedRental = aChangedRental;
		this.myReason = aReason;

	}

	/**
	 * 
	 * @return The changed rental
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Rental getChangedRental() {

		return this.myChangedRental;

	}

	/**
	 * 
	 * @return The reason the rental changed
	 * 
	 * @since 1.0.0
	 * 
	 */
	public ChangeReason getReason() {

		return this.myReason;

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

			RentalChangedEvent thatEvent = (RentalChangedEvent) thatObject;

			if (this.myChangedRental.equals(thatEvent.getChangedRental()) == true
					&& this.myReason.equals(thatEvent.getReason()) == true) {

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

		aHashCode += (17 * aHashCode) + this.myChangedRental.hashCode();
		aHashCode += (17 * aHashCode) + this.myReason.hashCode();

		return aHashCode;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return format("RentalChangedEvent (changedRental: %1$s, reason: %2$s",
				this.myChangedRental, this.myReason);

	}

	/**
	 * 
	 * The reasons a rental was changed by the {@link RentalService}
	 * 
	 * @author jburwell
	 * 
	 * @since 1.0.0
	 * 
	 */
	public enum ChangeReason {

		/**
		 * 
		 * Customer requested a rental
		 * 
		 * @since 1.0.0
		 * 
		 */
		REQUESTED,

		/**
		 * 
		 * The rental was queued for delivery to the customer
		 * 
		 * @since 1.0.0
		 * 
		 */
		QUEUED_FOR_DELIVERY,

		/**
		 * 
		 * The rental was queued for delivery due to a lack of available stock
		 * 
		 * @since 1.0.0
		 * 
		 */
		QUEUED_OUT_OF_STOCK,

		/**
		 * 
		 * The rental was added the customer's rental queue because the their
		 * rental limit was exceeded
		 * 
		 * @since 1.0.0
		 * 
		 */
		QUEUED_LIMITED_EXCEEDED,

		/**
		 * 
		 * The rental was sent to the customer
		 * 
		 * @since 1.0.0
		 * 
		 */
		SENT,

		/**
		 * 
		 * The renal was returned to the Faux Flix rental store by the customer
		 * 
		 * @since 1.0.0
		 * 
		 */
		RETURNED;

	}

}
