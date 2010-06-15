package net.cockamamy.fauxflix.simulator;

import static java.lang.String.*;
import static net.cockamamy.fauxflix.service.ServiceLocator.*;
import static net.cockamamy.fauxflix.util.StringUtilities.*;

import java.text.*;
import java.util.*;

import net.cockamamy.fauxflix.service.rental.*;
import net.cockamamy.fauxflix.util.*;

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
public final class DefaultUnitOfWork implements UnitOfWork {

	private static final DateFormat DATE_FORMATTER = DateFormat
			.getDateInstance(DateFormat.LONG);

	private final SortedSet<Command> myCommands;
	private final RentalService myRentalService;
	private final DateFactory myDateFactory;

	/**
	 * 
	 * @param theCommands
	 *            The commands to be executed
	 * 
	 * @since 1.0.0
	 * 
	 */
	public DefaultUnitOfWork(Set<Command> theCommands) {

		super();

		assert theCommands != null;

		this.myCommands = new TreeSet<Command>(new CommandComparator());
		boolean aResult = this.myCommands.addAll(theCommands);

		if (aResult == false) {

			throw new IllegalStateException(
					"Failed to add all commands to the UOW processor.");

		}

		assert (this.myCommands.size() == theCommands.size());

		for (Command aCommand : theCommands) {

			this.myCommands.addAll(aCommand.getAdditionalCommands());

		}

		this.myDateFactory = DateFactory.getInstance();

		this.myRentalService = findService(RentalService.class);
		this.myRentalService
				.registerEventHandler(new RentalChangedEventHandler());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.simulator.UOWProcessor#executeCommands()
	 */
	public void executeCommands() {

		if (this.myCommands.isEmpty() == false) {

			for (Command aCommand : this.myCommands) {

				if (aCommand.getOccurred()
						.equals(this.myDateFactory.getToday()) == false) {

					processOverdueRentals();
					this.myDateFactory.setToday(aCommand.getOccurred());
					System.out.println(format("%1$s:", DATE_FORMATTER
							.format(this.myDateFactory.getToday())));

				}

				String aMessage = aCommand.execute();

				if (isNotBlank(aMessage) == true) {

					System.out.println(aMessage);

				}

			}

		} else {

			System.err.println("No commands to execute.");

		}

	}

	private void processOverdueRentals() {

		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(DateFactory.getInstance().getToday());
		aCalendar.roll(Calendar.DAY_OF_MONTH, -2);
		Date aPastDueDate = aCalendar.getTime();

		Set<Rental> theRentals = this.myRentalService.findAllRentals();
		for (Rental aRental : theRentals) {

			if (aRental.getSentDate() != null
					&& aRental.getSentDate().before(aPastDueDate) == true) {

				System.out.println(format("%1$s's rental of %2$s is past due.",
						aRental.getCustomer().getName(), aRental.getMovie()
								.getTitle()));

			}

		}

	}

	private static class CommandComparator implements Comparator<Command> {

		public int compare(Command aCommand, Command thatCommand) {

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

	public final Set<Command> getCommands() {

		return this.myCommands;

	}

}
