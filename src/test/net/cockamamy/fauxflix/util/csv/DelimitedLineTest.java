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
import static org.testng.Assert.*;
import static net.cockamamy.fauxflix.util.CollectionUtilities.*;

import java.util.*;

import org.testng.annotations.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
@Test
public final class DelimitedLineTest {

	private static final String ITERATOR_PROVIDER = "iterator_provider";

	@Test(dataProvider = ITERATOR_PROVIDER)
	public void testIterator(DelimitedLine aLine, List<String> theColumnValues) {

		assertNotNull(aLine);
		assertNotNull(theColumnValues);

		int aColumn = 0;
		for (String aField : aLine) {

			assertEquals(aField, theColumnValues.get(aColumn));
			aColumn++;

		}

	}

	@DataProvider(name = ITERATOR_PROVIDER)
	public Object[][] provideIteratorData() {

		return new Object[][] {

			{ new DelimitedLine("1,2,3,4,5", ','),
					buildUnmodifiableList("1", "2", "3", "4", "5") },
			{ new DelimitedLine(null, ','), emptyList() }

		};

	}
}
