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


public class OngletGestion extends JPanel implements AL{

	private static final long serialVersionUID = 1L;
	private JTable tableFlotteExistantes;
	private JTable tableRobotsFlotte;
	private JTable tableAction;
	private JTable tableCapteur;
	private ModeleDynamiqueFlotte modeleFlotte;
	private ModeleDynamiqueRobot modeleRobot;
	private ModeleDynamiqueAction modeleAction;
	private ModeleDynamiqueCapteur modeleCapteur;
	private int flotteSelect;
	private int robotSelect;

	private JButton btnAjouterFlotte = new JButton("Ajouter");
	private JButton btnSupprimerFlotte = new JButton("Supprimer");
	private JButton buttonFleetToRobots = new JButton("=>");
	private JButton btnAjouterRobot = new JButton("Ajouter");
	private JButton btnSupprimerRobot = new JButton("Supprimer");
	private JButton buttonRobotToSensors = new JButton("=>");
	private JButton btnAjouterAction = new JButton("+");
	private JButton btnSupprimerAction = new JButton("-");
	private JButton buttonAjouterCapteur = new JButton("+");
	private JButton btnSupprimerCapteur = new JButton("-");

	public OngletGestion() {
		setLayout(null);
		this.modeleFlotte=new ModeleDynamiqueFlotte(Syst.getFleets());
		this.modeleRobot=new ModeleDynamiqueRobot();
		this.modeleAction=new ModeleDynamiqueAction();
		this.modeleCapteur=new ModeleDynamiqueCapteur();
		this.flotteSelect=-1; this.robotSelect=-1;
		btnAjouterFlotte.setBounds(385, 820, 100, 25);

		this.add(btnAjouterFlotte);

		btnSupprimerFlotte.setBounds(490, 820, 100, 25);
		this.add(btnSupprimerFlotte);


		buttonFleetToRobots.setBounds(615, 395, 50, 25);
		add(buttonFleetToRobots);


		btnAjouterRobot.setBounds(1055, 820, 100, 25);
		add(btnAjouterRobot);

		btnSupprimerRobot.setBounds(1160, 820, 100, 25);
		this.add(btnSupprimerRobot);

		buttonRobotToSensors.setBounds(1290, 395, 50, 25);
		add(buttonRobotToSensors);

		btnAjouterAction.setBounds(1861, 420, 41, 25);
		add(btnAjouterAction);

		btnSupprimerAction.setBounds(1815, 420, 41, 25);
		add(btnSupprimerAction);

		buttonAjouterCapteur.setBounds(1861, 880, 41, 25);
		add(buttonAjouterCapteur);

		btnSupprimerCapteur.setBounds(1815, 880, 41, 25);
		add(btnSupprimerCapteur);



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

		tableCapteur = new JTable(modeleCapteur);
		scrollPane_3.setViewportView(tableCapteur);

		btnAjouterFlotte.addActionListener(this);
		btnSupprimerFlotte.addActionListener(this);
		buttonFleetToRobots.addActionListener(this);
		btnAjouterRobot.addActionListener(this);
		btnSupprimerRobot.addActionListener(this);
		buttonRobotToSensors.addActionListener(this);
		btnAjouterAction.addActionListener(this);
		btnSupprimerAction.addActionListener(this);
		buttonAjouterCapteur.addActionListener(this);
		btnSupprimerCapteur.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnAjouterFlotte) {
			new Dialog_AjoutFlotte(modeleFlotte);
		}
		if(e.getSource()==btnSupprimerFlotte) {
			if(tableFlotteExistantes.getSelectedRow()>-1) {
				if(tableFlotteExistantes.getSelectedRow()!=flotteSelect) {
					Syst.getFleets().remove(tableFlotteExistantes.getSelectedRow());
					modeleFlotte.removeFlotte(tableFlotteExistantes.getSelectedRow());
				}
				else {
					new ErrorDialog("La flotte selectionné est encore active");
				}
			}
			else {
				new ErrorDialog("Aucune ligne selecionnée");
			}
		}
		if(e.getSource()==buttonFleetToRobots) {
			if(tableFlotteExistantes.getSelectedRow()>-1) {
				modeleRobot.removeAll();
				HashSet<Robot> robs=Syst.getFleets().get(tableFlotteExistantes.getSelectedRow()).getRobots();

				flotteSelect=tableFlotteExistantes.getSelectedRow();
				modeleRobot.initTable(robs);
			}
		}
		if(e.getSource()==btnAjouterRobot) {
			new Dialog_AjoutRobot(flotteSelect,modeleRobot);
		}
		if(e.getSource()==btnSupprimerRobot) {

			if (tableRobotsFlotte.getSelectedRow()>-1) {//verifier l'exclu pour avoir une ligne selectionnée
				HashSet<Robot> robs=Syst.getFleets().get(flotteSelect).getRobots();
				Iterator<Robot> value=robs.iterator();
				Robot rob=null;
				for(int i=0;i<tableRobotsFlotte.getSelectedRow()+1;i++) {
					rob=value.next();
				}
				if (tableRobotsFlotte.getSelectedRow()==robotSelect) {
					modeleCapteur.removeAll();
					modeleAction.removeAll();
				}
				Syst.getFleets().get(flotteSelect).getRobots().remove(rob);
				modeleRobot.removeRobot(tableRobotsFlotte.getSelectedRow());
			}
			else {
				new ErrorDialog("Aucune ligne selecionnée");
			}
		}
		if(e.getSource()==buttonRobotToSensors) {
			if(tableRobotsFlotte.getSelectedRow()>-1) {
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
		if(e.getSource()==btnAjouterAction) {
			new Dialog_AjoutAction(flotteSelect,robotSelect,modeleAction, modeleRobot);
		}
		if(e.getSource()==btnSupprimerAction) {

			if(tableAction.getSelectedRow()>-1){
				HashSet<Robot> robs=Syst.getFleets().get(flotteSelect).getRobots();
				Iterator<Robot> valueRobots=robs.iterator();
				Robot rob=null;
				for(int i=0;i<robotSelect+1;i++) {
					rob=valueRobots.next();
				}
				HashSet<Action> acts=rob.getAction();
				Iterator<Action> valueAction=acts.iterator();
				Action act=null;
				for(int i=0;i<tableAction.getSelectedRow()+1;i++) {
					act=valueAction.next();
				}
				rob.getAction().remove(act);
				modeleAction.removeAction(tableAction.getSelectedRow());
				modeleRobot.initTable(Syst.getFleets().get(flotteSelect).getRobots());//peut etre simplifiable en rajoutant une méthode actualise() dans le modele dynamique
			}
			else {
				new ErrorDialog("Aucune ligne selecionnée");
			}
		}
		if(e.getSource()==buttonAjouterCapteur) {
			new Dialog_AjoutCapteur(flotteSelect,robotSelect,modeleCapteur,modeleRobot);
		}
		if(e.getSource()==btnSupprimerCapteur) {
			if(tableCapteur.getSelectedRow()>-1){
				HashSet<Robot> robs=Syst.getFleets().get(flotteSelect).getRobots();
				Iterator<Robot> valueRobots=robs.iterator();
				Robot rob=null;
				for(int i=0;i<robotSelect+1;i++) {
					rob=valueRobots.next();
				}
				HashSet<Sensor> acts=rob.getSensor();
				Iterator<Sensor> valueAction=acts.iterator();
				Sensor capt=null;
				for(int i=0;i<tableAction.getSelectedRow()+1;i++) {
					capt=valueAction.next();
				}
				rob.getSensor().remove(capt);
				modeleCapteur.removeCapteur(tableCapteur.getSelectedRow());
				modeleRobot.initTable(Syst.getFleets().get(flotteSelect).getRobots());//peut etre simplifiable en rajoutant une méthode actualise() dans le modele dynamique
			}
			else {
				new ErrorDialog("Aucune ligne selecionnée");
			}
		}
	}
}
