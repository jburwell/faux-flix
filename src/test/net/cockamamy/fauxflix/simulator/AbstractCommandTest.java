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
package net.cockamamy.fauxflix.simulator;

import static net.cockamamy.fauxflix.service.inventory.MediaType.*;
import static org.easymock.EasyMock.*;
import static org.testng.Assert.*;

import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;
import net.cockamamy.fauxflix.service.rental.*;

import org.testng.annotations.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public abstract class AbstractCommandTest {

	@Test
	public final void testExecute() {

		Date anOccurred = new Date();

		Customer aCustomer = createMock(Customer.class);
		Movie aMovie = createMock(Movie.class);

		Rental aRental = createMock(Rental.class);
		RentalService aRentalService = createMock(RentalService.class);

		this.configureRentalService(aRentalService, aCustomer, aMovie, aRental,
				anOccurred);

		replay(aCustomer);
		replay(aMovie);
		replay(aRental);
		replay(aRentalService);

		Command aCommand = this.getCommandType().createCommand(anOccurred,
				aCustomer, aMovie, DVD, aRentalService);

		assertNotNull(aCommand);
		assertEquals(aCommand.getType(), this.getCommandType());
		assertEquals(aCommand.execute(), this.getExpectedResult());

	}

	protected abstract String getExpectedResult();

	protected abstract void configureRentalService(
			RentalService aRentalService, Customer aCustomer, Movie aMovie,
			Rental aRental, Date anOccurred);

	protected abstract CommandType getCommandType();

}
