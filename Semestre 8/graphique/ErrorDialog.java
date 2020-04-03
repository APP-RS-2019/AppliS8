package graphique;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import eventModel.AL;

public class ErrorDialog extends JDialog implements AL{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private JButton okButton = new JButton("OK");

	public ErrorDialog(String erreur) {
		this.setTitle("Erreur");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);

		JTextField message=new JTextField(erreur);
		message.setBounds(0, 0, 432, 218);
		message.setEditable(false);
		getContentPane().add(message);

		okButton.setSize(55, 25);
		okButton.setLocation(375, 222);
		okButton.addActionListener(this);
		getContentPane().add(okButton);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}

}
