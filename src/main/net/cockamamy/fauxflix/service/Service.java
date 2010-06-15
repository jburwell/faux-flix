package net.cockamamy.fauxflix.service;

/**
 * 
 * A service which provides business operations to manage one or more domain
 * objects. Services also publish events to listeners -- notifying them of
 * significant changes in the state of the domain they manage.
 * 
 * @author jburwell
 * 
 * @param <E>
 *            The type of event emitted by this service
 * 
 * @since 1.0.0
 * 
 */
public interface Service<E extends ServiceEvent> {

	/**
	 * 
	 * @param anEventHandler
	 * 
	 * @since 1.0.0
	 * 
	 */
	public void registerEventHandler(ServiceEventHandler<E> anEventHandler);

}
