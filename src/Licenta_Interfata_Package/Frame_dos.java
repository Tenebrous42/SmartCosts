package Licenta_Interfata_Package;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Frame_dos extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldNrDepozite;
	private JLabel lblNewLabel;
	private JLabel lblIntroduducetiNumarulDe;
	private JTextField textFieldNrMagazine;
	
	/////////////////////////////
	public static int nr_depozite;
	public static int nr_magazine;
	public static String unitate_monetara_costuri;
	private JTextField textFieldUMCost;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_dos frame = new Frame_dos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public static boolean Validare_depozite_magazine(int m, int n)
	{
		if(m <= 0 || n <= 0)
		{
			return false;
		}
		///if(n sau m nu sunt nr naturale avem eroare)
		return true;
	}
	
	public static boolean Validare_completare_date(int m, int n)
	{
		if(m <= 0 || n <= 0)
		{
			return false;
		}
		///if(n sau m nu sunt nr naturale avem eroare)
		return true;
	}
	/**
	 * Create the frame.
	 */
	public Frame_dos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtFieldNrDepozite = new JTextField();
		txtFieldNrDepozite.setBounds(51, 48, 115, 20);
		contentPane.add(txtFieldNrDepozite);
		txtFieldNrDepozite.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(txtFieldNrDepozite.getText().isEmpty() || textFieldNrMagazine.getText().isEmpty() || textFieldUMCost.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Date de intrare necompletate!");
				}
				else
				{
					
					nr_depozite = Integer.parseInt( txtFieldNrDepozite.getText());
					nr_magazine = Integer.parseInt( textFieldNrMagazine.getText());
					unitate_monetara_costuri = textFieldUMCost.getText();
					
					if(Validare_depozite_magazine(nr_depozite, nr_magazine) == false)
					{
						JOptionPane.showMessageDialog(null, "Date de intrare pentru depozite sau magazine gresite!");
					}
					else
					{
						//System.out.println("nr depozite: " + nr_depozite + ", nr magazine: " + nr_magazine);
						//pt frame_tres tre sa fac 
						System.out.println("am ajus aici");
						dispose();
						Frame_tres f3 = new Frame_tres();
						f3.setVisible(true);
					}
					
				}	
				
			}
		});
		btnNext.setBounds(158, 200, 89, 23);
		contentPane.add(btnNext);
		
		lblNewLabel = new JLabel("Introduduceti numarul de depozite ale firmei dvs.");
		lblNewLabel.setBounds(51, 27, 311, 14);
		contentPane.add(lblNewLabel);
		
		lblIntroduducetiNumarulDe = new JLabel("Introduduceti numarul de magazine ale firmei dvs.");
		lblIntroduducetiNumarulDe.setBounds(51, 79, 311, 14);
		contentPane.add(lblIntroduducetiNumarulDe);
		
		textFieldNrMagazine = new JTextField();
		textFieldNrMagazine.setBounds(51, 98, 115, 20);
		contentPane.add(textFieldNrMagazine);
		textFieldNrMagazine.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Introduceti unitatea de masura a costurilor.");
		lblNewLabel_1.setBounds(51, 155, 311, 14);
		contentPane.add(lblNewLabel_1);
		
		textFieldUMCost = new JTextField();
		textFieldUMCost.setBounds(51, 170, 86, 20);
		contentPane.add(textFieldUMCost);
		textFieldUMCost.setColumns(10);
	}
}
