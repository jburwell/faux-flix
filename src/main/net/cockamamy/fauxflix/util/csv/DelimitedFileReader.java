/*
 * Copyright (c) 2008-2010, John Burwell
 * 
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are 
 * permitted provided that the following conditions are met:
 * 
 *    * Redistributions of source code must retain the above copyright notice, this list of 
 *      conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright notice, this list of 
 *      conditions and the following disclaimer in the documentation and/or other materials 
 *      provided with the distribution.
 *    * Neither the name of the John Burwell nor the names of its contributors may be used to 
 *      endorse or promote products derived from this software without specific prior written 
 *      permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY 
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package net.cockamamy.fauxflix.util.csv;

import static java.lang.String.*;
import static java.util.Collections.*;
import static net.cockamamy.fauxflix.util.FileUtilities.*;

import java.io.*;
import java.util.*;

import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class DelimitedFileReader<T> {

	private static final char DEFAULT_DELIMITER = ',';

	private final File myDelimitedFile;

	private final char myDelimiter;

	private final ObjectBuilder<T> myObjectBuilder;

	public DelimitedFileReader(File aDelimitedFile,
			List<ColumnDefinition> theColumnDefinitions,
			ObjectFactory<T> aFactory, char aDelimiter) {

		super();

		assert aDelimitedFile != null : format(
				"%1$s(File, List, ObjectFactory) requires a non-null input file.",
				this.getClass().getName());

		this.myDelimitedFile = aDelimitedFile;
		this.myDelimiter = aDelimiter;
		this.myObjectBuilder = new ObjectBuilder<T>(theColumnDefinitions,
				aFactory);

	}

	public DelimitedFileReader(File aDelimitedFile,
			List<ColumnDefinition> theColumnDefinitions,
			ObjectFactory<T> aFactory) {

		this(aDelimitedFile, theColumnDefinitions, aFactory, DEFAULT_DELIMITER);

	}

	public Set<T> read() {

		Set<T> theResults = new HashSet<T>();
		BufferedReader aReader = null;

		try {

			aReader = new BufferedReader(new FileReader(this.myDelimitedFile));

			while (aReader.ready()) {

				theResults.add(this.myObjectBuilder
						.buildObject(new DelimitedLine(aReader.readLine(),
								this.myDelimiter)));

			}

		} catch (IOException e) {

			throw new IllegalStateException(format(
					"Failed to read CSV file %1$s.", this.myDelimitedFile
							.getPath()), e);

		} finally {

			close(aReader);

		}

		return unmodifiableSet(theResults);

	}
}
