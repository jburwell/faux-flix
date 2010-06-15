package net.cockamamy.fauxflix.util.mapper;

import static net.cockamamy.fauxflix.util.StringUtilities.*;

import java.text.*;
import java.util.*;

/**
 * 
 * Converts a string in the form of MM/dd/yyyy to a {@link Date} object
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class DatePropertyConverter implements PropertyConverter<Date> {

	private static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

	private final DateFormat myDateFormatter;

	/**
	 * 
	 * Default constructor
	 * 
	 * @since 1.0.0
	 * 
	 */
	public DatePropertyConverter() {

		super();

		this.myDateFormatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.PropertyConverter#convertValue(java.lang
	 * .String)
	 */
	public Date convertValue(String aValue) {

		Date aConvertedValue = null;

		try {

			if (isNotBlank(aValue) == true) {

				aConvertedValue = this.myDateFormatter.parse(aValue);

			}

		} catch (ParseException e) {

			throw new PropertyConversionException(aValue, Date.class, e);

		}

		return aConvertedValue;

	}

}
