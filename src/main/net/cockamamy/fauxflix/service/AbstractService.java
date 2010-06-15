package net.cockamamy.fauxflix.service;

import java.util.*;

/**
 * 
 * Convenience base class of the {@link Service} interface which provides the
 * infrastructure for registering and notifying listeners of service events.
 * 
 * @author jburwell
 * 
 * @param <E>
 *            The type of event emitted by this {@link Service} implementation
 * 
 * @since 1.0.0
 * 
 */
public abstract class AbstractService<E extends ServiceEvent> implements
		Service<E> {

	private List<ServiceEventHandler<E>> myEventListeners;

	/**
	 * 
	 * Default constructor
	 * 
	 * @since 1.0.0
	 * 
	 */
	public AbstractService() {

		super();

		this.myEventListeners = new ArrayList<ServiceEventHandler<E>>();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cockamamy.fauxflix.service.Service#registerEventHandler(net.cockamamy
	 * .fauxflix.service.ServiceEventHandler)
	 */
	public final void registerEventHandler(ServiceEventHandler<E> anEventHandler) {

		this.myEventListeners.add(anEventHandler);

	}

	/**
	 * 
	 * Notifies listeners event listeners of the occurrence of the passed event,
	 * <code>anEvent</code>.
	 * 
	 * @param anEvent
	 *            The event whose occurrence to notify observers
	 * 
	 * @since 1.0.0
	 * 
	 */
	protected final void notifyListeners(E anEvent) {

		for (ServiceEventHandler<E> anEventHandler : this.myEventListeners) {

			anEventHandler.handleEvent(anEvent);

		}

	}

}
