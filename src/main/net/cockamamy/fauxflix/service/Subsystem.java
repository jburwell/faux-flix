package net.cockamamy.fauxflix.service;

import java.util.*;

/**
 * 
 * Defines the lifecycle of a subsystem or module in the application exposing
 * one or more {@link Service}s and one or more domain objects. A subsystem is
 * responsible for registering {@link Service} instances exposed by the
 * subsystem, initializing the state of the subsystem at application, and
 * ensuring that the state of subsystem has been properly disposed before
 * application shutdown.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public interface Subsystem {

	/**
	 * 
	 * Initializes the state of the subsystem at application startup
	 * 
	 * @since 1.0.0
	 * 
	 */
	void startService();

	/**
	 * 
	 * Destroys the state of the subsystem at application shutdown
	 * 
	 * @since 1.0.0
	 * 
	 */
	void stopService();

	/**
	 * 
	 * @return The mappings of {@link Service} types to {@link Service}
	 *         instances supported by this subsystem.
	 * 
	 * @since 1.0.0
	 * 
	 */
	Map<Class<? extends Service<?>>, ? extends Service<?>> getServiceMappings();

}
