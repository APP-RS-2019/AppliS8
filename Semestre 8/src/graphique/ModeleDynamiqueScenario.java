package graphique;

import java.util.ArrayList;
import java.util.HashSet;

import action.ActionToDo;

import javax.swing.table.AbstractTableModel;

public class ModeleDynamiqueScenario extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private final String[] entete = {"Robot", "Action", "Time"};
	private ArrayList<ActionToDo> actions;
	
	public ModeleDynamiqueScenario() {
		super();
		this.actions=new ArrayList<ActionToDo>();
	}
	public int getColumnCount() {
		return entete.length;
	}
	public int getRowCount() {
		return actions.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0:
			return actions.get(rowIndex).getNameRobot();
		case 1:
			return actions.get(rowIndex).getNameAction();
		case 2:
			return actions.get(rowIndex).getTime();
		default:
			return null;
		}
	}
	public void addAction(ActionToDo action) {
		actions.add(action);
		fireTableRowsInserted(actions.size()-1,actions.size()-1);
	}
	public void removeAction(int index) {
		actions.remove(index);
		fireTableRowsDeleted(index,index);	
	}
	public String getColumnName(int columnIndex) {
		return entete[columnIndex];
	}
	public void initTable(HashSet<ActionToDo> acts) {
		for (ActionToDo action : acts) {
			actions.add(action);
			fireTableRowsInserted(actions.size()-1,actions.size()-1);
		}		
	}
}
