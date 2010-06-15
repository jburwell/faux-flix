package net.cockamamy.fauxflix.service.customer;

import static java.util.Collections.*;

import java.util.*;

import net.cockamamy.fauxflix.service.*;

/**
 * 
 * The customer subsystem that contains the services and domain objects for
 * managing Faux Flix rental store customer information.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class CustomerSubsystem implements Subsystem {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.Subsystem#getServiceMappings()
	 */
	public Map<Class<? extends Service<?>>, ? extends Service<?>> getServiceMappings() {

		Map<Class<? extends Service<?>>, Service<?>> theMappings = new HashMap<Class<? extends Service<?>>, Service<?>>();

		theMappings.put(CustomerService.class, new CustomerServiceImpl());

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

		// Nothing to do ...

	}

}
