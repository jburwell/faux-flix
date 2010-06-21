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
package net.cockamamy.fauxflix.util.uow;

import static java.lang.String.*;
import static net.cockamamy.fauxflix.util.ObjectUtilities.*;
import static net.cockamamy.fauxflix.util.StringUtilities.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class DefaultMessage implements Message {

	private final Severity mySeverity;
	private final String myMessage;

	public DefaultMessage(Severity aSeverity, String aMessage) {

		super();

		assert aSeverity != null : format(
				"%1$s(String) requires a non-null Severity", this.getClass()
						.getName());
		assert isNotBlank(aMessage) : format(
				"%1$s(String) requires a non-blank Message", this.getClass()
						.getName());

		this.mySeverity = aSeverity;
		this.myMessage = aMessage;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Message#getMessage()
	 */
	public String getMessage() {

		return this.myMessage;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.Message#getSeverity()
	 */
	public Severity getSeverity() {

		return this.mySeverity;

	}

	// BEGIN: Object implementation
	@Override
	public boolean equals(Object thatObject) {

		if (thatObject != null
				&& this.getClass().equals(thatObject.getClass()) == true) {

			Message thatMessage = (Message) thatObject;

			if (isEqualTo(this.myMessage, thatMessage.getMessage()) == true
					&& this.mySeverity == thatMessage.getSeverity()) {

				return true;

			}

		}

		return false;

	}

	@Override
	public int hashCode() {

		int aHashCode = 37;

		aHashCode += (aHashCode * 17) + this.myMessage.hashCode();
		aHashCode += (aHashCode * 17) + this.mySeverity.hashCode();

		return aHashCode;

	}

	@Override
	public String toString() {

		return format("Message (message: %1$s, severity: %2$s)",
				this.myMessage, this.mySeverity);

	}
	// END: Object implementation

}
