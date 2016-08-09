package backend;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frontend.LoginMenu;

/**
 * Class responsible for launching the Patient Management System.
 * @author Alessandro
 * @version 1.0
 */
public class Launcher {
	
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Initialise the JFrame containing the various JPanels responsible for the Patient Management System.
	 */
	public Launcher() {
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		
		LOGGER.log(Level.INFO, "Creating Application Frame");
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(contentPane);
		/* Initialises the Login menu of the application. */
		LOGGER.log(Level.INFO, "Initialising Login Menu");
		frame.getContentPane().add(new LoginMenu(frame));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Login");
		frame.setVisible(true);
	}
	
	/**
	 * Launch the Logger and the Patient Management System.
	 * @param args No arguments required by the Launcher.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoggerStartup();
					LOGGER.log(Level.INFO, "Launching Application");
					new Launcher();	
					LOGGER.log(Level.INFO, "Application Successfully Launched");
				} catch (Exception e) {
					LOGGER.log(Level.WARNING, "Error Encountered While Launching Application");
				}
			}
		});
	}

}