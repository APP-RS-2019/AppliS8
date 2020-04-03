package graphique;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eventModel.AL;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;

public class Dialog_PrefenceServeur extends JDialog implements AL{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textPort;
	private JTextField textIp;

	private JButton btnModifierIp = new JButton("Modifier IP");
	private JButton btnModifierPort = new JButton("Modifier Port");
	private JButton btnTestConnexion = new JButton("Test Connexion");
	private JButton btnConnexion = new JButton("Connexion");
	private JButton okButton = new JButton("OK");

	public Dialog_PrefenceServeur() {
		this.setBounds(100, 100, 450, 300);
		this.setTitle("Preferences Serveur");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("IP : ");
		lblNewLabel_1.setBounds(44, 58, 24, 16);
		contentPanel.add(lblNewLabel_1);


		JLabel lblNewLabel = new JLabel("Preferences Serveur");
		lblNewLabel.setBounds(172, 10, 117, 16);
		contentPanel.add(lblNewLabel);

		JLabel lblPort = new JLabel("Port : ");
		lblPort.setBounds(44, 106, 56, 16);
		contentPanel.add(lblPort);

		textPort = new JTextField();
		//textPort.setText(Integer.toString(Syst.getClientsocket().getPort()));
		textPort.setBounds(83, 103, 34, 22);
		contentPanel.add(textPort);
		textPort.setColumns(10);

		textIp = new JTextField();
		//textIp.setText(Syst.getClientsocket().getIp());
		textIp.setBounds(80, 55, 116, 22);
		contentPanel.add(textIp);
		textIp.setColumns(10);


		btnModifierIp.setBounds(299, 54, 105, 25);
		contentPanel.add(btnModifierIp);


		btnModifierPort.setBounds(299, 102, 105, 25);
		contentPanel.add(btnModifierPort);

		btnTestConnexion.setBounds(258, 152, 130, 25);
		/*if(Syst.getClientsocket().isOpen()) {
			btnTestConnexion.setBackground(Color.green);
		}
		else if (Syst.getClientsocket().isOpen()==false) {
			btnTestConnexion.setBackground(Color.red);
		}*/
		contentPanel.add(btnTestConnexion);

		btnConnexion.setBounds(45, 152, 97, 25);
		contentPanel.add(btnConnexion);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		btnModifierIp.addActionListener(this);
		btnModifierPort.addActionListener(this);
		btnTestConnexion.addActionListener(this);
		btnConnexion.addActionListener(this);
		okButton.addActionListener(this);

		btnTestConnexion.setEnabled(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnModifierIp) {
			try {
				//Syst.getClientsocket().setIp(textIp.getText());
				btnTestConnexion.setBackground(Color.red);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		if(e.getSource()==btnModifierPort) {
			try {
				//Syst.getClientsocket().setPort(Integer.parseInt(textPort.getText()));
				btnTestConnexion.setBackground(Color.red);
			} catch (NumberFormatException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		if(e.getSource()==btnTestConnexion) {
			//  if (Syst.getClientsocket().isOpen()) {
			//					btnTestConnection.setBackground(Color.green);
			//				}
			//				else {
			//					btnTestConnection.setBackground(Color.red);
			//				}
		}
		if(e.getSource()==btnConnexion) {
			/*if(Syst.getClientsocket().isOpen()) {
			try {
				Syst.getClientsocket().disconnect();
				Syst.getClientsocket().connect("");
				if(Syst.getClientsocket().isOpen()) {
					btnTestConnexion.setBackground(Color.green);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (Syst.getClientsocket().isOpen()==false) {
			try {
				Syst.getClientsocket().connect("");
				if(Syst.getClientsocket().isOpen()) {
					btnTestConnexion.setBackground(Color.green);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		}
		if(e.getSource()==okButton) {
			dispose();
		}
	}
}
