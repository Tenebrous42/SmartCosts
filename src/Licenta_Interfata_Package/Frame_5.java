package Licenta_Interfata_Package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Frame_5 extends JFrame {

	private JPanel contentPane;
	public static JTable table_magazine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_5 frame = new Frame_5();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static boolean Validare_nume_magazine(JTable table_magazine)
	{
		for(int i = 0 ; i < table_magazine.getRowCount(); i++)
		{
			if(table_magazine.getValueAt(i, 0) == null)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean Validare_cerere(JTable table_magazine)
	{
		for(int i = 0 ; i < table_magazine.getRowCount(); i++)
		{
			if( table_magazine.getValueAt(i, 1) == null || Double.parseDouble((String) table_magazine.getValueAt(i, 1)) <= 0)
			{
				return false;
			}
		}
		
		return true;
	}

	
	
	
	/**
	 * Create the frame.
	 */
	public Frame_5() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 90, 2, 2);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Cererea Magazinelor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(160, 0, 163, 22);
		contentPane.add(lblNewLabel);
		
		
		
		DefaultTableModel model_magazin = new DefaultTableModel(Frame_dos.nr_magazine, 2);
		
		table_magazine = new JTable();
		table_magazine.setBounds(49, 50, 354, 177);
		table_magazine.setModel(model_magazin);
		contentPane.add(table_magazine);
		table_magazine.setVisible(true);
		table_magazine.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//?
		
		
		
		
		
		JButton btnNewButton = new JButton("Calculate Solution");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				if(Validare_nume_magazine(table_magazine) == false)
				{
					JOptionPane.showMessageDialog(null, "Nume magazin omis !");
				}
				else
				{
					if(Validare_cerere(table_magazine) == false)
					{
						JOptionPane.showMessageDialog(null, "Date de intrare invalide pentru cerere!");
					}
					else
					{
						dispose();
						Frame_sase f6 = new Frame_sase();
						f6.setVisible(true);
					}
				}
				
			}
		});
		btnNewButton.setBounds(239, 238, 144, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Previous");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				dispose();
				Frame_patru f4 = new Frame_patru();
				f4.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(60, 238, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		JLabel lblNewLabel_1 = new JLabel("Nume magazin");
		lblNewLabel_1.setBounds(60, 25, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cantitate ceruta");
		lblNewLabel_2.setBounds(294, 25, 109, 14);
		contentPane.add(lblNewLabel_2);
	}
}
