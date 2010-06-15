package net.cockamamy.fauxflix.service.customer;

import net.cockamamy.fauxflix.service.*;

/**
 * 
 * Manages the addition and retrieval of Faux Flix customers. This service emits
 * {@link CustomerAddedEvent} events when customers are added to the Faux Flix
 * rental store.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface CustomerService extends Service<CustomerAddedEvent> {

	/**
	 * 
	 * Adds a customer to the Faux Flix rental store.
	 * 
	 * Upon successful completion of this operation, a
	 * {@link CustomerAddedEvent} is emitted.
	 * 
	 * @param aCustomerName
	 *            The name of the customer to add
	 * @param aMembershipLevel
	 *            The membership level of the customer to add
	 * 
	 * @return The {@link Customer} added to the Faux Flix rental store with the
	 *         passed customer name, <code>aCustomerName</code>, and membership
	 *         level, <code>aMembershipLevel</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Customer addCustomer(String aCustomerName, MembershipLevel aMembershipLevel);

	/**
	 * 
	 * Finds a {@link Customer} by the passed name, <code>aCustomerName</code>.
	 * 
	 * @param aCustomerName
	 *            The name of the customer to find.
	 * 
	 * @return The {@link Customer} matching the passed name,
	 *         <code>aCustomerName</code>. If no {@link Customer} is found, then
	 *         this method returns <code>null</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Customer findCustomer(String aCustomerName);

}
