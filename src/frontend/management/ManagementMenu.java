package frontend.management;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import backend.JSONParser;
import backend.LoggerStartup;
import backend.Patient;
import frontend.dialog.PersonalDetailsDialog;
import frontend.dialog.SearchDialog;

/**
 * Creates the Management menu for the Patient Management System.
 * This menu manages the main functions of the Patient Management System such as adding, deleting, editing, searching, opening, or saving patients.
 * Whenever one of the options is chosen a different JOptionPane appears giving the users more options.
 * The Management menu also acts as the container for the Patient menu, where different patients can be chosen to see more details about them.
 * @author Alessandro
 * @version 1.0
 */
public class ManagementMenu extends JPanel {
	
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Initialises the Management menu.
	 */
	public ManagementMenu() {
		setLayout(new BorderLayout());
		
		/* Initialises the Patient menu and adds it to the Management menu. */
		LOGGER.log(Level.INFO, "Initialising Patient Menu");
		PatientMenu patientMenu = new PatientMenu();
		LOGGER.log(Level.INFO, "Adding Patient Menu to the Management Menu");
		add(patientMenu, BorderLayout.CENTER);
		
		/* 
		 * Creates various buttons responsible for the various actions in the Management menu.
		 * An action listener is added to each button to define its function.
		 */
		LOGGER.log(Level.INFO, "Initialising Open File Button");
		JButton openBtn = new JButton(new ImageIcon(getClass().getResource("/open.gif")));
		openBtn.setToolTipText("Open Patient Register File");
		openBtn.addActionListener(new ActionListener() {
			
			/* Creates a new JSONParser to read a JSON text file.
			 * An ArrayList is returned by the parser and added to the list of patients contained in the Patient menu.
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LOGGER.log(Level.INFO, "Open File Selected");
				JSONParser json = new JSONParser();
				ArrayList<Patient> patient = json.load();
				LOGGER.log(Level.INFO, "Adding Patients To Management System");
				for (Patient p : patient) {
					patientMenu.addToList(p);
				}
			}	
		
		});
		
		LOGGER.log(Level.INFO, "Initialising Save To File Button");
		JButton saveBtn = new JButton(new ImageIcon(getClass().getResource("/save.gif")));
		saveBtn.setToolTipText("Save Patient Register File");
		saveBtn.addActionListener(new ActionListener() {
			
			/* 
			 * Opens a search dialog where the user can choose the patients to export.
			 * Once a list of patients has been selected a JSONParser is initialised to export the data to the selected text file.
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LOGGER.log(Level.INFO, "Save To File Selected");
				SearchDialog search = new SearchDialog(patientMenu.getPatients());
				search.setSingleSelection(false);
				int optionChosen = JOptionPane.showConfirmDialog(null, search, "Search Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);		
				if (optionChosen == JOptionPane.OK_OPTION) {
					ArrayList<Patient> list = new ArrayList<Patient>();
					list = patientMenu.getPatients();
					ArrayList<Patient> filteredlist = new ArrayList<Patient>();
					int[] selectedRows = search.getSelectedRows();
					for (int i = 0; i < selectedRows.length; i++) {
						filteredlist.add(list.get(selectedRows[i]));
					}
					JSONParser json = new JSONParser();
					LOGGER.log(Level.INFO, "Saving Patients To File");
					json.save(filteredlist);
				}
			}
		
		});
		
		LOGGER.log(Level.INFO, "Initialising Add Button");
		JButton addBtn = new JButton(new ImageIcon(getClass().getResource("/add.gif")));
		addBtn.setToolTipText("Add Patient");
		addBtn.addActionListener(new ActionListener() {
			
			/* Opens a patient dialog where the user can add a user to the Patient menu. */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LOGGER.log(Level.INFO, "Add Patient Selected");
				PersonalDetailsDialog personalDetailsDialog = new PersonalDetailsDialog();
				int optionChosen = JOptionPane.showConfirmDialog(null, personalDetailsDialog, "Add Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (optionChosen == JOptionPane.OK_OPTION) {
					patientMenu.addToList(new Patient(personalDetailsDialog.getFirstName(), personalDetailsDialog.getLastName(), personalDetailsDialog.getBirthday(), 
													  personalDetailsDialog.getPhoneNumber(), personalDetailsDialog.getStreetAddress(), personalDetailsDialog.getPostcode(), 
													  personalDetailsDialog.getBilling(), personalDetailsDialog.getDoctor(), personalDetailsDialog.getMedicalCondition(), 
													  personalDetailsDialog.getMedicalURI()));
					LOGGER.log(Level.INFO, "Patient Added To Management System");
				}
			}
		});
		
		JButton deleteBtn = new JButton(new ImageIcon(getClass().getResource("/delete.gif")));
		deleteBtn.setToolTipText("Remove Patient");
		deleteBtn.addActionListener(new ActionListener() {
			
			/* Deletes a patient from the system after the user confirms its deletion. */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LOGGER.log(Level.INFO, "Delete Patient Selected");
				int optionChosen = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Delete This Patient?", "Delete Patient", JOptionPane.OK_CANCEL_OPTION);
				if (optionChosen == JOptionPane.OK_OPTION) {
					Patient patient = patientMenu.getSelectedPatient();
					patientMenu.removeFromList(patient);

					LOGGER.log(Level.INFO, "Patient Deleted From Management System");
				}
			}
		});
		
		JButton editBtn = new JButton(new ImageIcon(getClass().getResource("/edit.gif")));
		editBtn.setToolTipText("Edit Patient");
		editBtn.addActionListener(new ActionListener() {
			
			/* Opens a patient dialog where the user can edit personal details of the user. */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LOGGER.log(Level.INFO, "Edit Patient Selected");
				PersonalDetailsDialog personalDetailsDialog = new PersonalDetailsDialog();
				if (patientMenu.getSelectedPatient()!= null) {
					Patient patient = patientMenu.getSelectedPatient();
					personalDetailsDialog.setPatientData(patient.getFirstName(), patient.getLastName(), patient.getBirthday(), patient.getPhoneNumber(), patient.getStreetAddress(), 
														 patient.getPostcode(), patient.getBilling(), patient.getDoctor(), patient.getCondition(), patient.getConditionURI());
					int optionChosen = JOptionPane.showConfirmDialog(null, personalDetailsDialog, "Edit Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (optionChosen == JOptionPane.OK_OPTION) {
						patient.setData(personalDetailsDialog.getFirstName(), personalDetailsDialog.getLastName(), personalDetailsDialog.getBirthday(),
									    personalDetailsDialog.getPhoneNumber(), personalDetailsDialog.getStreetAddress(), personalDetailsDialog.getPostcode(),
									    personalDetailsDialog.getBilling(), personalDetailsDialog.getDoctor(), personalDetailsDialog.getMedicalCondition(),
									    personalDetailsDialog.getMedicalURI());
						patientMenu.revalidatePatientMenu();
						LOGGER.log(Level.INFO, "Patient Edited");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No User Selected", "User Selection", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JButton searchBtn = new JButton(new ImageIcon(getClass().getResource("/search.gif")));
		searchBtn.setToolTipText("Search Patient Register");
		searchBtn.addActionListener(new ActionListener() {
			
			/* Opens a search dialog where the user can search patients and select the one to view. */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LOGGER.log(Level.INFO, "Search Patient Selected");
				if (patientMenu.getPatients() != null) {
					SearchDialog search = new SearchDialog(patientMenu.getPatients());
					search.setSingleSelection(true);
					int optionChosen = JOptionPane.showConfirmDialog(null, search, "Search Patient", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);		
					if (optionChosen == JOptionPane.OK_OPTION) {
						if (patientMenu.getPatients()!=null) {
							int i = search.getRow();
							patientMenu.setSelectedPatient(i);
							LOGGER.log(Level.INFO, "Patient Selected");
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No Users Found in Database", "Empty Database", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		
		});
		
		/* Creates a toolbar to display all of the buttons previously created. */
		LOGGER.log(Level.INFO, "Initialising Toolbar");
		JToolBar toolbar = new JToolBar();
		LOGGER.log(Level.INFO, "Adding Buttons to Toolbar");
		toolbar.add(openBtn);
		toolbar.add(saveBtn);
		toolbar.addSeparator();
		toolbar.add(addBtn);
		toolbar.add(deleteBtn);
		toolbar.addSeparator();
		toolbar.add(editBtn);
		toolbar.add(searchBtn);
		toolbar.setFloatable(false);
		LOGGER.log(Level.INFO, "Adding Toolbar To Management Menu");
		add(toolbar, BorderLayout.NORTH);
	}
	
}