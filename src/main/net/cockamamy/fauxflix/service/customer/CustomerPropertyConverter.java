package net.cockamamy.fauxflix.service.customer;

import static net.cockamamy.fauxflix.service.ServiceLocator.*;
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.PropertyConverter#convertValue(java.lang
	 * .String)
	 */
	public Customer convertValue(String aValue) {

		Customer aCustomer = null;

		if (isNotBlank(aValue) == true) {

			CustomerService aCustomerService = findService(CustomerService.class);
			aCustomer = aCustomerService.findCustomer(aValue);

		}

		return aCustomer;

	}

}
