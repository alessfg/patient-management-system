package frontend.management;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import backend.LoggerStartup;
import backend.Patient;
import frontend.management.data.InformationMenu;

/**
 * Creates the Patient menu where all the patients contained within the Patient Management System can be seen.
 * The Patient menu is composed of a split pane. The left pane contains a list of patients; the right pane contains a tabbed pane with the information of the selected patient.
 * @author Alessandro
 * @version 1.0
 */
public class PatientMenu extends JPanel {
		
	private ArrayList<Patient> patientList;
	private DefaultListModel<Patient> patientJListModel;
	private JList<Patient> patientJList;
	private JPanel listPanel;
	private JPanel patientPanel;
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Initialises the Patient menu.
	 */
	public PatientMenu() {
		LOGGER.log(Level.INFO, "Initialising Patient Menu Components");
		setLayout(new GridLayout());	
		JSplitPane landingPane = new JSplitPane();
		listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		listPanel.add(new JLabel("No Users Found", JLabel.CENTER), BorderLayout.NORTH);
		landingPane.add(listPanel, JSplitPane.LEFT);
		patientPanel = new JPanel();
		patientPanel.setLayout(new BorderLayout());
		patientPanel.add(new JLabel("No User Selected", JLabel.CENTER));
		landingPane.add(patientPanel, JSplitPane.RIGHT);
		add(landingPane);
		LOGGER.log(Level.INFO, "Patient Menu Correctly Initialised");
	}
	
	/**
	 * Adds a patient to the list of patients contained in the Patient Management System.
	 * @param patient The patient to be added.
	 */
	public void addToList(Patient patient) {
		if (patientJList == null) {
			LOGGER.log(Level.INFO, "Creating New List Of Patients");
			createJList();
			patientList.add(patient);
			patientJListModel.addElement(patient);
			LOGGER.log(Level.INFO, "Adding New Patient");
		}
		else {
			patientList.add(patient);
			patientJListModel.addElement(patient);
			LOGGER.log(Level.INFO, "Adding New Patient");
		}
	}
	
	/**
	 * Initiliases the list containing all the patients.
	 */
	public void createJList() {
		patientList = new ArrayList<Patient>();
		patientJList = new JList<Patient>();
		patientJList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			/* Displays the information menu of the selected patient. */
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				patientPanel.removeAll();
				if (patientJList.getSelectedValue() != null) {
					LOGGER.log(Level.INFO, "Patient Selected");
					patientPanel.add(new InformationMenu(patientJList.getSelectedValue()));
				}
				patientPanel.revalidate();
				patientPanel.repaint();
			}
			 
		 });
		
		patientJListModel = new DefaultListModel<Patient>();
		patientJList.setModel(patientJListModel);
		JScrollPane scrollPane = new JScrollPane(patientJList);
		listPanel.removeAll();
		listPanel.add(scrollPane);
		listPanel.updateUI();
	}
	
	/**
	 * Removes a patient from the list of patients contained in the Patient Management System.
	 * @param patient The patient to be removed.
	 */
	public void removeFromList(Patient patient) {
		if (patientJList != null) {
			LOGGER.log(Level.INFO, "Removing Patient");
			patientList.remove(patient);
			patientJListModel.removeElement(patient);
		}
	}
	
	/**
	 * Gets the patient currently selected by the user from the list of patients.
	 * @return The patient selected in the JList.
	 */
	public Patient getSelectedPatient() {
		if (patientJList != null) {
			return patientJList.getSelectedValue();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Changes the currently selected patient by indicating the index of the target patient. 
	 * @param index The index of the patient in the JList.
	 */
	public void setSelectedPatient(int index) {
		patientJList.setSelectedIndex(index);
	}
	
	
	/**
	 * Gets the list of patients currently contained in the Patient Management System.
	 * @return An ArrayList of patients contained in the System.
	 */
	public ArrayList<Patient> getPatients() {
		return patientList;
	}
	
	/**
	 * Revalidates the panel containing the information of any given patient.
	 */
	public void revalidatePatientMenu() {
		patientPanel.removeAll();
		patientPanel.add(new InformationMenu(patientJList.getSelectedValue()));
		patientPanel.revalidate();
		patientPanel.repaint();
	}

}