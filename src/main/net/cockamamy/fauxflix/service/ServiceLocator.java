package net.cockamamy.fauxflix.service;

import java.util.*;

/**
 * 
 * Manages the registration and location of {@link Service} instances.
 * Implementations are declared through the
 * {@link Subsystem#getServiceMappings()} method.
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class ServiceLocator {

	private static ServiceRegistry SERVICE_REGISTRY = new ServiceRegistry();

	/**
	 * 
	 * Finds a {@link Service} instance for the passed {@link Service} type,
	 * <code>aServiceClass</code>.
	 * 
	 * @param <S>
	 *            The type of {@link Service} instance to find.
	 * 
	 * @param aServiceClass
	 *            The class of the service instance to find.
	 * 
	 * @return The service instance for the passed {@link Service} type,
	 *         <code>aServiceClass</code>.
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static <S extends Service<?>> S findService(Class<S> aServiceClass) {

		return SERVICE_REGISTRY.lookupService(aServiceClass);

	}

	/**
	 * 
	 * Registers the passed {@link Service} instance, <code>aService</code>, for
	 * the passed type, <code>aServiceClass</code>,
	 * 
	 * @param aServiceClass
	 *            The type of service to be registered
	 * @param aService
	 *            The instance to be registered
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static void registerService(
			Class<? extends Service<?>> aServiceClass, Service<?> aService) {

		SERVICE_REGISTRY.registerService(aServiceClass, aService);

	}

	private ServiceLocator() {

		super();

		// Enforce the utility class pattern ...

	}

	/**
	 * 
	 * Manages the mapping of {@link Service} instances to {@link Service}
	 * types.
	 * 
	 * @author jburwell
	 * 
	 * @since 1.0.0
	 * 
	 */
	private static final class ServiceRegistry {

		private Map<Class<? extends Service<?>>, Service<?>> myServiceRegistry;

		ServiceRegistry() {

			super();

			this.myServiceRegistry = new HashMap<Class<? extends Service<?>>, Service<?>>();

		}

		/**
		 * 
		 * Finds a {@link Service} instance for the passed type,
		 * <code>aServiceClass</code>, in the registry.
		 * 
		 * @param <S>
		 *            The type of {@link Service} to lookup
		 * 
		 * @param aServiceClass
		 *            The type of {@link Service} to lookup
		 * 
		 * @return The {@link Service} instance associated with the passed type,
		 *         <code>aServiceClass</code>.
		 * 
		 * @since 1.0.0
		 * 
		 */
		@SuppressWarnings("unchecked")
		<S extends Service> S lookupService(Class<S> aServiceClass) {

			return (S) this.myServiceRegistry.get(aServiceClass);

		}

		/**
		 * 
		 * Registers the passed {@link Service} instance, <code>aService</code>,
		 * with the passed {@link Service} type, <code>aServiceClass</code>.
		 * 
		 * @param aServiceClass
		 *            The type {@link Service} to register
		 * @param aService
		 *            The {@link Service} instance to associate with the passed
		 *            type, <code>aServiceClass</code>
		 * 
		 * @since 1.0.0
		 * 
		 */
		void registerService(Class<? extends Service<?>> aServiceClass,
				Service<?> aService) {

			this.myServiceRegistry.put(aServiceClass, aService);

		}

	}

}
