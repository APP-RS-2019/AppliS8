package graphique;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.table.AbstractTableModel;
import action.Action;
import sensor.Sensor;

public class ModeleDynamiqueAction extends AbstractTableModel{
	private final ArrayList<Action> actions= new ArrayList<Action>();
	private final String[]  entete= {"Id", "Nom", "description"};

	public ModeleDynamiqueAction() {
		super();
	}

	public int getColumnCount() {
		return entete.length;
	}
	public int getRowCount() {
		return actions.size();
	}
	public String getColumnName(int columnIndex) {
		return entete[columnIndex];
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return actions.get(rowIndex).getAction();
		case 1: 
			return actions.get(rowIndex).getNom();
		case 2:
			return actions.get(rowIndex).getDescription();
		default:
			return null;
		}
	}
	public void addAction(Action action) {
		actions.add(action);
		fireTableRowsInserted(actions.size()-1,actions.size()-1);
	}
	public void removeAction(int index) {
		actions.remove(index);
		fireTableRowsDeleted(index,index);	
	}
	public void initTable(HashSet<Action> acts) {
		for (Action action : acts) {
			actions.add(action);
			fireTableRowsInserted(actions.size()-1,actions.size()-1);
		}		
	}
	public void removeAll() {
		int size=actions.size();
		for(int i=0;i<size;i++) {
			actions.remove(0);
			fireTableRowsDeleted(0, 0);
		}

	}

}
