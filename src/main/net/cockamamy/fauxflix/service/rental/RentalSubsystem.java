package net.cockamamy.fauxflix.service.rental;

import static java.lang.String.*;
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

	private final InventoryService myInventoryService;
	private final CustomerService myCustomerService;
	private final RentalServiceImpl myRentalService;

	public RentalSubsystem(InventoryService anInventoryService,
			CustomerService aCustomerService) {

		super();

		assert anInventoryService != null : format(
				"%1$s(InventoryService, CustomerService) requires a non-null InventoryService reference.",
				this.getClass().getName());
		assert aCustomerService != null : format(
				"%1$s(InventoryService, CustomerService) requires a non-null CustomerService reference.",
				this.getClass().getName());

		this.myInventoryService = anInventoryService;
		this.myCustomerService = aCustomerService;
		this.myRentalService = new RentalServiceImpl(this.myInventoryService);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.Subsystem#getServiceMappings()
	 */
	public Map<Class<? extends Service<?>>, ? extends Service<?>> getServiceMappings() {

		Map<Class<? extends Service<?>>, Service<?>> theMappings = new HashMap<Class<? extends Service<?>>, Service<?>>();

		theMappings.put(RentalService.class, this.myRentalService);

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

		this.myInventoryService
				.registerEventHandler(new StockChangedEventHandler(
						this.myRentalService));

		this.myCustomerService
				.registerEventHandler(new CustomerAddedEventHandler(
						this.myRentalService));

	}

}
