package backend;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class representing a patient contained in the Patient Management System.
 * @author Alessandro
 * @version 1.0
 */
public class Patient {

	private String firstName;
	private String lastName;
	private String birthday;
	private String phoneNumber;
	private String streetAddress;
	private String postcode;
	private double billing;
	private String doctor;
	private String medicalCondition;
	private String medicalURI;
	private String ID;
	private String profilePicturePath;
	private ArrayList<String> conditionImage;
	private String comments;
	private String appointments;
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Initialises a patient with basic personal details.
	 * @param firstName The first name of a patient.
	 * @param lastName The last name of a patient.
	 * @param birthday The birthday of a patient.
	 * @param phoneNumber The phone number of a patient.
	 * @param streetAddress The street address of a patient.
	 * @param postcode The postcode of a patient.
	 * @param billing The current billing of a patient.
	 * @param doctor The doctor of a patient.
	 * @param medicalCondition The medical condition of a patient.
	 * @param medicalURI The medical URI of a patient.
	 */
	public Patient(String firstName, String lastName, String birthday, String phoneNumber, String streetAddress,
				   String postcode, double billing, String doctor, String medicalCondition, String medicalURI) {
		LOGGER.log(Level.INFO, "Creating A New Patient");
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.postcode = postcode;
		this.billing = billing;
		this.doctor = doctor;
		this.medicalCondition = medicalCondition;
		this.medicalURI = medicalURI;
		this.ID = UUID.randomUUID().toString();
		this.profilePicturePath = "";
		this.conditionImage = new ArrayList<String>();
		this.comments = "";
		this.appointments = "";
	}
	
	/**
	 * Initialises a patient with all details. Used when loading patients from a text file.
	 * @param firstName The first name of a patient.
	 * @param lastName The last name of a patient.
	 * @param birthday The birthday of a patient.
	 * @param phoneNumber The phone number of a patient.
	 * @param streetAddress The street address of a patient.
	 * @param postcode The postcode of a patient.
	 * @param billing The current billing of a patient.
	 * @param doctor The doctor of a patient.
	 * @param medicalCondition The medical condition of a patient.
	 * @param medicalURI The medical URI of a patient.
	 * @param ID The ID of a patient.
	 * @param profilePicturePath The profile picture path of a patient.
	 * @param conditionImage The array containing all the medical picture paths of a patient.
	 * @param comments The comments of a patient.
	 * @param appointments The appointments of a patient.
	 */
	public Patient(String firstName, String lastName, String birthday, String phoneNumber, String streetAddress,
				   String postcode, double billing, String doctor, String medicalCondition, String medicalURI, 
				   String ID, String profilePicturePath, ArrayList<String> conditionImage, String comments, String appointments) {
		LOGGER.log(Level.INFO, "Creating A New Patient");
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.postcode = postcode;
		this.billing = billing;
		this.doctor = doctor;
		this.medicalCondition = medicalCondition;
		this.medicalURI = medicalURI;
		this.ID = ID;
		this.profilePicturePath = profilePicturePath;
		this.conditionImage = conditionImage;
		this.comments = comments;
		this.appointments = appointments;	
	}
	
	/**
	 * Gets the first name of a patient.
	 * @return The first name of a patient.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Gets the last name of a patient.
	 * @return The last name of a patient.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Gets the birthday of a patient.
	 * @return The birthday of a patient.
	 */
	public String getBirthday() {
		return birthday;
	}
	
	/**
	 * Gets the street address of a patient.
	 * @return The street address of a patient.
	 */
	public String getStreetAddress() {
		return streetAddress;
	}
	
	/**
	 * Gets the postcode of a patient.
	 * @return The postcode of a patient.
	 */
	public String getPostcode() {
		return postcode;
	}
	
	/**
	 * Gets the phone number of a patient.
	 * @return The phone number of a patient.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Gets the billing of a patient.
	 * @return The billing of a patient.
	 */
	public double getBilling() {
		return billing;
	}
	
	/**
	 * Gets the doctor of a patient.
	 * @return The doctor of a patient.
	 */
	public String getDoctor() {
		return doctor;
	}
	
	/**
	 * Get the medical condition of a patient.
	 * @return The medical condition of a patient.
	 */
	public String getCondition() {
		return medicalCondition;
	}
	/**
	 * Get the medical URI of a patient's condition.
	 * @return The medical URI of a patient's condition.
	 */
	public String getConditionURI() {
		return medicalURI;
	}
	
	/**
	 * Gets the ID of a patient.
	 * @return The ID of a patient.
	 */
	public String getID() {
		return ID;
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
	public void setData(String firstName, String lastName, String birthday, String phoneNumber, String streetAddress, 
							   String postcode, double billing, String doctor, String medicalCondition, String medicalURI) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.postcode = postcode;
		this.billing = billing;
		this.doctor = doctor;
		this.medicalCondition = medicalCondition;
		this.medicalURI = medicalURI;
	}
	
	/**
	 * Gets the path of a patient's user picture.
	 * @return The path of a patient's user picture.
	 */
	public String getProfilePicturePath() {
		return profilePicturePath;
	}
	
	/**
	 * Sets the path of a patient's user picture.
	 * @param profilePicturePath The path of a patient's user picture.
	 */
	public void setProfilePicture(String profilePicturePath) {
		this.profilePicturePath = profilePicturePath;
	}
	
	/**
	 * Gets an array containing the paths to various medical pictures of a patient.
	 * @return An array containing the paths to various medical pictures of a patient.
	 */
	public ArrayList<String> getConditionImage() {
		return conditionImage;
	}
	
	/**
	 * Gets the comments of a patient.
	 * @return The comments of a patient.
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * Sets the comments of a patient.
	 * @param comments The comments of a patient.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the appointments of a patient.
	 * @return The appointments of a patient.
	 */
	public String getAppointments() {
		return appointments;
	}
	
	/**
	 * Sets the appointments of a patient.
	 * @param appointments The appointments of a patient.
	 */
	public void setAppointments(String appointments) {
		this.appointments = appointments;
	}
	
	/**
	 * Overrides the toString method to return a string containing the first and last name of a patient.
	 * Used to populate the list of patients contained in the Patient Management System.
	 * @return A string containing the first and last name of a patient
	 */
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

}