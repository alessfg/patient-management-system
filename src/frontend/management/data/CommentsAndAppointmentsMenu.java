package frontend.management.data;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import backend.LoggerStartup;
import backend.Patient;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates the Comments and Appointments menu.
 * This menu contains all the comments and appointments of any given patient.
 * @author Alessandro
 * @version 1.0
 */
public class CommentsAndAppointmentsMenu extends JPanel {

	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());

	/**
	 * Initialises the Comments and Appointments menu.
	 * @param patient The patient containing the comments and appointments.
	 */
	public CommentsAndAppointmentsMenu(Patient patient) {
		LOGGER.log(Level.INFO, "Initialising Comments And Appointments Menu");
		setLayout(new GridLayout(2,1));
		
		JTextArea commentsArea = new JTextArea();
		commentsArea.setText(patient.getComments());
		commentsArea.addKeyListener(new KeyAdapter() {
			
			/* Whenever a key is pressed and released the comments string of the patient is updated. */
			@Override
			public void keyReleased(KeyEvent arg0) {
				patient.setComments(commentsArea.getText());
			}
			
		});
		JScrollPane commentsPane = new JScrollPane(commentsArea);
		JPanel comments = new JPanel();
		comments.setLayout(new BorderLayout());
		comments.setBorder(new TitledBorder("Comments"));
		comments.add(commentsPane);
		add(comments);
		
		JTextArea appointmentsArea = new JTextArea();
		appointmentsArea.setText(patient.getAppointments());
		appointmentsArea.addKeyListener(new KeyAdapter() {

			/* Whenever a key is pressed and released the appointments string of the patient is updated. */
			@Override
			public void keyReleased(KeyEvent e) {
				patient.setAppointments(appointmentsArea.getText());
			}
			
		});
		JPanel appointments = new JPanel();
		appointments.setLayout(new BorderLayout());
		appointments.setBorder(new TitledBorder("Appointments"));
		JScrollPane appointmentsPane = new JScrollPane(appointmentsArea);
		appointments.add(appointmentsPane);
		add(appointments);
	}

}
