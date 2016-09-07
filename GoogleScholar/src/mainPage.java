import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Panel;
import java.awt.FlowLayout;

public class mainPage extends JFrame {

	private JFrame frmGoogleScholarTool;
	
	
	URL url = this.getClass().getResource("/icon/scholar.png");
	Icon GSicon = new ImageIcon(url);
	
	URL sUrl = this.getClass().getResource("/icon/scopus.png");
	Icon SpIcon = new ImageIcon(sUrl);
	
	private final JLabel lblGoogleScholarSearch = new JLabel("Scholar Search Tool");
	private final JButton btnNewButton = new JButton("Google Scholar",GSicon);
	private final JButton btnNewButton_1 = new JButton("Scopus",SpIcon);
	private final JPanel panel = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Run Scholar Search Tool");
	private final JLabel lblNewLabel_1 = new JLabel("Select one search engine to start the program");
	private final JLabel lblNewLabel_2 = new JLabel("Copyright (C) 2016 UOW GScholar Project. All Rights Reserved. Version 1.0.0");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainPage frame = new mainPage();
					frame.frmGoogleScholarTool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainPage() {
		initialize();
	}
	
	private void initialize() {
		frmGoogleScholarTool = new JFrame();
		frmGoogleScholarTool.setFont(new Font("Dialog", Font.BOLD, 12));
		frmGoogleScholarTool.setTitle("Scholar Search Tool");
		frmGoogleScholarTool.setBounds(100, 100, 1074, 727);
		frmGoogleScholarTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGoogleScholarTool.setLocationRelativeTo(null);
		
		frmGoogleScholarTool.getContentPane().setBackground(new Color(204,255,255));
		
		
		frmGoogleScholarTool.getContentPane().setLayout(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmGoogleScholarTool.dispose();
				
				GoogleScholarSearch jurFrame = new GoogleScholarSearch();
				
			}
		});
		btnNewButton.setBounds(140, 244, 217, 76);
		
		frmGoogleScholarTool.getContentPane().add(btnNewButton);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmGoogleScholarTool.dispose();
				
				ScopusSearch AFrame = new ScopusSearch();
				AFrame.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(140, 359, 217, 76);
		
		frmGoogleScholarTool.getContentPane().add(btnNewButton_1);
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 1074, 166);
		
		frmGoogleScholarTool.getContentPane().add(panel);
		panel.setLayout(null);
		lblGoogleScholarSearch.setForeground(new Color(255, 255, 255));
		lblGoogleScholarSearch.setBounds(358, 56, 390, 57);
		panel.add(lblGoogleScholarSearch);
		lblGoogleScholarSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setBounds(498, 228, 340, 27);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setBounds(498, 299, 382, 45);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(555, 664, 492, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_2);
		
	}
}
