package frontend.dialog;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;

import backend.LoggerStartup;

/**
 * A simple class used to override the method responsible for making a default table editable.
 * @author Alessandro
 * @version 1.0
 */
public class SearchTableModel extends DefaultTableModel {
	
	private final static Logger LOGGER = Logger.getLogger(LoggerStartup.class.getName());
	
	/**
	 * Initialises the table model.
	 * @param data The data used to populate the rows of the table.
	 * @param columnNames The columns names of the table.
	 */
	public SearchTableModel(Object[][] data, Object[] columnNames){
		super(data, columnNames);
		LOGGER.log(Level.INFO, "Initialising Search Table Model");
	}
	
	/**
	 * Overrides the isCellEditable method so that it always return false.
	 * This makes all the cells of the table non editable.
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
