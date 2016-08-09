package backend;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Initialises a new Logger to be used by Patient Management System.
 * @author Alessandro
 * @version 1.0
 */
public class LoggerStartup {
	
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Initialises the logger.
	 */
	public LoggerStartup() {
		try {
			FileHandler fileTxt = new FileHandler("Logging.log");
			SimpleFormatter formatterTxt = new SimpleFormatter();
			fileTxt.setFormatter(formatterTxt);
			LOGGER.addHandler(fileTxt);
			LOGGER.setUseParentHandlers(false);
			LOGGER.setLevel(Level.ALL);
			LOGGER.log(Level.INFO, "Initialising Logger");
			Runtime.getRuntime().addShutdownHook(new Thread(){
				/* Closes the logger upon closing the application. */
				@Override
				public void run() {
					fileTxt.close();
				}
				
			});
		}
		catch (Exception e) {
			LOGGER.log(Level.WARNING, "Error Encountered While Initialising Logger");
		}
		
	}
	
}