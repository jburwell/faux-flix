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

import java.util.*;

import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;
import net.cockamamy.fauxflix.util.uow.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface SimulatorCommand extends Command<SimulatorCommand> {

	/**
	 * 
	 * JavaBean-complaint name of the type property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String TYPE_PROP_NAME = "type";

	/**
	 * 
	 * JavaBean-complaint name of the occurred property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String OCCURRED_PROP_NAME = "occurred";

	/**
	 * 
	 * JavaBean-complaint name of the customer property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String CUSTOMER_PROP_NAME = "customer";

	/**
	 * 
	 * JavaBean-complaint name of the movie property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String MOVIE_PROP_NAME = "movie";

	/**
	 * 
	 * JavaBean-complaint name of the media type property
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static final String MEDIA_TYPE_PROP_NAME = "mediaType";

	/**
	 * 
	 * @return The type of command
	 * 
	 * @since 1.0.0
	 * 
	 */
	SimulatorCommandType getType();

	/**
	 * 
	 * @return The date on which the actions of the command will occur
	 * 
	 * @since 1.0.0
	 * 
	 */
	Date getOccurred();

	/**
	 * 
	 * @return The movie for which the command will be executed
	 * 
	 * @since 1.0.0
	 * 
	 */
	Movie getMovie();

	/**
	 * 
	 * @return The customer for which the command will be executed
	 * 
	 * @since 1.0.0
	 * 
	 */
	Customer getCustomer();

	/**
	 * 
	 * @return The media type of the movie for which this command will be
	 *         executed
	 * 
	 * @since 1.0.0
	 * 
	 */
	MediaType getMediaType();

}
