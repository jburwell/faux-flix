package net.cockamamy.fauxflix.service.rental;

import net.cockamamy.fauxflix.service.*;
import net.cockamamy.fauxflix.service.inventory.*;

/**
 * 
 * Handles {@link StockChangedEvent} events emitted by the
 * {@link InventoryService} by sending the next rental in the queue for a movie
 * returning to stock or creating movie queues when a new movie is added to the
 * catalog.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class StockChangedEventHandler implements
		ServiceEventHandler<StockChangedEvent> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.ServiceEventHandler#handleEvent(net.cockamamy
	 * .fauxflix.service.ServiceEvent)
	 */
	public void handleEvent(StockChangedEvent anEvent) {

		RentalService aRentalService = ServiceLocator
				.findService(RentalService.class);

		switch (anEvent.getChangeType()) {

		case ADDED:

			aRentalService.sendMovie(anEvent.getMovie());
			break;

		case CREATED:

			aRentalService.createMovieQueues(anEvent.getMovie());
			break;

		}

	}

}
