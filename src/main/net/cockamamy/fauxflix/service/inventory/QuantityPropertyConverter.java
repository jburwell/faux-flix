package net.cockamamy.fauxflix.service.inventory;

import static net.cockamamy.fauxflix.util.StringUtilities.*;
import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * Converts a string value to a {@link Quantity} object
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public class QuantityPropertyConverter implements PropertyConverter<Quantity> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.util.csv.PropertyConverter#convertValue(java.lang
	 * .String)
	 */
	public Quantity convertValue(String aValue) {

		Quantity aQuantity = null;

		try {

			if (isNotBlank(aValue) == true) {

				aQuantity = new Quantity(Long.decode(aValue));

			}

		} catch (NumberFormatException e) {

			throw new PropertyConversionException(aValue, Quantity.class, e);

		}

		return aQuantity;

	}

}
