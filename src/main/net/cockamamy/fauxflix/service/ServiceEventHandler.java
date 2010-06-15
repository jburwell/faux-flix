package net.cockamamy.fauxflix.service;

/**
 * 
 * Handles {@link ServiceEvent}s emitted by {@link Service} instances. Clients
 * needing notification of event occurrence must implement this interface and
 * register those implementations through the {@link Service} instance.
 * 
 * @author jburwell
 * 
 * @param <E>
 *            The type of event handled
 * 
 * @since 1.0.0
 * 
 */
public interface ServiceEventHandler<E extends ServiceEvent> {

	/**
	 * 
	 * @param anEvent
	 * 
	 * @since 1.0.0
	 * 
	 */
	void handleEvent(E anEvent);

}
