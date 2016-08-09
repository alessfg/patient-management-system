package frontend.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import backend.LoggerStartup;
import backend.Patient;

/**
 * Creates the layout to be used by the search and save JOptionPanes opened from the Management menu.
 * This layout contains a table with a custom model displaying relevant fields from each patient contained in the database.
 * It is not possible to edit the table.
 * It is possible to search the table by typing on the textfield below the table.
 * If the user is searching the list of patients it is only possible to select a single patient.
 * If the user is saving patients then multiple patients can be selected.
 * @author Alessandro
 * @version 1.0
 */
public class SearchDialog extends JPanel {
	
	private int modelRow;
	private int[] selectedRows;
	private JTable table;
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());

	/**
	 * Initialises the search dialog.
	 * @param patient The patients used to populate the table.
	 */
	public SearchDialog(ArrayList<Patient> patient) {
		LOGGER.log(Level.INFO, "Initialising Search Dialog");		
		setLayout(new BorderLayout());
		String[][] data = new String[patient.size()][9];
		for (int i = 0; i < patient.size(); i++) {
			data[i][0] = patient.get(i).getFirstName();
			data[i][1] = patient.get(i).getLastName();
			data[i][2] = patient.get(i).getBirthday();
			data[i][3] = patient.get(i).getPhoneNumber();
			data[i][4] = patient.get(i).getStreetAddress();
			data[i][5] = patient.get(i).getPostcode();
			data[i][6] = patient.get(i).getCondition();
			data[i][7] = patient.get(i).getDoctor();
			data[i][8] = patient.get(i).getID();
		}
		String[] columns = {"First Name", "Last Name", "Birthday", "Phone Number", "Street Address", "Postcode", "Medical Condition", "Doctor", "ID"};
		SearchTableModel model = new SearchTableModel(data, columns);
		table = new JTable();
		table.setModel(model);
		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(1000,250));
		JScrollPane scrollPane = new JScrollPane(table);
		TableRowSorter<SearchTableModel> rowsorter = new TableRowSorter<SearchTableModel>(model);
		table.setRowSorter(rowsorter);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			/* Stores the currently selected rows based on the selection mode of the table. */
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (table.getSelectionModel().getSelectionMode()== ListSelectionModel.SINGLE_SELECTION) {
					LOGGER.log(Level.INFO, "Single Row Selected");
					int viewRow = table.getSelectedRow();
					modelRow = table.convertRowIndexToModel(viewRow);
				}
				else {
					LOGGER.log(Level.INFO, "Multiple Rows Selected");
					int[] rows = table.getSelectedRows();
					selectedRows = new int[rows.length];
					for (int i = 0; i < rows.length; i++) {
						selectedRows[i] = table.convertRowIndexToModel(rows[i]);
					}
				}
			}
			
		});
		JTextField filter = new JTextField(10);
		rowsorter.setRowFilter(RowFilter.regexFilter(filter.getText()));
		filter.addKeyListener(new KeyAdapter() {
			
			/* Whenever a key is released the row filter is updated with the contents of the text field. */
			@Override
			public void keyReleased(KeyEvent arg0) {
				rowsorter.setRowFilter(RowFilter.regexFilter(filter.getText()));
			}
			
		});
		add(scrollPane);
		add(filter, BorderLayout.SOUTH);
	}
	
	/**
	 * Gets the currently selected row in the table.
	 * Used when searching the list of patients.
	 * @return The currently selected row.
	 */
	public int getRow() {
		return modelRow;
	}
	
	/**
	 * Gets the currently selected rows in the table.
	 * Used when selecting which patients to export.
	 * @return The currently selected rows.
	 */
	public int[] getSelectedRows() {
		return selectedRows;
	}
	
	/**
	 * Sets the selection mode currently employed by the table.
	 * @param selection True if only one patient can be selected.
	 */
	public void setSingleSelection(boolean selection) {
		if (selection) {
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		else {
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
	}

}