package net.cockamamy.fauxflix.simulator;

import static java.lang.String.*;
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

	private final RentalService myRentalService;

	public RentalChangedEventHandler(RentalService aRentalService) {

		super();

		assert aRentalService != null : format(
				"%1$s(RentalService) requires a non-null %2$s instance.",
				RentalChangedEventHandler.class.getName(), RentalService.class
						.getName());

		this.myRentalService = aRentalService;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.ServiceEventHandler#handleEvent(net.cockamamy
	 * .fauxflix.service.ServiceEvent)
	 */
	public void handleEvent(RentalChangedEvent anEvent) {

		assert anEvent != null : format(
				"%1$s.handleEvent requires a non-null event",
				RentalChangedEvent.class.getName());
		assert anEvent.getChangedRental() != null : format(
				"%1$s.handleEvent requires a non-null changed rental.",
				RentalChangedEvent.class.getName());
		assert anEvent.getReason() != null : format(
				"%1$s.handleEvent requires a non-null reason.",
				RentalChangedEvent.class.getName());

		String aMessage = anEvent.getReason().describeAction(
				anEvent.getChangedRental(), this.myRentalService);

		if (isNotBlank(aMessage) == true) {

			System.out.println(aMessage);

		}
		
	}
}
