package net.cockamamy.fauxflix.service.rental;

import static java.util.Collections.*;

import java.util.*;

import net.cockamamy.fauxflix.service.*;
import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;

/**
 * 
 * The rental subsystem that contains services and domain objects supporting the
 * rental of movies by customers of the Faux Flix rental store.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class RentalSubsystem implements Subsystem {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.Subsystem#getServiceMappings()
	 */
	public Map<Class<? extends Service<?>>, ? extends Service<?>> getServiceMappings() {

		Map<Class<? extends Service<?>>, Service<?>> theMappings = new HashMap<Class<? extends Service<?>>, Service<?>>();

		theMappings.put(RentalService.class, new RentalServiceImpl());

		return unmodifiableMap(theMappings);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.Subsystem#stopService()
	 */
	public void stopService() {

		// Nothing to do ...

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.Subsystem#startService()
	 */
	public void startService() {

		InventoryService anInventoryService = ServiceLocator
				.findService(InventoryService.class);
		anInventoryService.registerEventHandler(new StockChangedEventHandler());

		CustomerService aCustomerService = ServiceLocator
				.findService(CustomerService.class);
		aCustomerService.registerEventHandler(new CustomerAddedEventHandler());

	}

}
