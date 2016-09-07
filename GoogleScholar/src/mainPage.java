import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private final JLabel lblGoogleScholarSearch = new JLabel("Scholar Search Tool");
	private final JButton btnNewButton = new JButton("Google Scholar");
	private final JButton btnNewButton_1 = new JButton("Scopus");
	private final JButton btnNewButton_2 = new JButton("Search By General Information");
	private final JPanel panel = new JPanel();

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
		btnNewButton.setBounds(54, 310, 217, 203);
		
		frmGoogleScholarTool.getContentPane().add(btnNewButton);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmGoogleScholarTool.dispose();
				
				ScopusSearch AFrame = new ScopusSearch();
				AFrame.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(426, 310, 217, 203);
		
		frmGoogleScholarTool.getContentPane().add(btnNewButton_1);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGoogleScholarTool.dispose();
				
				GeneralSearch GFrame = new GeneralSearch();
				GFrame.setVisible(true);
				
				
			}
		});
		btnNewButton_2.setBounds(813, 310, 217, 203);
		
		frmGoogleScholarTool.getContentPane().add(btnNewButton_2);
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 1074, 166);
		
		frmGoogleScholarTool.getContentPane().add(panel);
		panel.setLayout(null);
		lblGoogleScholarSearch.setForeground(new Color(255, 255, 255));
		lblGoogleScholarSearch.setBounds(358, 56, 390, 57);
		panel.add(lblGoogleScholarSearch);
		lblGoogleScholarSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		
	}
}
