package graphique;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.*;
import action.Action;
import eventModel.AL;
import robot.Robot;
import upperClass.Syst;

public class Dialog_AjoutAction extends JDialog implements AL{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private int flotteSelect;
	private int robotSelect;
	private JTextField textId;
	private JTextField textName;
	private JTextArea textDescription;
	private ModeleDynamiqueAction modeleAction;
	private ModeleDynamiqueRobot modeleRobot;
	
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	public Dialog_AjoutAction(int flotteSelect, int robotSelect,ModeleDynamiqueAction modeleAction,ModeleDynamiqueRobot modeleRobot) {

		this.robotSelect=robotSelect;
		this.modeleAction=modeleAction;
		this.modeleRobot=modeleRobot;

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//this.modeleRobot=modeleRobot;
		this.setBounds(100, 100, 450, 300);
		this.setTitle("Ajout d'actionneur");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblId = new JLabel("Id de l'Actionneur :");
		lblId.setBounds(12, 30, 123, 16);
		contentPanel.add(lblId);

		textId = new JTextField();
		textId.setBounds(140, 27, 116, 22);
		contentPanel.add(textId);
		textId.setColumns(10);

		JLabel lblName = new JLabel("Nom de l'Actionneur :");
		lblName.setBounds(12, 80, 135, 16);
		contentPanel.add(lblName);

		textName = new JTextField();
		textName.setBounds(140, 77, 116, 22);
		contentPanel.add(textName);
		textName.setColumns(10);

		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setBounds(12, 126, 96, 16);
		contentPanel.add(lblDescription);

		textDescription = new JTextArea();
		textDescription.setBounds(140, 123, 190, 50);
		contentPanel.add(textDescription);
		textDescription.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);


		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==okButton) {
			Action action=new Action(textId.getText(), textName.getText(), textDescription.getText());
			HashSet<Robot> robs=Syst.getFleets().get(flotteSelect).getRobots();
			
			Iterator<Robot> valueRobots=robs.iterator();
			Robot rob=null;
			for(int i=0;i<robotSelect+1;i++) {
				rob=valueRobots.next();
			}
			rob.addAction(action);
			modeleAction.addAction(action);
			modeleRobot.initTable(Syst.getFleets().get(flotteSelect).getRobots());//peut etre simplifiable en rajoutant une méthode actualise() dans le modele dynamique
			dispose();
		}
		if(e.getSource()==cancelButton) {
			dispose();
		}
	}
}
