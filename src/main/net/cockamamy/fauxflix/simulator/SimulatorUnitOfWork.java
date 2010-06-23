package net.cockamamy.fauxflix.simulator;

import java.util.*;

import net.cockamamy.fauxflix.util.uow.*;

/**
 * 
 * Default {@link UnitOfWork} implementation that executes {@link Command}s in
 * the order of their occurred date.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class SimulatorUnitOfWork implements UnitOfWork<SimulatorCommand> {

	private final Set<SimulatorCommand> myCommands;

	private final SimulatorClock myClock;

	/**
	 * 
	 * @param theCommands
	 *            The commands to be executed
	 * 
	 * @since 1.0.0
	 * 
	 */
	public SimulatorUnitOfWork(Set<SimulatorCommand> theCommands) {

		super();

		assert theCommands != null;

		this.myCommands = new TreeSet<SimulatorCommand>(
				new SimulatorCommandComparator());
		boolean aResult = this.myCommands.addAll(theCommands);

		if (aResult == false) {

			throw new IllegalStateException(
					"Failed to add all commands to the UOW processor.");

		}

		assert this.myCommands.size() == theCommands.size();

		// TODO Move into a recursive execution algorithm ...
		for (SimulatorCommand aCommand : theCommands) {

			this.myCommands.addAll(aCommand.getAdditionalCommands());

		}

		SimulatorCommand theFirstCommand = (SimulatorCommand) this.myCommands
				.toArray()[0];
		this.myClock = new SimulatorClock(theFirstCommand.getOccurred());
		
	}

	public final Set<SimulatorCommand> getCommands() {

		return this.myCommands;

	}

	private static class SimulatorCommandComparator implements
			Comparator<SimulatorCommand> {

		public int compare(SimulatorCommand aCommand,
				SimulatorCommand thatCommand) {

			assert aCommand != null;
			assert thatCommand != null;

			int aResult = aCommand.getOccurred().compareTo(
					thatCommand.getOccurred());

			if (aResult == 0) {

				aResult = aCommand.getType().compareTo(thatCommand.getType());

			}

			if (aResult == 0) {

				aResult = aCommand.getCustomer().compareTo(
						thatCommand.getCustomer());

			}

			if (aResult == 0) {

				aResult = aCommand.getMovie().compareTo(thatCommand.getMovie());

			}

			if (aResult == 0) {

				aResult = aCommand.getMediaType().compareTo(
						thatCommand.getMediaType());

			}

			return aResult;

		}

	}

	public SimulatorClock getClock() {

		return this.myClock;
	}

}
