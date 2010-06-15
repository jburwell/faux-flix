package net.cockamamy.fauxflix.service.customer;

import java.util.*;

import net.cockamamy.fauxflix.service.*;

/**
 * 
 * {@link CustomerService} implementation that manages {@link Customer}s and
 * contains the repository of {@link Customer} instances.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class CustomerServiceImpl extends AbstractService<CustomerAddedEvent>
		implements CustomerService {

	private Map<String, Customer> myCustomers;

	/**
	 * 
	 * Default constructor
	 * 
	 * @since 1.0.0
	 * 
	 */
	public CustomerServiceImpl() {

		super();

		this.myCustomers = new HashMap<String, Customer>();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.customer.CustomerService#addCustomer(java
	 * .lang.String, net.cockamamy.fauxflix.service.customer.MembershipLevel)
	 */
	public Customer addCustomer(String aCustomerName,
			MembershipLevel aMembershipLevel) {

		Customer aCustomer = new CustomerImpl(aCustomerName, aMembershipLevel);

		this.myCustomers.put(aCustomer.getName(), aCustomer);

		this.notifyListeners(new CustomerAddedEvent(aCustomer));

		return aCustomer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.customer.CustomerService#findCustomer(
	 * java.lang.String)
	 */
	public Customer findCustomer(String aCustomerName) {

		return this.myCustomers.get(aCustomerName);

	}

}
