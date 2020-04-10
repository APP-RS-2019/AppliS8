package graphique;

import java.awt.event.ActionEvent;
import javax.swing.*;

import eventModel.AL;

public class Frame extends JFrame implements AL{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
//	private OngletGestion ongletGestion;
//	private OngletModeNormal ongletModeNormal;
//	private  OngletScenario ongletSimulation;
	
	public Frame() {
		super();
		this.setTitle("APP RS 2021");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar = new JMenuBar();	
		
		
		this.initComponent();
		}

	private void initComponent() {
		JMenu fichier = new JMenu("fichier");
		JMenu edition = new JMenu("edition");
		
		//Definition des différents menus
		menuBar.add(fichier);
		menuBar.add(edition);
		
		//Contenu du menu Fichier
		JMenuItem ouvrir = new JMenuItem("ouvrir");
		JMenuItem ouvrirRecent = new JMenuItem("ouvrir récents");
		JMenuItem sauvegarder = new JMenuItem("sauvegarder");
		fichier.add(ouvrir);fichier.add(ouvrirRecent);fichier.add(sauvegarder);
		//Contenu du menu préférence
		
		JMenuItem serveur = new JMenuItem("serveur");
		this.setJMenuBar(menuBar);
		serveur.addActionListener(this);
		edition.add(serveur);
		
		
		
		//Les onglets
		JTabbedPane onglet = new JTabbedPane();
		onglet.add("GESTION", new OngletGestion());
		onglet.add("NORMAL", new OngletModeNormal());
		onglet.add("SCENARIO", new OngletScenario());
		
		this.getContentPane().add(onglet);
		this.setVisible(true);
		
	}

	public static void actualize() {
		/*this.ongletGestion.initComponent();
		this.ongletModeNormal.initComponent();*/
		//ongletSimulation.repaint();
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new Dialog_PrefenceServeur();
	}
}
	
