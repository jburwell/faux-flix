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

import static java.util.Collections.*;

import java.util.*;

import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * Builds a set of objects from a CSV file assuming that each row of the CSV
 * represents a single object instance and that the CSV file maps to one and
 * only object type.
 * 
 * @param <T>
 *            The type of object built by this builder
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */ 
final class ObjectBuilder<T> {

	private final List<ColumnDefinition> myColumnDefinitions;

	private final ObjectFactory<T> myFactory;

	/**
	 * 
	 * @param theColumnDefinitions
	 *            The list of column definitions for the CSV files that will be
	 *            read specified in order of occurrence in the file
	 * @param aFactory
	 *            A factory capable of building objects of type <code>T</code>
	 *            from a {@link Map} of property values.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public ObjectBuilder(List<ColumnDefinition> theColumnDefinitions,
			ObjectFactory<T> aFactory) {

		super();

		this.myColumnDefinitions = theColumnDefinitions;
		this.myFactory = aFactory;

	}

	public T buildObject(final DelimitedLine aLine) {

		final Map<String, Object> thePropertyValues = new HashMap<String, Object>();
		
		int aColumnNumber = 0;
		for (String aFieldValue : aLine) {

			ColumnDefinition aColumnDefinition = this.myColumnDefinitions
					.get(aColumnNumber);

			thePropertyValues.put(aColumnDefinition.getPropertyName(),
					aColumnDefinition.getConverter().convertValue(
							aFieldValue.trim()));

			aColumnNumber++;

		}

		// Build the object from the Map and add it to the results ...
		return this.myFactory.createObject(unmodifiableMap(thePropertyValues));

	}

}
