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
package net.cockamamy.fauxflix.service.inventory;

import static org.easymock.EasyMock.*;
import static net.cockamamy.fauxflix.util.CollectionUtilities.*;
import static net.cockamamy.fauxflix.util.StringUtilities.*;

import java.util.*;

import org.testng.annotations.*;

import net.cockamamy.fauxflix.util.mapper.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
@Test
public final class MoviePropertyConverterTest extends
		AbstractPropertyConverterTest<Movie, MoviePropertyConverter> {

	private static Set<String> TITLES = buildUnmodifiableSet(
			"The Great Escape", "The Dirty Dozen", "Kelly's Heros",
			"The Longest Day");

	private InventoryService myInventoryService;


	@Override
	protected void doBeforeSetup() {
		
		this.myInventoryService = createMock(InventoryService.class);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.util.mapper.AbstractPropertyConverterTest#
	 * buildConvertValueSuccessData()
	 */
	@Override
	protected Object[][] buildConvertValueSuccessData() {

		Object[][] theData = new Object[TITLES.size() + 2][2];
		
		int i = 0;
		for (String aTitle : TITLES) {

			Movie aMovie = new MovieImpl(aTitle);
			
			theData[i] = new Object[] { aTitle, aMovie };
			expect(this.myInventoryService.findMovie(aTitle)).andReturn(
					aMovie);
			
			i++;

		}

		theData[i] = new Object[] { null, null };
		theData[++i] = new Object[] { BLANK_STRING, null };
		
		replay(this.myInventoryService);

		return theData;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.util.mapper.AbstractPropertyConverterTest#
	 * createPropertyConverter()
	 */
	@Override
	protected MoviePropertyConverter createPropertyConverter() {

		return new MoviePropertyConverter(this.myInventoryService);

	}

}
