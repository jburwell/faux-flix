package net.cockamamy.fauxflix.service.rental;

import static java.lang.String.*;

import java.text.*;
import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;

/**
 * 
 * Implementation of the {@link Rental} domain object
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class RentalImpl implements Rental {

	private final Customer myCustomer;
	private final Movie myMovie;
	private final MediaType myMediaType;
	private final Date myRequestDate;
	private Date myReturnDate;
	private Date mySentDate;

	/**
	 * 
	 * @param aCustomer
	 *            The customer to whom the movie will be rented
	 * @param aMovie
	 *            The movie to be rented
	 * @param aMediaType
	 *            The media type to be rented
	 * @param aRequestDate
	 *            Tje date the rental was requested
	 * 
	 * @since 1.0.0
	 * 
	 */
	public RentalImpl(Customer aCustomer, Movie aMovie, MediaType aMediaType,
			Date aRequestDate) {

		super();

		assert aCustomer != null : format("%1$s requires a non-null customer.",
				this.getClass().getName());
		assert aMovie != null : format("%1$s requires a non-null movie.", this
				.getClass().getName());
		assert aMediaType != null : format(
				"%1$s requires a non-null media type.", this.getClass()
						.getName());
		assert aRequestDate != null : format(
				"%1$s requires a non-null request date.", this.getClass()
						.getName());

		this.myCustomer = aCustomer;
		this.myMovie = aMovie;
		this.myMediaType = aMediaType;
		this.myRequestDate = aRequestDate;

	}

	// BEGIN: Rental implementation
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.rental.Rental#getCustomer()
	 */
	public Customer getCustomer() {

		return this.myCustomer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.rental.Rental#getMediaType()
	 */
	public MediaType getMediaType() {

		return this.myMediaType;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.rental.Rental#getMovie()
	 */
	public Movie getMovie() {

		return this.myMovie;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.rental.Rental#getRequestDate()
	 */
	public Date getRequestDate() {

		return this.myRequestDate;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.rental.Rental#getReturnDate()
	 */
	public Date getReturnDate() {

		return this.myReturnDate;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.rental.Rental#setReturnDate(java.util.
	 * Date)
	 */
	public void setReturnDate(Date aReturnDate) {

		assert aReturnDate != null : "setReturnDate requires a non-null return date.";

		this.myReturnDate = aReturnDate;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.rental.Rental#getSentDate()
	 */
	public final Date getSentDate() {

		return this.mySentDate;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.rental.Rental#setSentDate(java.util.Date)
	 */
	public final void setSentDate(Date aSentDate) {

		assert aSentDate != null : "setSentDate requires a non-null sent date.";

		this.mySentDate = aSentDate;

	}

	// END: Rental implementation

	// BEGIN: Object implementation
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

			RentalImpl thatRental = (RentalImpl) thatObject;

			if (this.myCustomer.equals(thatRental.getCustomer()) == true
					&& this.myMediaType.equals(thatRental.getMediaType()) == true
					&& this.myMovie.equals(thatRental.getMovie()) == true
					&& this.myRequestDate.equals(thatRental.getRequestDate()) == true
					&& ((this.myReturnDate == thatRental.getReturnDate()) || (this.myReturnDate != null && this.myReturnDate
							.equals(thatRental.getReturnDate()) == true))
					&& ((this.mySentDate == thatRental.getSentDate()) || (this.mySentDate != null && this.mySentDate
							.equals(thatRental.getSentDate()) == true))) {

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

		aHashCode = (17 * aHashCode) + this.myCustomer.hashCode();
		aHashCode = (17 * aHashCode) + this.myMediaType.hashCode();
		aHashCode = (17 * aHashCode) + this.myMovie.hashCode();
		aHashCode = (17 * aHashCode) + this.myRequestDate.hashCode();
		aHashCode = (17 * aHashCode)
				+ (this.myReturnDate != null ? this.myReturnDate.hashCode() : 0);
		aHashCode = (17 * aHashCode)
				+ (this.mySentDate != null ? this.mySentDate.hashCode() : 0);

		return aHashCode;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		DateFormat aDateFormatter = DateFormat
				.getDateInstance(DateFormat.SHORT);

		return format(
				"Rental: (customer: %1$s, mediaType: %2s, movie: %3$s, requestDate: %4$s, sentDate: %5$s, returnDate: %6$s",
				this.myCustomer, this.myMediaType, this.myMovie,
				this.myRequestDate != null ? aDateFormatter
						.format(this.myRequestDate) : "",
				this.mySentDate != null ? aDateFormatter
						.format(this.mySentDate) : "",
				this.myReturnDate != null ? aDateFormatter
						.format(this.myReturnDate) : "");

	}

	// END: Object implementation

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.util.Describable#describe()
	 */
	public String describe() {

		return format("Movie \"%1$s\" (%2$s)", this.myMovie.describe(),
				this.myMediaType.describe());

	}

}
