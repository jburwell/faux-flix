/**
 * 
 * The application service model. A {@link net.cockamamy.fauxflix.service.Service} 
 * manages the state and operations on one or more domain objects.  Additionally, 
 * clients may subscribe to services for the notification of significant events in 
 * the {@link net.cockamamy.fauxflix.service.Service}'s operation through the 
 * {@link net.cockamamy.fauxflix.service.ServiceEventHandler} interface.
 * 
 * {@link net.cockamamy.fauxflix.service.Subsystem}s expose 
 * {@link net.cockamamy.fauxflix.service.Service}s and manage their lifecycle in 
 * the context of the application lifecycle.  
 * {@link net.cockamamy.fauxflix.service.Subsystem}s are realized as packages with 
 * public interfaces representing contained 
 * {@link net.cockamamy.fauxflix.service.Service}s and domain objects.  All 
 * {@link net.cockamamy.fauxflix.service.Service} and domain object implementations 
 * are declared with default visibility -- encapsulating the implementation of §service.
 * 
 * @since 1.0.0
 * 
 */
package net.cockamamy.fauxflix.service;