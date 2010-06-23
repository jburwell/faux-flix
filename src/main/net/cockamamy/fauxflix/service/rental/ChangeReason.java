package net.cockamamy.fauxflix.service.rental;

import static java.lang.String.*;

/**
 * 
 * The reasons a rental was changed by the {@link RentalService}
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
// TODO Extract to a standalone unit ...
public enum ChangeReason {

	/**
	 * 
	 * Customer requested a rental
	 * 
	 * @since 1.0.0
	 * 
	 */
	REQUESTED {

		@Override
		public String describeAction(Rental aChangedRental,
				RentalService aRentalService) {

			return format("%1$s requests %2$s", aChangedRental.getCustomer()
					.describe(), aChangedRental.describe());

		}

	},

	/**
	 * 
	 * The rental was queued for delivery to the customer
	 * 
	 * @since 1.0.0
	 * 
	 */
	QUEUED_FOR_DELIVERY {

		@Override
		public String describeAction(Rental aChangedRental,
				RentalService aRentalService) {

			return format("%1$s has been queued for delivery to %2$s",
					aChangedRental.describe(), aChangedRental.describe());

		}

	},

	/**
	 * 
	 * The rental was queued for delivery due to a lack of available stock
	 * 
	 * @since 1.0.0
	 * 
	 */
	QUEUED_OUT_OF_STOCK {

		@Override
		public String describeAction(Rental aChangedRental,
				RentalService aRentalService) {

			return format(
					"%1$s is out of stock; %2$s's request was added to the queue.",
					aChangedRental.describe(), aChangedRental.getCustomer()
							.describe());

		}

	},

	/**
	 * 
	 * The rental was added the customer's rental queue because the their rental
	 * limit was exceeded
	 * 
	 * @since 1.0.0
	 * 
	 */
	QUEUED_LIMITED_EXCEEDED {

		@Override
		public String describeAction(Rental aChangedRental,
				RentalService aRentalService) {

			return format(
					"%1$s has exceed their rental limit; %1$s's request has been added to their rental queue.",
					aChangedRental.getCustomer().describe());

		}

	},

	/**
	 * 
	 * The rental was sent to the customer
	 * 
	 * @since 1.0.0
	 * 
	 */
	SENT {

		@Override
		public String describeAction(Rental aChangedRental,
				RentalService aRentalService) {

			return format("Store mails %1$s to %2$s",
					aChangedRental.describe(), aChangedRental.getCustomer()
							.describe());

		}

	},

	/**
	 * 
	 * The renal was returned to the Faux Flix rental store by the customer
	 * 
	 * @since 1.0.0
	 * 
	 */
	RETURNED {

		@Override
		public String describeAction(Rental aChangedRental,
				RentalService aRentalService) {

			final int theRentalCount = aRentalService.findRentals(
					aChangedRental.getCustomer()).size();
			
			return format(
					"%1$s returned (%2$s) to stock; %1$s has %3$s movies still rented.",
					aChangedRental.getCustomer().describe(), aChangedRental
							.describe(), theRentalCount);

		}

	};

	public abstract String describeAction(Rental aChangedRental,
			RentalService aRentalService);

}