package net.cockamamy.fauxflix.service.inventory;

import static java.util.Collections.*;

import java.util.*;

import net.cockamamy.fauxflix.service.*;

/**
 * 
 * The inventory subsystem that contains the services and domain objects to
 * manage the physical inventory and movie catalog of the Faux Fix movie store.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class InventorySubsystem implements Subsystem {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.cockamamy.fauxflix.service.Subsystem#getServiceMappings()
	 */
	public Map<Class<? extends Service<?>>, Service<?>> getServiceMappings() {

		Map<Class<? extends Service<?>>, Service<?>> theMappings = new HashMap<Class<? extends Service<?>>, Service<?>>();

		theMappings.put(InventoryService.class, new InventoryServiceImpl());

		return unmodifiableMap(theMappings);

	}

}
