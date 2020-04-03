package graphique;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eventModel.AL;
import robot.Fleet;
import upperClass.Syst;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Dialog_AjoutFlotte extends JDialog implements AL{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textId;
	private JTextField textName;
	private ModeleDynamiqueFlotte modeleFlotte;
	
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");


	public Dialog_AjoutFlotte(ModeleDynamiqueFlotte modeleFlotte) {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.modeleFlotte=modeleFlotte;
		this.setBounds(100, 100, 450, 300);
		this.setTitle("Ajout d'une flotte");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblId = new JLabel("Id de la Flotte :");
		lblId.setBounds(12, 30, 88, 16);
		contentPanel.add(lblId);

		textId = new JTextField();
		textId.setBounds(130, 27, 116, 22);
		contentPanel.add(textId);
		textId.setColumns(10);

		JLabel lblName = new JLabel("Nom de la Flotte :");
		lblName.setBounds(12, 107, 103, 16);
		contentPanel.add(lblName);

		textName = new JTextField();
		textName.setBounds(130, 104, 116, 22);
		contentPanel.add(textName);
		textName.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		//okButton.setActionCommand("OK");
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
			Fleet fleet=new Fleet(textId.getText(), textName.getText());
			Syst.getFleets().add(fleet);
			modeleFlotte.addFlotte(fleet);
			dispose();
		}
		if(e.getSource()==cancelButton) {
			dispose();
		}
	}
}
