package net.cockamamy.fauxflix.util;

import java.util.*;

/**
 * 
 * Factory that creates date instances. It manages the notion of today's date in
 * the content of a simulation execution.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class DateFactory {

	private static final DateFactory SINGLETON = new DateFactory();

	/**
	 * 
	 * Obtains a <code>DateFactory</code> instance
	 * 
	 * @return A <code>DateFactory</code> instance
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static DateFactory getInstance() {

		return SINGLETON;

	}

	private Date myCurrentDate;

	private DateFactory() {

		super();

		this.myCurrentDate = new Date(System.currentTimeMillis());

	}

	/**
	 * 
	 * @return Today's date
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Date getToday() {

		return this.myCurrentDate;

	}

	/**
	 * 
	 * @param aCurrentDate The new value for today's date
	 * 
	 * @since 1.0.0
	 * 
	 */
	public void setToday(Date aCurrentDate) {

		this.myCurrentDate = aCurrentDate;

	}

}
