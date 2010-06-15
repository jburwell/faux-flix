package net.cockamamy.fauxflix.service.rental;

import net.cockamamy.fauxflix.service.*;
import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;

/**
 * 
 * Handles {@link CustomerAddedEvent} events emitted by the
 * {@link InventoryService} by creating the queues used by the
 * {@link RentalService} for a {@link Customer}.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
final class CustomerAddedEventHandler implements
		ServiceEventHandler<CustomerAddedEvent> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.ServiceEventHandler#handleEvent(net.cockamamy
	 * .fauxflix.service.ServiceEvent)
	 */
	public void handleEvent(CustomerAddedEvent anEvent) {

		RentalService aRentalService = ServiceLocator
				.findService(RentalService.class);
		aRentalService.createCustomerQueues(anEvent.getAddedCustomer());

	}

}
