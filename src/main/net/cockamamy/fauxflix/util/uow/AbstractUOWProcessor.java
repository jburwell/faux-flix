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

import static java.util.Collections.*;
import static net.cockamamy.fauxflix.util.uow.Severity.*;

import java.util.*;

/**
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public abstract class AbstractUOWProcessor<C extends Command<C>, U extends UnitOfWork<C>>
		implements UOWProcessor<C, U> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.UOWProcessor#executeCommands()
	 */
	public final Result<C, U> executeCommands(U aUnitOfWork) {

		DefaultResult<C, U> aResult = new DefaultResult<C, U>(aUnitOfWork);

		Set<C> theCommands = aUnitOfWork.getCommands();

		if (theCommands.isEmpty() == false) {

			// TODO Convert to a logger warning ...
			System.err.println("No commands to execute.");

		}

		this.prepareCommandForExecution(aUnitOfWork, aResult);
		
		for (C aCommand : theCommands) {

			this.beforeCommandExecute(aUnitOfWork, aCommand);

			aResult.addMessages(aCommand.execute());

			if (aResult.isFailure()) {

				break;

			}

		}

		return aResult;

	}

	/**
	 *
	 * @param aUnitOfWork
	 * @param aResult
	 *
	 * @since 1.0.0
	 *
	 */
	protected void prepareCommandForExecution(U aUnitOfWork,
			Result<C, U> aResult) {

		// By default, do nothing ...
		
	}

	/**
	 * 
	 * @param aCommand
	 * 
	 * @since 1.0.0
	 * 
	 */
	protected void beforeCommandExecute(U aUnitOfWork, C aCommand) {

		// By default, do nothing ...

	}

	private final static class DefaultResult<C extends Command<C>, U extends UnitOfWork<C>>
			implements Result<C, U> {

		private final List<Message> myMessages;
		private final U myUnitOfWork;
		private boolean mySuccessFlag;

		public DefaultResult(U aUnitOfWork) {

			super();

			this.myMessages = new ArrayList<Message>();
			this.myUnitOfWork = aUnitOfWork;
			this.mySuccessFlag = false;

		}

		void addMessages(List<Message> theMessages) {

			for (Message aMessage : theMessages) {

				if (aMessage.getSeverity() == ERROR) {

					this.mySuccessFlag = false;

				}

				this.myMessages.add(aMessage);

			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.cockamamy.fauxflix.simulator.Result#getMessages()
		 */
		public List<Message> getMessages() {

			return unmodifiableList(this.myMessages);

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.cockamamy.fauxflix.simulator.Result#getUnitOfWork()
		 */
		public U getUnitOfWork() {

			return this.myUnitOfWork;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.cockamamy.fauxflix.simulator.Result#isFailure()
		 */
		public boolean isFailure() {

			return !this.mySuccessFlag;

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.cockamamy.fauxflix.simulator.Result#isSuccess()
		 */
		public boolean isSuccess() {

			return this.mySuccessFlag;

		}

	}

}
