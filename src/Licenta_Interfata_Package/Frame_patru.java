package Licenta_Interfata_Package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class Frame_patru extends JFrame {

	private JPanel contentPane;
	// public static JTable table_depozite;
	public static JTable table_depozite_nou;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_patru frame = new Frame_patru();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static boolean Validare_nume_depozite(JTable table_depozite_nou)
	{
		for(int i = 0 ; i < table_depozite_nou.getRowCount(); i++)
		{
			if(table_depozite_nou.getValueAt(i, 0) == null)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean Validare_oferta(JTable table_depozite_nou)
	{
		for(int i = 0 ; i < table_depozite_nou.getRowCount(); i++)
		{
			if( table_depozite_nou.getValueAt(i, 1) == null || Double.parseDouble((String) table_depozite_nou.getValueAt(i, 1)) <= 0)
			{
				return false;
			}
		}
		
		return true;
	}

	
	/**
	 * Create the frame.
	 */
	public Frame_patru() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 100, 2, 2);
		contentPane.add(scrollPane);

		/*
		 * DefaultTableModel model_depozite = new
		 * DefaultTableModel(Frame_dos.nr_depozite, 2); table_depozite = new JTable();
		 * table_depozite.setModel(model_depozite);
		 * 
		 * table_depozite.setVisible(true);
		 * System.out.println("am ajuns aici in quatros");
		 * 
		 * table_depozite.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//?
		 * 
		 * //table_magazine.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//?
		 * scrollPane.setViewportView(table_depozite);
		 */

		DefaultTableModel model_test = new DefaultTableModel(Frame_dos.nr_depozite, 2);

		table_depozite_nou = new JTable();
		table_depozite_nou.setBounds(69, 50, 321, 159);
		table_depozite_nou.setModel(model_test);

		contentPane.add(table_depozite_nou);

		table_depozite_nou.setVisible(true);
		table_depozite_nou.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);// ?

		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				 

				if(Validare_nume_depozite(table_depozite_nou) == false)
				{
					JOptionPane.showMessageDialog(null, "Nume depozit omis !");
				}
				else
				{
					if(Validare_oferta(table_depozite_nou) == false)
					{
						JOptionPane.showMessageDialog(null, "Date de intrare invalide pentru oferta!");
					}
					else
					{
						dispose();
						Frame_5 f5 = new Frame_5();
						f5.setVisible(true);
					}
				}
			}
		});
		btnNewButton.setBounds(273, 220, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Oferta depozitelor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(167, 0, 115, 14);
		contentPane.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("Previous");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				Frame_tres f3 = new Frame_tres();
				f3.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(69, 220, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Nume depozit");
		lblNewLabel_1.setBounds(69, 29, 89, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Cant. din depozit");
		lblNewLabel_2.setBounds(266, 25, 96, 14);
		contentPane.add(lblNewLabel_2);
	}
}
