package net.cockamamy.fauxflix.util.loader;

import static java.lang.String.*;
import static net.cockamamy.fauxflix.util.FileUtilities.*;
import static net.cockamamy.fauxflix.util.StringUtilities.*;

import java.io.*;
import java.util.*;

import net.cockamamy.fauxflix.util.csv.*;
import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * Loads domain object from a CSV file.
 * 
 * @author jburwell
 * @param <T>
 * 
 * @since 1.0.0
 * 
 */
public abstract class AbstractDelimitedFileDataLoader<T> implements
		DataLoader<T>, ObjectFactory<T> {

	private final DelimitedFileReader<T> myReader;

	/**
	 * 
	 * @param theColumnDefinitions
	 *            The definition of each column in the CSV file provided in the
	 *            order they in the file.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public AbstractDelimitedFileDataLoader(File aDataSetDirectory,
			String aDataFilename, List<ColumnDefinition> theColumnDefinitions)
			throws FileNotFoundException {

		super();

		assert aDataSetDirectory != null : format(
				"%1$s(File, String, List) requires a non-null data set directory.",
				this.getClass().getName());
		assert isNotBlank(aDataFilename) == true : format(
				"%1$s(File, String, List) requires a non-blank data set directory.",
				this.getClass().getName());
		assert theColumnDefinitions != null && theColumnDefinitions.size() > 0 : format(
				"%1$s(File, String, List) requires a non-empty column definition list.",
				this.getClass().getName());

		this.myReader = new DelimitedFileReader<T>(openFile(aDataSetDirectory,
				aDataFilename), theColumnDefinitions, this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.DataLoader#loadData(java.io.File)
	 */
	public final Set<T> loadData() {

		return this.myReader.read();

	}

	/**
	 * 
	 * @return A description of the data loaded by this class.
	 * 
	 * @since 1.0.0
	 * 
	 */
	protected abstract String getDescription();

}
