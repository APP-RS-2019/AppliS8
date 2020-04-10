package graphique;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import robot.*;

public class ModeleDynamiqueFlotte extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private final ArrayList<Fleet> flottes;
	private final String[] entete = {"Id", "Nom", "NbRobots"};
	public ModeleDynamiqueFlotte(ArrayList<Fleet> fleets) {
		super();
		this.flottes=new ArrayList<Fleet>();
		for (int i=0;i<fleets.size();i++) {
			flottes.add(fleets.get(i));
		}
	}

	public int getColumnCount() {
		return entete.length;
	}
	public int getRowCount() {
		return flottes.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0:
			return flottes.get(rowIndex).getId();
		case 1:
			return flottes.get(rowIndex).getName();
		case 2:
			return flottes.get(rowIndex).sizeFleet();
		default:
			return null;
		}
	}
	public void addFlotte(Fleet flotte) {
		flottes.add(flotte);
		fireTableRowsInserted(flottes.size()-1,flottes.size()-1);
	}
	public void removeFlotte(int index) {
		flottes.remove(index);
		fireTableRowsDeleted(index,index);	
	}
	public String getColumnName(int columnIndex) {
		return entete[columnIndex];
	}

}
