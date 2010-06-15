package net.cockamamy.fauxflix.util;

import static java.util.Collections.*;

import java.util.*;

/**
 * 
 * Utilities for creating and manipulating Collections.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class CollectionUtilities {

	private CollectionUtilities() {

		super();

	}

	/**
	 * 
	 * Builds an unmodifiable {@link List} from the passed elements,
	 * <code>theElements</code>.
	 * 
	 * @param <T>
	 *            The element type
	 * 
	 * @param theElements
	 *            The elements from which to create the unmodifiable list
	 * 
	 * @return An unmodifiable {@link List} containing the passed elements,
	 *         <code>theElements</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static <T> List<T> buildUnmodifiableList(T... theElements) {

		if (theElements == null) {
			
			return emptyList();
			
		}

		List<T> aList = new ArrayList<T>(theElements.length);

		for (T anElement : theElements) {

			aList.add(anElement);

		}

		return unmodifiableList(aList);

	}

}
