package net.cockamamy.fauxflix.service.customer;

import net.cockamamy.fauxflix.service.*;

/**
 * 
 * Event emitted by the {@link CustomerService} when a Faux Flix customer is
 * added to the Faux Flix rental store.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class CustomerAddedEvent implements ServiceEvent {

	private final Customer myAddedCustomer;

	/**
	 * 
	 * @param theAddedCustomer
	 *            The customer added to the Faux Flix rental store.
	 * 
	 * @since 1.0.0
	 * 
	 */
	CustomerAddedEvent(Customer theAddedCustomer) {

		super();

		this.myAddedCustomer = theAddedCustomer;

	}

	/**
	 * 
	 * @return The customer added to the Faux Flix rental store
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Customer getAddedCustomer() {

		return this.myAddedCustomer;

	}

}
