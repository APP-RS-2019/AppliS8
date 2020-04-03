package graphique;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import action.Action;
import eventModel.AL;
import robot.*;
import sensor.Sensor;
import upperClass.Syst;

import javax.swing.JButton;
import java.awt.event.*;
import java.util.*;


public class OngletModeNormal extends JPanel implements AL{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableFlotteExistantes;
	private JTable tableRobotsFlotte;
	private JTable tableAction;
	private JTable tableCapteurs;
	private ModeleDynamiqueFlotte modeleFlotte;
	private ModeleDynamiqueRobot modeleRobot;
	private ModeleDynamiqueAction modeleAction;
	private ModeleDynamiqueCapteur modeleCapteur;
	private int flotteSelect;
	private int robotSelect;
	
	private JButton buttonFleetToRobots = new JButton("=>");
	private JButton buttonRobotToSensors = new JButton("=>");

	public OngletModeNormal() {
		setLayout(null);

		//tests de flotte à afficher

		this.modeleFlotte=new ModeleDynamiqueFlotte(Syst.getFleets());
		this.modeleRobot=new ModeleDynamiqueRobot();
		this.modeleAction=new ModeleDynamiqueAction();
		this.modeleCapteur=new ModeleDynamiqueCapteur();
		this.flotteSelect=0; this.robotSelect=0;
		
		buttonFleetToRobots.setBounds(615, 395, 50, 25);
		add(buttonFleetToRobots);
		
		buttonRobotToSensors.setBounds(1290, 395, 50, 25);
		add(buttonRobotToSensors);

		JButton btnExecuter_1 = new JButton("Excecute");
		btnExecuter_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExecuter_1.setBounds(1861, 420, 41, 25);
		add(btnExecuter_1);

		JButton buttonExcecuter_2 = new JButton("Excecute");
		buttonExcecuter_2.setBounds(1861, 880, 41, 25);
		add(buttonExcecuter_2);

		//Affichage des tableaux et scrollPane
		tableFlotteExistantes = new JTable(modeleFlotte);
		JScrollPane scrollPane = new JScrollPane(tableFlotteExistantes);
		scrollPane.setBounds(10, 10, 585, 800);
		add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(685, 10, 585, 800);
		add(scrollPane_1);


		tableRobotsFlotte = new JTable(modeleRobot);
		scrollPane_1.setViewportView(tableRobotsFlotte);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(1360, 10, 550, 400);
		add(scrollPane_2);

		tableAction = new JTable(modeleAction);
		scrollPane_2.setViewportView(tableAction);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(1360, 470, 550, 400);
		add(scrollPane_3);

		tableCapteurs = new JTable(modeleCapteur);
		scrollPane_3.setViewportView(tableCapteurs);
		buttonFleetToRobots.addActionListener(this);
		buttonRobotToSensors.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==buttonFleetToRobots) {

			//rajouter un if selection != 0 qui ne beug pas!!
			modeleRobot.removeAll();
			HashSet<Robot> robs=Syst.getFleets().get(tableFlotteExistantes.getSelectedRow()).getRobots();
			
			
			flotteSelect=tableFlotteExistantes.getSelectedRow();
			modeleRobot.initTable(robs);
		}
		if(e.getSource()==buttonRobotToSensors) {

			robotSelect=tableRobotsFlotte.getSelectedRow();
			modeleAction.removeAll();
			modeleCapteur.removeAll();
			HashSet<Robot> robs=Syst.getFleets().get(flotteSelect).getRobots();
			Iterator<Robot> value = robs.iterator();
			HashSet<Action> acts=new HashSet<Action>();
			HashSet<Sensor> sens=new HashSet<Sensor>();
			Robot temp;
			for (int i=0;i<robotSelect+1;i++) {
				temp=value.next();
				sens=temp.getSensor();
				acts=temp.getAction();
			}
			modeleAction.initTable(acts);
			modeleCapteur.initTable(sens);
		}
	}
}
