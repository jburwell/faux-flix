package net.cockamamy.fauxflix.service.customer;

import static net.cockamamy.fauxflix.util.StringUtilities.*;
import static java.lang.String.*;

/**
 * 
 * An immutable {@link Customer} implementation
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class CustomerImpl implements Customer {

	private final String myName;
	private final MembershipLevel myMembershipLevel;

	/**
	 * 
	 * @param aName
	 *            The customer name
	 * @param aMembershipLevel
	 *            The membership level
	 * 
	 * @since 1.0.0
	 * 
	 */
	public CustomerImpl(String aName, MembershipLevel aMembershipLevel) {

		super();

		assert isNotBlank(aName) == true : format(
				"%1$s requires a non-blank customer name", this.getClass()
						.getName());
		assert aMembershipLevel != null : format(
				"%1$s requires a non-null membership level", this.getClass()
						.getName());

		this.myName = aName;
		this.myMembershipLevel = aMembershipLevel;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.customer.Customer#getMembershipLevel()
	 */
	public MembershipLevel getMembershipLevel() {

		return this.myMembershipLevel;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.customer.Customer#getName()
	 */
	public String getName() {

		return this.myName;

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

			CustomerImpl thatCustomer = (CustomerImpl) thatObject;

			if (this.myMembershipLevel
					.equals(thatCustomer.getMembershipLevel()) == true
					&& this.myName.equals(thatCustomer.getName()) == true) {

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

		aHashCode = (17 * aHashCode) + this.myMembershipLevel.hashCode();
		aHashCode = (17 * aHashCode) + this.myName.hashCode();

		return aHashCode;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return format("Customer [membershipLevel: %1$s, name: %2$s]",
				this.myMembershipLevel, this.myName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Customer thatCustomer) {

		assert thatCustomer != null;

		return this.myName.compareTo(thatCustomer.getName());

	}

	/* (non-Javadoc)
	 * @see net.cockamamy.fauxflix.util.Describable#describe()
	 */
	public String describe() {
		
		return this.myName;
		
	}

}
