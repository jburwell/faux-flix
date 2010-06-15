package net.cockamamy.fauxflix.service.customer;

import static java.util.Collections.*;
import static net.cockamamy.fauxflix.service.customer.Customer.*;

import java.io.*;
import java.util.*;

import net.cockamamy.fauxflix.util.csv.*;
import net.cockamamy.fauxflix.util.loader.*;
import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * Loads customer data in the {@link CustomerService} from a CSV file with the
 * following columns: <li>
 * <ul>
 * Customer Name
 * </ul>
 * <ul>
 * {@link MembershipLevel}
 * </ul>
 * </li>
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class CustomerDataLoader extends
		AbstractDelimitedFileDataLoader<Customer> {

	private static final String CUSTOMER_DATA_FILE_NAME = "customers.csv";

	private final static List<ColumnDefinition> COLUMN_DEFINITIONS;
	static {

		List<ColumnDefinition> theDefinitions = new ArrayList<ColumnDefinition>();

		theDefinitions.add(new ColumnDefinition(NAME_PROP_NAME,
				new StringPropertyConverter()));
		theDefinitions.add(new ColumnDefinition(MEMBERSHIP_LEVEL_PROP_NAME,
				new EnumPropertyConverter<MembershipLevel>(
						MembershipLevel.class)));

		COLUMN_DEFINITIONS = unmodifiableList(theDefinitions);

	}

	private final CustomerService myCustomerService;

	/**
	 * 
	 * @param aCustomerService
	 *            The {@link CustomerService} into which to load data
	 * 
	 * @since 1.0.0
	 * 
	 */
	public CustomerDataLoader(CustomerService aCustomerService,
			File aDataSetDirectory) throws FileNotFoundException {

		super(aDataSetDirectory, CUSTOMER_DATA_FILE_NAME, COLUMN_DEFINITIONS);

		assert aCustomerService != null;

		this.myCustomerService = aCustomerService;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.AbstractCSVDataLoader#getDescription()
	 */
	@Override
	protected String getDescription() {

		return "Customer";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.ObjectFactory#createObject(java.util.Map)
	 */
	public Customer createObject(Map<String, Object> thePropertyValues) {

		assert thePropertyValues != null;

		return this.myCustomerService.addCustomer((String) thePropertyValues
				.get(NAME_PROP_NAME), (MembershipLevel) thePropertyValues
				.get(MEMBERSHIP_LEVEL_PROP_NAME));

	}

}
