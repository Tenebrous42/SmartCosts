package Licenta_Interfata_Package;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Frame_Licenta {

	private JFrame frame;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Licenta window = new Frame_Licenta();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame_Licenta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setSize(400, 400);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cod_Licenta_Class ob1 = new Cod_Licenta_Class();
				
				
				if(textFieldUsername.getText().equals("postgres") && textFieldPassword.getText().equals("qwerty"))
				{
					frame.dispose();
					Frame_dos f2 = new Frame_dos();
					f2.setVisible(true);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Wrong username or password!");
				}
				
				
				//JOptionPane.showMessageDialog(null, textFieldPassword.getText());
			}
		});
		btnNewButton.setBounds(115, 241, 124, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(115, 80, 82, 23);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(115, 144, 56, 14);
		frame.getContentPane().add(lblPassword);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(115, 104, 86, 20);
		frame.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(115, 169, 86, 20);
		frame.getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		lblNewLabel = new JLabel("WELCOME TO SMARTCOSTS");
		lblNewLabel.setBounds(102, 0, 174, 58);
		frame.getContentPane().add(lblNewLabel);
	}
}
