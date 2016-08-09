package frontend.management.data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import backend.LoggerStartup;
import backend.Patient;

/**
 * Creates the Personal Details menu.
 * This menu contains all the basic information of a patient and allows the user to change the profile picture of a patient.
 * @author Alessandro
 * @version 1.0
 */
public class PersonalDetailsMenu extends JPanel {
	
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Initialises the Personal Details menu.
	 * @param patient The patient whose personal details are required.
	 */
	public PersonalDetailsMenu(Patient patient) {
		LOGGER.log(Level.INFO, "Initialising Personal Details Menu");
		JPanel profilePicture = new JPanel();
		profilePicture.setLayout(new GridBagLayout());
		GridBagConstraints cProfilePicture = new GridBagConstraints();
		profilePicture.add(new JLabel("ID: " + patient.getID()), cProfilePicture);
		
		cProfilePicture.gridy = 1;
		JLabel image = new JLabel();
		image.setBorder(UIManager.getBorder("TextField.border"));
		image.setHorizontalAlignment(JLabel.CENTER);
		if (!patient.getProfilePicturePath().equals("")) {
			image.setIcon(new ImageIcon(patient.getProfilePicturePath()));
			image.setPreferredSize(new Dimension(300,300));
		}
		else {
			image.setText("Insert Profile Image");
			image.setOpaque(true);
			image.setBackground(Color.WHITE);
			image.setPreferredSize(new Dimension(300, 300));
		}		
		profilePicture.add(image, cProfilePicture);

		cProfilePicture.gridy = 2;
		JButton addBtn = new JButton(new ImageIcon(getClass().getResource("/add.gif")));
		addBtn.setToolTipText("Add Profile Picture");
		addBtn.addActionListener(new ActionListener() {
			
			/* Allows the user to add a profile image to a patient. */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LOGGER.log(Level.INFO, "Add Image Selected");
				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(null);
				if (openFile.getSelectedFile()!= null) {
					patient.setProfilePicture(openFile.getSelectedFile().getPath());
					image.setText(null);
					image.setIcon(new ImageIcon(patient.getProfilePicturePath()));
					LOGGER.log(Level.INFO, "Image Added");
				}
			}
			
		});
		JButton deleteBtn = new JButton(new ImageIcon(getClass().getResource("/delete.gif")));
		deleteBtn.setToolTipText("Remove Profile Picture");
		deleteBtn.addActionListener(new ActionListener() {
			
			/* Allows the user to delete the profile image of a patient. */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LOGGER.log(Level.INFO, "Delete Image Selected");
				image.setIcon(null);
				image.setText("Insert Profile Image");
				image.setBackground(Color.WHITE);
				image.setPreferredSize(new Dimension(300, 300));
				LOGGER.log(Level.INFO, "Image Deleted");
			}
			
		});
		JPanel buttons = new JPanel();
		buttons.add(addBtn);
		buttons.add(deleteBtn);
		profilePicture.add(buttons, cProfilePicture);
		
		JPanel details = new JPanel();
		details.setLayout(new GridBagLayout());
		GridBagConstraints cDetails = new GridBagConstraints();
		cDetails.anchor = GridBagConstraints.EAST;
		cDetails.weightx = 1;
		cDetails.weighty = 1;
		details.add(new JLabel("First Name: "),cDetails);
		cDetails.gridx = 1;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		JTextField firstName = new JTextField();
		firstName.setText(patient.getFirstName());
		firstName.setEditable(false);
		firstName.setHorizontalAlignment(JTextField.CENTER);
		details.add(firstName,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridx = 2;
		details.add(new JLabel("Last Name: "),cDetails);
		cDetails.gridx = 3;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		JTextField lastName = new JTextField();
		lastName.setText(patient.getLastName());
		lastName.setEditable(false);
		lastName.setHorizontalAlignment(JTextField.CENTER);
		details.add(lastName,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridy = 1;
		cDetails.gridx = 0;
		details.add(new JLabel("Birthday: "),cDetails);
		cDetails.gridx = 1;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		JTextField birthday = new JTextField();
		birthday.setText(patient.getBirthday());
		birthday.setEditable(false);
		birthday.setHorizontalAlignment(JTextField.CENTER);
		details.add(birthday,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridx = 2;
		details.add(new JLabel("Phone Number: "),cDetails);
		cDetails.gridx = 3;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		JTextField phoneNumber = new JTextField();
		phoneNumber.setText(patient.getPhoneNumber());
		phoneNumber.setEditable(false);
		phoneNumber.setHorizontalAlignment(JTextField.CENTER);
		details.add(phoneNumber,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridy = 2;
		cDetails.gridx = 0;
		details.add(new JLabel("Street Address: "),cDetails);
		cDetails.gridx = 1;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		JTextField streetAddress = new JTextField();
		streetAddress.setText(patient.getStreetAddress());
		streetAddress.setEditable(false);
		streetAddress.setHorizontalAlignment(JTextField.CENTER);
		details.add(streetAddress,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridx = 2;
		details.add(new JLabel("Postcode: "),cDetails);
		cDetails.gridx = 3;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		JTextField postcode = new JTextField();
		postcode.setText(patient.getPostcode());
		postcode.setEditable(false);
		postcode.setHorizontalAlignment(JTextField.CENTER);
		details.add(postcode,cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridy = 3;
		cDetails.gridx = 0;
		details.add(new JLabel("Billing: "), cDetails);
		cDetails.gridx = 1;
		cDetails.fill = GridBagConstraints.HORIZONTAL;	
		JTextField billing = new JTextField();
		billing.setText(String.valueOf(patient.getBilling()));
		billing.setEditable(false);
		billing.setHorizontalAlignment(JTextField.CENTER);
		details.add(billing, cDetails);
		
		cDetails.fill = GridBagConstraints.NONE;
		cDetails.gridx = 2;
		details.add(new JLabel("Doctor: "), cDetails);
		cDetails.gridx = 3;
		cDetails.fill = GridBagConstraints.HORIZONTAL;
		JTextField doctor = new JTextField();
		doctor.setText(String.valueOf(patient.getDoctor()));
		doctor.setEditable(false);
		doctor.setHorizontalAlignment(JTextField.CENTER);
		details.add(doctor, cDetails);
		
		setLayout(new GridLayout(1,2));
		add(details);
		add(profilePicture);
	}

}
