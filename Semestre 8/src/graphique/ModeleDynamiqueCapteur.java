package graphique;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.table.AbstractTableModel;
import sensor.Sensor;

public class ModeleDynamiqueCapteur extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final ArrayList<Sensor> capteurs= new ArrayList<Sensor>();
	private final String[]  entete= {"Id", "Nom", "Description"};

	public ModeleDynamiqueCapteur() {
		super();
	}

	public int getColumnCount() {
		return entete.length;
	}
	public int getRowCount() {
		return capteurs.size();
	}
	public String getColumnName(int columnIndex) {
		return entete[columnIndex];
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return capteurs.get(rowIndex).getIdSensor();
		case 1:
			return capteurs.get(rowIndex).getName();
		case 2:
			return capteurs.get(rowIndex).getDescription();
		default:
			return null;
		}
	}
	public void addCapteur(Sensor capteur) {
		capteurs.add(capteur);
		fireTableRowsInserted(capteurs.size()-1,capteurs.size()-1);
	}
	public void removeCapteur(int index) {
		capteurs.remove(index);
		fireTableRowsDeleted(index,index);	
	}
	public void initTable(HashSet<Sensor> sens) {
		for (Sensor capteur : sens) {
			capteurs.add(capteur);
			fireTableRowsInserted(capteurs.size()-1,capteurs.size()-1);
		}
	}
	public void removeAll() {
		int size=capteurs.size();
		for(int i=0;i<size;i++) {
			capteurs.remove(0);
			fireTableRowsDeleted(0, 0);
		}

	}

}
