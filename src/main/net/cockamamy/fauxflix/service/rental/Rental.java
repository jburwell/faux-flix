package net.cockamamy.fauxflix.service.rental;

import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;

/**
 * 
 * A rental of the movie and media type to a customer
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface Rental {

	/**
	 * 
	 * @return The customer to whom the movie is rented
	 * 
	 * @since 1.0.0
	 * 
	 */
	Customer getCustomer();

	/**
	 * 
	 * @return The movie rented
	 * 
	 * @since 1.0.0
	 * 
	 */
	Movie getMovie();

	/**
	 * 
	 * @return The media type rented
	 * 
	 * @since 1.0.0
	 * 
	 */
	MediaType getMediaType();

	/**
	 * 
	 * @return The date the customer requested the movie rental
	 * 
	 * @since 1.0.0
	 * 
	 */
	Date getRequestDate();

	/**
	 * 
	 * @return The date the rental was sent to the customer. If the rental has
	 *         not been sent to the customer, then this method will return
	 *         <code>null</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Date getSentDate();

	/**
	 * 
	 * @param aSentDate
	 *            The new value for the date the rental was sent to the
	 *            customer.
	 * 
	 * @since 1.0.0
	 * 
	 */
	void setSentDate(Date aSentDate);

	/**
	 * 
	 * @return The date the customer returned the rental to the store. If the
	 *         movie has not been returned, then this method will return
	 *         <code>null</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Date getReturnDate();

	/**
	 * 
	 * @param aReturnDate
	 *            The new value for the date the rental was returned to the
	 *            store.
	 * 
	 * @since 1.0.0
	 * 
	 */
	void setReturnDate(Date aReturnDate);
	
}
