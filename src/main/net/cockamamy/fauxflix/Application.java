package net.cockamamy.fauxflix;

import static java.lang.String.*;
import static net.cockamamy.fauxflix.service.ServiceLocator.*;
import static net.cockamamy.fauxflix.util.CollectionUtilities.*;

import java.io.*;
import java.util.*;

import net.cockamamy.fauxflix.service.*;
import net.cockamamy.fauxflix.service.customer.*;
import net.cockamamy.fauxflix.service.inventory.*;
import net.cockamamy.fauxflix.service.rental.*;
import net.cockamamy.fauxflix.simulator.*;

/**
 * 
 * Entry point and bootstrap for the Faux Flix rental store simulator
 * 
 * @author jburwell
 * 
 * @since 1.0.0
 * 
 */
public final class Application implements Runnable {

	private static final List<Subsystem> SUBSYSTEMS = buildUnmodifiableList(
			new CustomerSubsystem(), new InventorySubsystem(),
			new RentalSubsystem());

	private final File myDataSetDirectory;

	private UnitOfWork myUOWProcessor;

	/**
	 * 
	 * @param aCustomerDataFile
	 *            A reference to the CSV file containing the customer data for
	 *            the simulation
	 * @param aMovieDataFile
	 *            A reference to the CSV file containing the movie catalog data
	 *            for the simulation
	 * @param anInventoryDataFile
	 *            A reference to the CSV file containing the inventory data for
	 *            the simulation
	 * @param aCommandDataFile
	 *            A reference to the CSV file containing the simulator command
	 *            data for the simulation
	 * 
	 * @since 1.0.0
	 * 
	 */
	public Application(File aDataSetDirectory) {

		super();

		assert aDataSetDirectory != null : format(
				"%1$s requires a non-null data set directory", this.getClass()
						.getName());

		this.myDataSetDirectory = aDataSetDirectory;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

		try {

			// Spin up the subsystems ...
			this.start();

			// Execute the simulation ...
			this.myUOWProcessor.executeCommands();

		} catch (Exception e) {

			// TODO Log the exception ...

		} finally {

			// Shutdown the subsystems ...
			this.stop();

		}

	}

	private void start() throws Exception {

		// Map services into the service locator ...
		for (Subsystem aSubsystem : SUBSYSTEMS) {

			Map<Class<? extends Service<?>>, ? extends Service<?>> theMappings = aSubsystem
					.getServiceMappings();
			for (Map.Entry<Class<? extends Service<?>>, ? extends Service<?>> anEntry : theMappings
					.entrySet()) {

				ServiceLocator.registerService(anEntry.getKey(), anEntry
						.getValue());

			}

		}

		for (Subsystem aSubsystem : SUBSYSTEMS) {

			aSubsystem.startService();

		}

		new CustomerDataLoader(findService(CustomerService.class),
				this.myDataSetDirectory).loadData();

		new MovieDataLoader(findService(InventoryService.class),
				this.myDataSetDirectory).loadData();

		new InventoryDataLoader(findService(InventoryService.class),
				this.myDataSetDirectory).loadData();

		this.myUOWProcessor = new DefaultUnitOfWork(new CommandDataLoader(
				this.myDataSetDirectory).loadData());

	}

	private void stop() {

		for (Subsystem aSubsystem : SUBSYSTEMS) {

			aSubsystem.stopService();

		}

	}

	/**
	 * 
	 * Entry point for the Faux Flix movie rental store that supports a single
	 * command line option -- name of the directory containing the name of the
	 * data set directory.
	 * 
	 * @param theArguments
	 *            The command line arguments
	 * 
	 * @since 1.0.0
	 * 
	 */
	public static void main(String[] theArguments) {

		try {

			if (theArguments.length != 1) {

				throw new IllegalArgumentException(
						"A data set directory must be specified as only parameter.");

			}

			File aDataSetDirectory = new File(theArguments[0]);

			if (aDataSetDirectory.exists() == false) {

				throw new IllegalArgumentException(format(
						"%1$s data set directory does not exist.",
						aDataSetDirectory.getName()));

			}

			if (aDataSetDirectory.isDirectory() == false) {

				throw new IllegalStateException(format(
						"%1$s is not a vaid directory", aDataSetDirectory
								.getName()));

			}

			Application anApplication = new Application(aDataSetDirectory);
			anApplication.run();

		} catch (Exception e) {

			System.out.println("Simulation failed due to exception " + e);

		}

	}

}