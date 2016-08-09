package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * This class is responsible for saving and opening the data selected by the user.
 * All save files are written in JSON using the free third party library available at http://www.json.org/java/index.html.
 * @author Alessandro
 * @version 1.0
 */
public class JSONParser {
	
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Saves a list of patients in JSON format to the selected text file.
	 * @param patient The list of patients to be saved.
	 */
	public void save(ArrayList<Patient> patient) {
		JFileChooser saveFile = new JFileChooser();
		saveFile.showSaveDialog(null);
		if (saveFile.getSelectedFile()!= null) {
			try {
				LOGGER.log(Level.INFO, "Saving Patients");
				BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile.getSelectedFile(), true));
				JSONObject jsonFnl = new JSONObject();
				JSONArray array = new JSONArray();
				for (Patient p : patient) {
					JSONObject json = new JSONObject();
					json.put("First Name", p.getFirstName());
					json.put("Last Name", p.getLastName());
					json.put("Birthday", p.getBirthday());
					json.put("Phone Number", p.getPhoneNumber());
					json.put("Street Address", p.getStreetAddress());
					json.put("Postcode", p.getPostcode());
					json.put("Medical Condition", p.getCondition());
					json.put("Medical URI", p.getConditionURI());
					json.put("Comments", p.getComments());
					json.put("Appointments", p.getAppointments());
					json.put("Doctor", p.getDoctor());
					json.put("ID", p.getID());
					json.put("Billing", p.getBilling());
					json.put("Profile Picture Path", p.getProfilePicturePath());
					json.put("Condition Images", p.getConditionImage());
					array.put(json);
				}
				jsonFnl.put("Patients", array);
				jsonFnl.write(writer);
				writer.close();
			} 
			catch (IOException e) {
				LOGGER.log(Level.WARNING, "Patients Could Not Be Saved To The Selected File");
			}
		}
	}
	
	/**
	 * Loads a list of patients from a JSON text file and returns an ArrayList containing those patients.
	 * @return A list of patients.
	 */
	public ArrayList<Patient> load() {
		ArrayList<Patient> patient = new ArrayList<Patient>();
		JFileChooser openFile = new JFileChooser();
		openFile.showOpenDialog(null);
		if (openFile.getSelectedFile()!= null) {
			try {
				LOGGER.log(Level.INFO, "Loading Patients");
				BufferedReader reader = new BufferedReader(new FileReader(openFile.getSelectedFile()));
				JSONTokener tokener = new JSONTokener(reader);
				JSONObject json = new JSONObject(tokener);
				JSONArray array = json.getJSONArray("Patients");
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					ArrayList<String> imagePaths = new ArrayList<String>();
					JSONArray imageArray = object.getJSONArray("Condition Images");
					for (int j = 0; j < imageArray.length(); j++) {
						imagePaths.add(imageArray.get(j).toString());
					}
					patient.add(new Patient(object.getString("First Name"), object.getString("Last Name"), object.getString("Birthday"), 
											object.getString("Phone Number"), object.getString("Street Address"), object.getString("Postcode"), 
											object.getDouble("Billing"), object.getString("Doctor"), object.getString("Medical Condition"), 
											object.getString("Medical URI"), object.getString("ID"), object.getString("Profile Picture Path"), 
											imagePaths, object.getString("Comments"), object.getString("Appointments")));
				}
				reader.close();
			} 
			catch (IOException e) {
				LOGGER.log(Level.WARNING, "Selected File Could Not Be Opened");
			}
		}
		return patient;
	}
	
}