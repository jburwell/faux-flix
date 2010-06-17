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

import static net.cockamamy.fauxflix.simulator.CommandType.*;
import static net.cockamamy.fauxflix.service.inventory.MediaType.*;
import static org.easymock.EasyMock.*;

import java.util.*;

import org.testng.annotations.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;
import net.cockamamy.fauxflix.service.rental.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
@Test
public final class ReceiveMovieCommandTest extends
		AbstractCommandTest<ReceiveMovieCommand> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.AbstractCommandTest#buildCommands()
	 */
	@Override
	protected Object[][] buildCommands() {

		Date aReturnDate = new Date();

		Customer aCustomer = createMock(Customer.class);
		Movie aMovie = createMock(Movie.class);

		Rental aRental = createMock(Rental.class);
		RentalService aRentalService = createMock(RentalService.class);
		expect(aRentalService.findRental(aCustomer, aMovie, DVD)).andReturn(
				aRental);
		aRentalService.returnMovie(aRental, aReturnDate);

		replay(aCustomer);
		replay(aMovie);
		replay(aRental);
		replay(aRentalService);

		return new Object[][] {

			{ this.getCommandType().createCommand(aReturnDate, aCustomer,
				aMovie, DVD, aRentalService), null }

		};

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.simulator.AbstractCommandTest#getCommandType()
	 */
	@Override
	protected CommandType getCommandType() {

		return RECEIVES;

	}

}
