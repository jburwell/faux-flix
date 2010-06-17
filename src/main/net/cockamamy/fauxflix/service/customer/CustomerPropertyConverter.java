package net.cockamamy.fauxflix.service.customer;

import static net.cockamamy.fauxflix.util.StringUtilities.*;
import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * Converts a string value into a {@link Customer} object
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class CustomerPropertyConverter implements
		PropertyConverter<Customer> {

	private CustomerService myCustomerService;

	public CustomerPropertyConverter(CustomerService aCustomerService) {
		
		super();
		
		this.myCustomerService = aCustomerService; 
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.PropertyConverter#convertValue(java.lang
	 * .String)
	 */
	public Customer convertValue(String aValue) {

		Customer aCustomer = null;

		// TODO Push this rule down into the service ...
		if (isNotBlank(aValue) == true) {

			aCustomer = this.myCustomerService.findCustomer(aValue);

		}

		return aCustomer;

	}

}
