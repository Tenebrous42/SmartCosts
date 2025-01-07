package Licenta_Interfata_Package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class Frame_tres extends JFrame {

	private JPanel contentPane;
	public static JTable table_costs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_tres frame = new Frame_tres();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static boolean Validare_costuri_initiale(JTable table_costs)
	{
		for (int i = 0; i < table_costs.getRowCount(); i++)
			for (int j = 0; j < table_costs.getColumnCount(); j++)
				if(table_costs.getValueAt(i, j) == null || Double.parseDouble((String) table_costs.getValueAt(i, j)) <= 0)
					return false;

		return true;
	}
	
	/**
	 * Create the frame.
	 */
	public Frame_tres() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 414, 227);
		contentPane.add(scrollPane);
		
		//System.out.println("Tres: nr depozite: " + Frame_dos.nr_depozite + ", nr magazine: " + Frame_dos.nr_magazine);
		DefaultTableModel model_tab = new DefaultTableModel(Frame_dos.nr_depozite, Frame_dos.nr_magazine);
		
		table_costs = new JTable();
		table_costs.setModel(model_tab);
		//System.out.println("tres depozite: " + Frame_dos.nr_depozite + ", " + Frame_dos.nr_magazine);
		table_costs.setVisible(true);
		table_costs.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);//?
		
		scrollPane.setViewportView(table_costs);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				
				if(Validare_costuri_initiale(table_costs) == false)
				{
					JOptionPane.showMessageDialog(null, "Date de intrare invalide pentru costuri!");
				}
				else
				{
					dispose();
					Frame_patru f4 = new Frame_patru();
					f4.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(245, 255, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Costurile unitare");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(170, -1, 100, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Previous");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				dispose();
				Frame_dos f2 = new Frame_dos();
				f2.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(68, 255, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
