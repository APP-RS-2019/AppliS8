package graphique;

import java.util.*;

import javax.swing.table.AbstractTableModel;
import robot.*;

public class ModeleDynamiqueRobot extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private final ArrayList<Robot> robots= new ArrayList<Robot>();
	private final String[]  entete= {"Id/IP", "Nom", "Posx", "Posy","Angle","nb capteur","nb action"};//addnbaction et nbsensor

	public ModeleDynamiqueRobot() {
		super();
	}
	public int getColumnCount() {
		return entete.length;
	}
	public int getRowCount() {
		return robots.size();
	}
	public String getColumnName(int columnIndex) {
		return entete[columnIndex];
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return robots.get(rowIndex).getId();
		case 1:
			return robots.get(rowIndex).getName();
		case 2:
			return robots.get(rowIndex).getPosx();
		case 3:
			return robots.get(rowIndex).getPosy();
		case 4:
			return robots.get(rowIndex).getAngle();
		case 5:
			return robots.get(rowIndex).nbSensor();
		case 6:
			return robots.get(rowIndex).nbAction();
		default:
			return null;			
		}
	}
	public void addRobot(Robot robot) {
		robots.add(robot);
		fireTableRowsInserted(robots.size()-1,robots.size()-1);
	}
	public void removeRobot(int index) {
		robots.remove(index);
		fireTableRowsDeleted(index,index);	
	}
	public void initTable(HashSet<Robot> robs) {
		this.removeAll();
		for (Robot robot : robs) {
			robots.add(robot);
			fireTableRowsInserted(robots.size()-1,robots.size()-1);
		}
	}
	public void removeAll() {
		int size=robots.size();
		for(int i=0;i<size;i++) {
			robots.remove(0);
			fireTableRowsDeleted(0, 0);
		}
	}
}
