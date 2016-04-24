import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.Dimension;
import javax.swing.JButton;
import java.awt.Choice;


public class mainAppWindow {

	private JFrame frmGoogleScholarTool;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainAppWindow window = new mainAppWindow();
					window.frmGoogleScholarTool.setVisible(true);
					
					System.setProperty("phantomjs.binary.path", "C:\\phantomjs\\bin\\phantomjs.exe");
					WebDriver driver = new PhantomJSDriver();
					
					//set invisible window size (needs to be done because browser is headless)
					Dimension size = new Dimension(1150,550);
					driver.manage().window().setSize(size);
					
					//go to website
					driver.get("http://www.ram-bay.com");
					
					//print title
					System.out.println(driver.getTitle());
					
					//find html element with id = "signin" and click on it
					driver.findElement(By.id("signin")).click();
					
					//get title of new page
					System.out.println(driver.getTitle());
					
					//go back to homepage
					driver.get("http://www.ram-bay.com");

					//find html element with id = "rego" and click on it
					driver.findElement(By.linkText("Register")).click();
					
					//get title of new page
					System.out.println(driver.getTitle());
					
					//go to google scholar
					driver.get("http://scholar.google.com");
					
					//get title of new page					
					System.out.println(driver.getTitle());
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainAppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGoogleScholarTool = new JFrame();
		frmGoogleScholarTool.setFont(new Font("Dialog", Font.BOLD, 12));
		frmGoogleScholarTool.setTitle("Google Scholar Tool");
		frmGoogleScholarTool.setBounds(100, 100, 999, 646);
		frmGoogleScholarTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmGoogleScholarTool.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		frmGoogleScholarTool.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Journal Title:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 23, 97, 29);
		frmGoogleScholarTool.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(122, 29, 374, 20);
		frmGoogleScholarTool.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblIssn = new JLabel("ISSN:");
		lblIssn.setBounds(77, 68, 56, 16);
		frmGoogleScholarTool.getContentPane().add(lblIssn);
		
		textField_1 = new JTextField();
		textField_1.setBounds(122, 65, 374, 22);
		frmGoogleScholarTool.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(523, 26, 97, 25);
		frmGoogleScholarTool.getContentPane().add(btnSearch);
		
		JButton btnClearAll = new JButton("Clear all");
		btnClearAll.setBounds(523, 64, 97, 25);
		frmGoogleScholarTool.getContentPane().add(btnClearAll);
		
		Choice choice = new Choice();
		choice.add("Google Scholar");
		choice.add("Scopus");
		choice.add("ISI Web of Science");
		choice.setBounds(122, 104, 133, 22);
		frmGoogleScholarTool.getContentPane().add(choice);
		
		JLabel lblData = new JLabel("Data source:");
		lblData.setBounds(40, 110, 73, 16);
		frmGoogleScholarTool.getContentPane().add(lblData);
	}
}
