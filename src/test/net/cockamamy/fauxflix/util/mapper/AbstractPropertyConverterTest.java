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
package net.cockamamy.fauxflix.util.mapper;

import static org.testng.Assert.*;

import org.testng.annotations.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public abstract class AbstractPropertyConverterTest<T, P extends PropertyConverter<T>> {

	private static final String CONVERT_VALUE_SUCCESS_PROVIDER = "convert_value_success_provider";

	private static final String CONVERT_VALUE_FAILURE_PROVIDER = "convert_value_failure_provider";

	private P myPropertyConverter = null;

	@BeforeClass
	public final void setup() {

		this.doBeforeSetup();

		this.myPropertyConverter = this.createPropertyConverter();

		assert this.myPropertyConverter != null;
		
	}

	protected void doBeforeSetup() {
		
		// Do nothing by default
		
	}

	@Test(dataProvider = CONVERT_VALUE_SUCCESS_PROVIDER)
	public final void testConvertValueSuccess(String aValue, T aResult) {

		assertEquals(this.myPropertyConverter.convertValue(aValue), aResult);

	}

	@Test(dataProvider = CONVERT_VALUE_FAILURE_PROVIDER, expectedExceptions = PropertyConversionException.class)
	public final void testConvertValueFailure(String aValue) {

		this.myPropertyConverter.convertValue(aValue);

	}

	protected abstract P createPropertyConverter();

	@DataProvider(name = CONVERT_VALUE_SUCCESS_PROVIDER)
	public final Object[][] provideConvertValueSuccessData() {

		return buildConvertValueSuccessData();

	}

	@DataProvider(name = CONVERT_VALUE_FAILURE_PROVIDER)
	public final Object[][] provideConvertValueFailureData() {

		return buildConvertValueFailureData();

	}

	/**
	 * 
	 * @return
	 * 
	 * @since 1.0.0
	 * 
	 */
	protected Object[][] buildConvertValueFailureData() {

		return new Object[][] {};

	}

	/**
	 * 
	 * @return
	 * 
	 * @since 1.0.0
	 * 
	 */
	protected abstract Object[][] buildConvertValueSuccessData();

}
