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

	private final RentalService myRentalService;

	StockChangedEventHandler(RentalService aRentalService) {

		super();

		this.myRentalService = aRentalService;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.ServiceEventHandler#handleEvent(net.cockamamy
	 * .fauxflix.service.ServiceEvent)
	 */
	public void handleEvent(StockChangedEvent anEvent) {

		switch (anEvent.getChangeType()) {

		case ADDED:

			this.myRentalService.sendMovie(anEvent.getMovie());
			break;

		case CREATED:

			this.myRentalService.createMovieQueues(anEvent.getMovie());
			break;

		// TODO Add a default case to debug log skipped type ...

		}

	}

}
