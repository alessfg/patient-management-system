package frontend.management.data;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import backend.LoggerStartup;
import backend.Patient;

/**
 * Creates the Medical Details menu.
 * This menu contains a link to the medical condition of the patient and a series of images describing the patients condition. 
 * Images can be both added and deleted.
 * @author Alessandro
 * @version 1.0
 */
public class MedicalDetailsMenu extends JPanel {
	
	private String selectedImage;
	private ListIterator<String> imageIterator;
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());

	/**
	 * Initialises the Medical Details menu.
	 * @param patient The patient whose medical details are required.
	 */
	public MedicalDetailsMenu(Patient patient) {
		LOGGER.log(Level.INFO, "Initialising Medical Details Menu");
		setLayout(new GridBagLayout());
		GridBagConstraints cDetails = new GridBagConstraints();
		JLabel label = new JLabel(patient.getCondition(), JLabel.CENTER);
		label.setForeground(Color.BLUE);
		label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new MouseListener() {
			
			/* Opens the user's browser if they click the label with the medical description. */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI(patient.getConditionURI()));
					LOGGER.log(Level.INFO, "Opening The Browser");
				} 
				catch (URISyntaxException e) {
					JOptionPane.showMessageDialog(null, "URI Could Not Be Found", "Incorrect URI", JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "Incorrect URI");
				}
				catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Browser Could Not Be Found", "Browser Error", JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "Browser Could Not Be Opened");
				} 
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// Do nothing.
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// Do nothing.
			}
			@Override
			public void mousePressed(MouseEvent arg0) {	
				// Do nothing.
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {		
				// Do nothing.
			}	
			
		});
		JPanel medicalLink = new JPanel();
		medicalLink.add(new JLabel("Medical Description: "));
		medicalLink.add(label);
		add(medicalLink, cDetails);
		
		cDetails.gridy = 1;
		JLabel image = new JLabel();
		image.setBorder(UIManager.getBorder("TextField.border"));
		image.setHorizontalAlignment(JLabel.CENTER);
		image.setVerticalAlignment(JLabel.CENTER);
		if (patient.getConditionImage().isEmpty()) {
			image.setText("No Images Found");
			image.setHorizontalAlignment(JLabel.CENTER);
			image.setOpaque(true);
			image.setBackground(Color.WHITE);
			image.setPreferredSize(new Dimension(300, 300));
		}
		else {
			imageIterator = patient.getConditionImage().listIterator();
			image.setIcon(new ImageIcon(imageIterator.next()));
			image.setPreferredSize(new Dimension(300, 300));
		}
		JPanel panel = new JPanel();
		panel.add(image);
		add(panel, cDetails);
		
		/* 
		 * Each of the following buttons is responsible for the functionality described by its name.
		 * E.g. addBtn adds an image.
		 */
		cDetails.gridy = 2;
		JButton previousBtn = new JButton(new ImageIcon(getClass().getResource("/previous.gif")));
		previousBtn.setToolTipText("Previous Image");
		previousBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imageIterator != null) {
					if (imageIterator.hasPrevious()) {
						LOGGER.log(Level.INFO, "Go To Previous Image");
						selectedImage = imageIterator.previous();
						image.setIcon(new ImageIcon(selectedImage));
						image.setPreferredSize(new Dimension(300, 300));
						if (!imageIterator.hasPrevious()) {
							imageIterator.next();
						}
					}
				}
			}
			
		});
		JButton addBtn = new JButton(new ImageIcon(getClass().getResource("/add.gif")));
		addBtn.setToolTipText("Add Image");
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(null);
				if (openFile.getSelectedFile()!= null) {
					LOGGER.log(Level.INFO, "Add Selected Image");
					selectedImage = openFile.getSelectedFile().getPath();
					patient.getConditionImage().add(selectedImage);
					image.setText(null);
					image.setIcon(new ImageIcon(selectedImage));
					image.setPreferredSize(new Dimension(300, 300));
					imageIterator = patient.getConditionImage().listIterator();
					while(imageIterator.hasNext()) {
						imageIterator.next();	
					}
					if (imageIterator.hasPrevious()) {
						imageIterator.previous();
					}
				}
			}
			
		});
		JButton deleteBtn = new JButton(new ImageIcon(getClass().getResource("/delete.gif")));
		deleteBtn.setToolTipText("Delete Image");
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!patient.getConditionImage().isEmpty()) {
					LOGGER.log(Level.INFO, "Delete Selected Image");
					patient.getConditionImage().remove(selectedImage);
					imageIterator = patient.getConditionImage().listIterator();
					if (patient.getConditionImage().isEmpty()) {
						image.setIcon(null);
						image.setText("No Images Found");
						image.setHorizontalAlignment(JLabel.CENTER);
						image.setOpaque(true);
						image.setBackground(Color.WHITE);
						image.setPreferredSize(new Dimension(300, 300));
					}
					else {
						if (imageIterator.hasNext()) {
							selectedImage = imageIterator.next();
							image.setIcon(new ImageIcon(selectedImage));
						}
						else if (imageIterator.hasPrevious()) {
							selectedImage = imageIterator.previous();
							image.setIcon(new ImageIcon(selectedImage));
						}
					}
				}
			}
		
			
		});
		JButton nextBtn = new JButton(new ImageIcon(getClass().getResource("/next.gif")));
		nextBtn.setToolTipText("Next Image");
		nextBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (imageIterator != null) {
					if (imageIterator.hasNext()) {
						LOGGER.log(Level.INFO, "Go To Next Image");
						selectedImage = imageIterator.next();
						image.setIcon(new ImageIcon(selectedImage));
						image.setPreferredSize(new Dimension(300, 300));
						if (!imageIterator.hasNext()) {
							imageIterator.previous();
						}
					}
				}
			}
			
		});
		JPanel buttons = new JPanel();
		buttons.add(previousBtn);
		buttons.add(addBtn);
		buttons.add(deleteBtn);
		buttons.add(nextBtn);
		add(buttons, cDetails);
	}
	
}
