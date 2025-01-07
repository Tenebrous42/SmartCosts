package Licenta_Interfata_Package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Licenta_Interfata_Package.Cod_Licenta_Class;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Frame_sase extends JFrame {

	private JPanel contentPane;
	public static JTable table_final;
	public static JTextField textFieldCostMinim;
	public static JTextField textField_1Unica;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_sase frame = new Frame_sase();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame_sase() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Solutie");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(180, 0, 87, 26);
		contentPane.add(lblNewLabel);
		
		DefaultTableModel model_final = new DefaultTableModel(Frame_dos.nr_depozite + 1, Frame_dos.nr_magazine + 1);
		
		//String[] colum_name = {"C1", "C2", "C3"};
		
		table_final = new JTable();
		table_final.setBounds(42, 26, 367, 126);
		table_final.setModel(model_final);
		contentPane.add(table_final);
		table_final.setVisible(true);
		table_final.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//?
		
		/*
		 * table_final.getColumnModel().getColumn(0).setHeaderValue("newHeader0");
		 * table_final.getColumnModel().getColumn(1).setHeaderValue("newHeader1");
		 * table_final.getColumnModel().getColumn(2).setHeaderValue("newHeader2");
		 * 
		 * table_final.getTableHeader().repaint();
		 */
		
		//setam depozitele
		
		for(int i = 1; i < Frame_dos.nr_depozite + 1; i++)
			Frame_sase.table_final.setValueAt(Frame_patru.table_depozite_nou.getValueAt(i - 1, 0), i, 0);
		
		//setam magazinele
		
		for(int j = 1; j < Frame_dos.nr_magazine + 1; j++)
			Frame_sase.table_final.setValueAt(Frame_5.table_magazine.getValueAt(j - 1, 0), 0, j);
		
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnNewButton.setBounds(264, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Previous");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				dispose();
				Frame_5 f5 = new Frame_5();
				f5.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(64, 227, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Costul minim este: ");
		lblNewLabel_1.setBounds(42, 176, 98, 26);
		contentPane.add(lblNewLabel_1);
		
		textFieldCostMinim = new JTextField();
		textFieldCostMinim.setBounds(42, 196, 111, 20);
		contentPane.add(textFieldCostMinim);
		textFieldCostMinim.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("Solutia este: ");
		lblNewLabel_2.setBounds(265, 182, 88, 14);
		contentPane.add(lblNewLabel_2);
		
		textField_1Unica = new JTextField();
		textField_1Unica.setBounds(264, 196, 133, 20);
		contentPane.add(textField_1Unica);
		textField_1Unica.setColumns(10);
		
		Cod_Licenta_Class.main();

	}
}
