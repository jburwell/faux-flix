package net.cockamamy.fauxflix.service.inventory;

import static java.lang.String.*;
import static net.cockamamy.fauxflix.util.StringUtilities.*;

/**
 * 
 * An immutable {@link Movie} implementation
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class MovieImpl implements Movie {

	private final String myTitle;

	/**
	 * 
	 * @param aTitle The title of the movie
	 * 
	 * @since 1.0.0
	 * 
	 */
	MovieImpl(String aTitle) {

		super();

		assert isNotBlank(aTitle) == true : format(
				"%1$s requires a non-blank title.", this.getClass().getName());

		this.myTitle = aTitle;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.inventory.Movie#getTitle()
	 */
	public String getTitle() {

		return this.myTitle;

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

			MovieImpl thatMovie = (MovieImpl) thatObject;

			if (this.myTitle.equals(thatMovie.getTitle()) == true) {

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

		aHashCode = (17 * aHashCode) + this.myTitle.hashCode();

		return aHashCode;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return format("Movie: %1$s", this.myTitle);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Movie thatMovie) {

		assert thatMovie != null;

		return this.myTitle.compareTo(thatMovie.getTitle());

	}

}
