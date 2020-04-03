package graphique;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eventModel.AL;
import robot.*;
import upperClass.Syst;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Dialog_AjoutRobot extends JDialog implements AL{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textId;
	private JTextField textName;
	private ModeleDynamiqueRobot modeleRobot;
	private int flotteSelect;
	
	private String[] listRobots = {"Pepper","Nao","Robotino","Pekee"};
	private JComboBox<String> comboBox = new JComboBox<String>(listRobots);
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");


	public Dialog_AjoutRobot(int flotteSelect, ModeleDynamiqueRobot modeleRobot) {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.modeleRobot=modeleRobot;
		this.flotteSelect=flotteSelect;
		this.setBounds(100, 100, 450, 300);
		this.setTitle("Ajout d'un robot");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblId = new JLabel("Id du Robot :");
		lblId.setBounds(12, 30, 88, 16);
		contentPanel.add(lblId);

		textId = new JTextField();
		textId.setBounds(130, 27, 116, 22);
		contentPanel.add(textId);
		textId.setColumns(10);

		JLabel lblName = new JLabel("Nom du Robot :");
		lblName.setBounds(12, 87, 103, 16);
		contentPanel.add(lblName);

		textName = new JTextField();
		textName.setBounds(130, 84, 116, 22);
		contentPanel.add(textName);
		textName.setColumns(10);

		JLabel lblType = new JLabel("Type :");
		lblType.setBounds(12, 133, 56, 16);
		contentPanel.add(lblType);
		
		comboBox.setBounds(130, 130, 88, 22);
		contentPanel.add(comboBox);
		comboBox.setSelectedIndex(0);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
				
				//A comprendre pk le switch ne marche pas 
				
				//				switch(comboBox.getSelectedItem().toString()) {
				//				case listRobots[0]:
				//					System.err.println("pepper");
				//					Pepper pepper=new Pepper(textId.getText(), textName.getText());
				//					Syst.getFleets().get(flotteSelect).addRobot(pepper);;
				//					modeleRobot.addRobot(pepper);
				//					dispose();
				//				case "Nao":
				//					System.err.println("nao");
				//					Nao nao=new Nao(textId.getText(), textName.getText());
				//					Syst.getFleets().get(flotteSelect).addRobot(nao);;
				//					modeleRobot.addRobot(nao);
				//					dispose();
				//				case "Robotino":
				//					System.err.println("robotino");
				//					Robotino robotino=new Robotino(textId.getText(), textName.getText());
				//					Syst.getFleets().get(flotteSelect).addRobot(robotino);;
				//					modeleRobot.addRobot(robotino);
				//					dispose();
				//				case "Pekee":
				//					System.err.println("pekee");
				//					Peekee1R pekee=new Peekee1R(textId.getText(), textName.getText());
				//					Syst.getFleets().get(flotteSelect).addRobot(pekee);;
				//					modeleRobot.addRobot(pekee);
				//					dispose();
				//
				//				}
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==okButton) {
			try{
			if (comboBox.getSelectedItem().toString().equals("Pepper")) {
				Pepper pepper=new Pepper(textId.getText(), textName.getText());
				Syst.getFleets().get(flotteSelect).addRobot(pepper);;
				modeleRobot.addRobot(pepper);
				dispose();
			}
			else if (comboBox.getSelectedItem().toString().equals("Nao")) {
				Nao nao=new Nao(textId.getText(), textName.getText());
				Syst.getFleets().get(flotteSelect).addRobot(nao);;
				modeleRobot.addRobot(nao);
				dispose();
			}
			else if (comboBox.getSelectedItem().toString().equals("Robotino")) {
				Robotino robotino=new Robotino(textId.getText(), textName.getText());
				Syst.getFleets().get(flotteSelect).addRobot(robotino);;
				modeleRobot.addRobot(robotino);
				dispose();
			}
			else if (comboBox.getSelectedItem().toString().equals("Pekee")) {
				Peekee1R pekee=new Peekee1R(textId.getText(), textName.getText());
				Syst.getFleets().get(flotteSelect).addRobot(pekee);;
				modeleRobot.addRobot(pekee);
				dispose();
			}}
			catch(Exception ArrayIndexOutOfBoundsException) {
				new ErrorDialog("Pas de flotte sélectionnée");
			}
		}
		if(e.getSource()==cancelButton) {
			dispose();
		}
	}
}
