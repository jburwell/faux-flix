package net.cockamamy.fauxflix.service.customer;

import net.cockamamy.fauxflix.util.*;

/**
 * 
 * A customer of the Faux Flix rental store.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface Customer extends Comparable<Customer>, Describable {

	/**
	 * 
	 * JavaBean-complaint name of the customer name property
	 * 
	 * @since 1.0.0
	 * 
	 */
	static final String NAME_PROP_NAME = "name";

	/**
	 * 
	 * JavaBean-complaint name of the membership level property
	 * 
	 * @since 1.0.0
	 * 
	 */
	static final String MEMBERSHIP_LEVEL_PROP_NAME = "membershipLevel";

	/**
	 * 
	 * @return The name of the customer
	 * 
	 * @since 1.0.0
	 * 
	 */
	String getName();

	/**
	 * 
	 * @return The membership level of the customer
	 * 
	 * @since 1.0.0
	 * 
	 */
	MembershipLevel getMembershipLevel();

}
