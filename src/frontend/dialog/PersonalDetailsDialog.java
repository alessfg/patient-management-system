package frontend.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.LoggerStartup;

/**
 * Creates the layout to be used by the add and edit JOptionPanes opened from the Management menu.
 * This layout contains various text fields which the user can use to either add a new patient or modify an exisiting one.
 * @author Alessandro
 * @version 1.0
 */
public class PersonalDetailsDialog extends JPanel {
	
	private JTextField firstName;
	private JTextField lastName;
	private JTextField birthday;
	private JTextField phoneNumber;
	private JTextField streetAddress;
	private JTextField postcode;
	private JTextField medicalCondition;
	private JTextField medicalURI;
	private JTextField doctor;
	private JTextField billing;
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Initialises the personal details dialog.
	 */
	public PersonalDetailsDialog() {	
		LOGGER.log(Level.INFO, "Initialising Personal Details Dialog");
		firstName = new JTextField(10);
		lastName = new JTextField(10);
		birthday = new JTextField(10);
		phoneNumber = new JTextField(10);
		streetAddress = new JTextField(10);
		postcode = new JTextField(10);
		billing = new JTextField(10);
		doctor = new JTextField(10);
		medicalCondition = new JTextField(10);
		medicalURI = new JTextField(10);
		
		setLayout(new GridBagLayout());
		GridBagConstraints cDetails = new GridBagConstraints();
		cDetails.anchor = GridBagConstraints.EAST;
		cDetails.weightx = 1;
		cDetails.weighty = 1;
		add(new JLabel("First Name: "),cDetails);
		cDetails.gridx = 1;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		add(firstName,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridx = 2;
		add(new JLabel("Last Name: "),cDetails);
		cDetails.gridx = 3;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		add(lastName,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridy = 1;
		cDetails.gridx = 0;
		add(new JLabel("Birthday: "),cDetails);
		cDetails.gridx = 1;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		add(birthday,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridx = 2;
		add(new JLabel("Phone Number: "),cDetails);
		cDetails.gridx = 3;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		add(phoneNumber,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridy = 2;
		cDetails.gridx = 0;
		add(new JLabel("Street Address: "),cDetails);
		cDetails.gridx = 1;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		add(streetAddress,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridx = 2;
		add(new JLabel("Postcode: "),cDetails);
		cDetails.gridx = 3;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		add(postcode,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridy = 3;
		cDetails.gridx = 0;
		add(new JLabel("Billing: "), cDetails);
		cDetails.gridx = 1;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		add(billing,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridx = 2;
		add(new JLabel("Doctor: "), cDetails);
		cDetails.gridx = 3;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		add(doctor,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridy = 4;
		cDetails.gridx = 0;
		add(new JLabel("Medical Condition: "),cDetails);
		cDetails.gridx = 1;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		add(medicalCondition,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridx = 2;
		add(new JLabel("Medical URL: "),cDetails);
		cDetails.gridx = 3;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		add(medicalURI,cDetails);
	}
	
	/**
	 * Gets the first name of a patient.
	 * @return The first name of a patient.
	 */
	public String getFirstName(){
		return firstName.getText();
	}

	/**
	 * Gets the last name of a patient.
	 * @return The last name of a patient.
	 */
	public String getLastName() {
		return lastName.getText();
	}
	
	/**
	 * Gets the birthday of a patient.
	 * @return The birthday of a patient.
	 */
	public String getBirthday() {
		return birthday.getText();
	}
	
	/**
	 * Gets the phone number of a patient.
	 * @return The phone number of a patient.
	 */
	public String getPhoneNumber() {
		return phoneNumber.getText();
	}
	
	/**
	 * Gets the street address of a patient.
	 * @return The street address of a patient.
	 */
	public String getStreetAddress() {
		return streetAddress.getText();
	}
	
	/**
	 * Gets the postcode of a patient.
	 * @return The postcode of a patient
	 */
	public String getPostcode() {
		return postcode.getText();
	}

	/**
	 * Gets the doctor of a patient.
	 * @return The doctor of a patient.
	 */
	public String getDoctor() {
		return doctor.getText();
	}
	
	/**
	 * Gets the billing of a patient.
	 * @return The billing of a patient
	 */
	public double getBilling() {
		double number;
		try {
			number = Double.parseDouble(billing.getText());
		}
		catch (NumberFormatException n) {
			LOGGER.log(Level.WARNING, "The Billing String Could Not Be Converted To A Number");
			number = 0;
		}
		return number;
	}

	/**
	 * Gets the medical condition of a patient.
	 * @return The medical condition of a patient.
	 */
	public String getMedicalCondition() {
		return medicalCondition.getText();
	}

	/**
	 * Gets the medical URI of a patient's condition.
	 * @return The medical URI of a patient's condition.
	 */
	public String getMedicalURI() {
		return medicalURI.getText();
	}

	/**
	 * Sets all the personal details of a patient.
	 * @param firstName The first name of a patient.
	 * @param lastName The last name of a patient.
	 * @param birthday The birthday of a patient.
	 * @param phoneNumber The phone number of a patient.
	 * @param streetAddress The street address of a patient.
	 * @param postcode The postcode of a patient.
	 * @param billing The current billing of a patient.
	 * @param doctor The doctor of a patient.
	 * @param medicalCondition The medical condition of a patient.
	 * @param medicalURI The medical URI of a patient's condition.
	 */
	public void setPatientData(String firstName, String lastName, String birthday, String phoneNumber, String streetAddress, 
							   String postcode, double billing, String doctor, String medicalCondition, String medicalURI) {
		this.firstName.setText(firstName);
		this.lastName.setText(lastName);
		this.birthday.setText(birthday);
		this.phoneNumber.setText(phoneNumber);
		this.streetAddress.setText(streetAddress);
		this.postcode.setText(postcode);
		this.billing.setText(String.valueOf(billing));
		this.doctor.setText(doctor);
		this.medicalCondition.setText(medicalCondition);
		this.medicalURI.setText(medicalURI);
	}
	
}