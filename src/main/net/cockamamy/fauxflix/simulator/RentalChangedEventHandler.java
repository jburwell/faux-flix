package net.cockamamy.fauxflix.simulator;

import static java.lang.String.*;
import static net.cockamamy.fauxflix.service.ServiceLocator.*;
import static net.cockamamy.fauxflix.util.StringUtilities.*;
import net.cockamamy.fauxflix.service.*;
import net.cockamamy.fauxflix.service.rental.*;

/**
 * 
 * Handles {@link RentalChangedEvent} events emitted by the
 * {@link RentalService} and display the appropriate messages to the console --
 * describing the activities of the simulation.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class RentalChangedEventHandler implements
		ServiceEventHandler<RentalChangedEvent> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.ServiceEventHandler#handleEvent(net.cockamamy
	 * .fauxflix.service.ServiceEvent)
	 */
	public void handleEvent(RentalChangedEvent anEvent) {

		assert anEvent != null && anEvent.getChangedRental() != null : format("%1$s.handle event requires a non-null event with a non-null changed rental.");

		Rental theChangedRental = anEvent.getChangedRental();

		String aMessage = null;
		switch (anEvent.getReason()) {

		case SENT:
			aMessage = format("Store mails \"%1$s\" (%2$s) to %3$s",
					theChangedRental.getMovie().getTitle(), theChangedRental
							.getMediaType().name().toLowerCase(),
					theChangedRental.getCustomer().getName());
			break;

		case QUEUED_OUT_OF_STOCK:
			aMessage = format(
					"Movie \"%1$s\" (%2$s) is out of stock; %3$s's request was added to the queue.",
					theChangedRental.getMovie().getTitle(), theChangedRental
							.getMediaType().name().toLowerCase(),
					theChangedRental.getCustomer().getName());
			break;

		case QUEUED_LIMITED_EXCEEDED:
			aMessage = format(
					"%1$s has exceed their rental limit; %1$s's request has been added to their rental queue.",
					theChangedRental.getCustomer().getName());
			break;

		case REQUESTED:
			aMessage = format("%1$s requests \"%2$s\" (%3$s)", theChangedRental
					.getCustomer().getName(), theChangedRental.getMovie()
					.getTitle(), theChangedRental.getMediaType().name()
					.toLowerCase());

			break;

		case RETURNED:

			RentalService aRentalService = findService(RentalService.class);
			aMessage = format(
					"%1$s's \"%2$s\" returned (%3$s) to stock; %1$s has %4$s movies still rented.",
					theChangedRental.getCustomer().getName(), theChangedRental
							.getMovie().getTitle(), theChangedRental
							.getMediaType().name().toLowerCase(),
					aRentalService.findRentals(theChangedRental.getCustomer())
							.size());

			break;

		}

		if (isNotBlank(aMessage) == true) {

			System.out.println(aMessage);

		}

	}

}
