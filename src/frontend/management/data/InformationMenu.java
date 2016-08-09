package frontend.management.data;

import java.awt.BorderLayout;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import backend.LoggerStartup;
import backend.Patient;

/**
 * Creates the Information menu responsible for displaying all the data of the selected patient.
 * The Information menu contains a tabbed pane with each tab displaying different information.
 * @author Alessandro
 * @version 1.0
 */
public class InformationMenu extends JPanel {

	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Initialises the Information menu.
	 * @param patient The patient to load.
	 */
	public InformationMenu(Patient patient) {
		LOGGER.log(Level.INFO, "Initialising Information Menu");
		setLayout(new BorderLayout());
		JTabbedPane informationPanel = new JTabbedPane();
		informationPanel.addTab("Personal Details", new PersonalDetailsMenu(patient));
		informationPanel.addTab("Medical Details", new MedicalDetailsMenu(patient));
		informationPanel.addTab("Comments and Appointments", new CommentsAndAppointmentsMenu(patient));
		add(informationPanel);
	}
	
}