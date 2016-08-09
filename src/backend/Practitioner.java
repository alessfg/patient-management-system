package backend;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class representing the user/practitioner logging to the Patient Management System.
 * @author Alessandro
 * @version 1.0
 */
public class Practitioner {

	private String username;
	private char[] password;
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Initialises a new practitioner with default values for username and password.
	 */
	public Practitioner() {
		LOGGER.log(Level.INFO, "Creating New Practitioner");
		this.username = "admin";
		this.password = new char[]{ '1', '2', '3', '4'};
	}

	/**
	 * Gets the practitioner's username.
	 * @return The practitioner's username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the practitioner's password.
	 * @return The practitioner's password.
	 */
	public char[] getPassword() {
		return password;
	}
	
}