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

import static java.util.Calendar.*;
import static java.lang.String.*;

import java.util.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class SimulatorClock {

	private Date myCurrentDate;

	public SimulatorClock(final Date aStartDate) {

		super();

		assert aStartDate != null : format(
				"%1$s(Date) requires a non-null start date.", this.getClass()
						.getName());

		this.myCurrentDate = aStartDate;

	}

	public Date getToday() {

		return new Date(this.myCurrentDate.getTime());

	}

	public Date advance() {

		Calendar aCalendar = Calendar.getInstance();
		aCalendar.add(DAY_OF_MONTH, 1);
		this.myCurrentDate = aCalendar.getTime();

		return this.getToday();

	}

	@Override
	public boolean equals(Object thatObject) {

		if (thatObject != null
				&& this.getClass().equals(thatObject.getClass()) == true) {

			SimulatorClock thatClock = (SimulatorClock) thatObject;

			if (this.myCurrentDate.equals(thatClock.getToday()) == true) {

				return true;

			}

		}

		return false;

	}

	@Override
	public int hashCode() {

		return (37 * 17) + this.myCurrentDate.hashCode();

	}

	@Override
	public String toString() {

		return format("Simulator Clock (today: %1$D)", this.myCurrentDate);

	}

}
