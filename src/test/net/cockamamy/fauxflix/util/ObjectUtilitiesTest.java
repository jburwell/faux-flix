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
package net.cockamamy.fauxflix.util;

import static net.cockamamy.fauxflix.util.ObjectUtilities.*;
import static org.testng.Assert.*;

import org.testng.annotations.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
@Test
public final class ObjectUtilitiesTest {

	private static final String IS_EQUAL_TO_PROVIDER = "is_equal_to_provider";

	@Test(dataProvider = IS_EQUAL_TO_PROVIDER)
	public void testIsEqualTo(Object anObject, Object thatObject,
			boolean anExpectedValue) {

		assertEquals(isEqualTo(anObject, thatObject), anExpectedValue);

	}

	@DataProvider(name = IS_EQUAL_TO_PROVIDER)
	public Object[][] provideIsEqualToData()
	{
		
		return new Object[][] {
		
				{"foo", "foo", true},
				{"foo", "bar", false},
				{null, null, true},
				{"foo", null, false},
				{null, "bar", false},
				{Long.valueOf(1L), Long.valueOf(1L), true},
				{Long.valueOf(1L), Long.valueOf(2L), false},
				{Long.valueOf(1L), null, false},				
				{null, Long.valueOf(2L), false}
				
		};
		
	}
	
}
