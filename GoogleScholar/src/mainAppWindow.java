import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

import org.openqa.selenium.Dimension;
import javax.swing.JButton;
import java.awt.Choice;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class mainAppWindow {

	private JFrame frmGoogleScholarTool;
	private JTextField textField;
	private JTextField textField_1;

	//Menu Bar and Menu Item
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmNewMenuItem = new JMenuItem("Import Data");
	private final JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save as CSV");
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	private final JMenu mnEdit = new JMenu("Edit");
	private final JMenuItem mntmCut = new JMenuItem("Cut");
	private final JMenuItem mntmCopy = new JMenuItem("Copy");
	private final JMenuItem mntmPaste = new JMenuItem("Paste");
	private final JMenuItem mntmDelete = new JMenuItem("Delete");
	private final JMenuItem mntmCopyResult = new JMenuItem("Copy Result");
	private final JMenu mnView = new JMenu("View");
	private final JMenuItem mntmJournalImpact = new JMenuItem("Journal Impact");
	private final JMenuItem mntmAuthorImapct = new JMenuItem("Author Imapct");
	private final JMenuItem mntmGeneralCitationSearch = new JMenuItem("General citation search");
	private final JMenu mnTool = new JMenu("Tool");
	private final JMenuItem mntmNewMenuItem_2 = new JMenuItem("Clear data");
	private final JMenuItem mntmClearCache = new JMenuItem("Clear Cache");
	private final JMenuItem mntmP = new JMenuItem("Preference");
	private final JMenu mnHelp = new JMenu("Help");
	private final JMenuItem mntmAboutThisProgram = new JMenuItem("About this program");
	private final JLabel lblNewLabel_1 = new JLabel("Year of Publication between");
	private final JLabel lblNewLabel_2 = new JLabel("Exculde words");
	private final JTextField textField_2 = new JTextField();
	private final JTextField textField_3 = new JTextField();
	private final JLabel lblAnd = new JLabel("to");
	private final JTextField textField_4 = new JTextField();
	private final JLabel lblResult = new JLabel("Result",SwingConstants.CENTER);
	private final JLabel lblNewLabel_3 = new JLabel("Papers:");
	private final JLabel lblNewLabel_4 = new JLabel("Citation: ");
	private final JLabel lblYears = new JLabel("Years: ");
	private final JLabel lblNewLabel_5 = new JLabel("Cites/Year:");
	private final JLabel PaperLabel = new JLabel("");
	private final JLabel citationLabel = new JLabel("");
	private final JLabel yearLabel = new JLabel("");
	private final JLabel citeYearLabel = new JLabel("");
	private final JLabel lblNewLabel_6 = new JLabel("Cites/Paper:");
	private final JLabel lblCites = new JLabel("Cites/Author: ");
	private final JLabel lblPaperauthor = new JLabel("Paper/Author:");
	private final JLabel lblAuthorspaper = new JLabel("Authors/Paper:");
	private final JLabel citesPaperLabel = new JLabel("");
	private final JLabel citesAuthorLabel = new JLabel("");
	private final JLabel paperAuthorLabel = new JLabel("");
	private final JLabel authorPaperLabel = new JLabel("");
	private final JLabel lblHindex = new JLabel("h-Index:");
	private final JLabel lblNewLabel_7 = new JLabel("g-Index");
	private final JLabel lblNewLabel_8 = new JLabel("hI,norm:");
	private final JLabel lblNewLabel_9 = new JLabel("hI, annual:");
	private final JLabel hIndexLabel = new JLabel("");
	private final JLabel gIndexLabel = new JLabel("");
	private final JLabel hinormLabel = new JLabel("");
	private final JLabel hIAnnualLabel = new JLabel("");
	private final JButton btnHelp = new JButton("Help");
	
	
	
	
	
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
		textField_4.setBounds(323, 144, 67, 28);
		textField_4.setColumns(10);
		textField_3.setBounds(226, 144, 56, 28);
		textField_3.setColumns(10);
		textField_2.setBounds(146, 104, 738, 28);
		textField_2.setColumns(10);
		frmGoogleScholarTool = new JFrame();
		frmGoogleScholarTool.setFont(new Font("Dialog", Font.BOLD, 12));
		frmGoogleScholarTool.setTitle("Google Scholar Tool");
		frmGoogleScholarTool.setBounds(100, 100, 999, 646);
		frmGoogleScholarTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//JMenuBar menuBar = new JMenuBar();
		frmGoogleScholarTool.setJMenuBar(menuBar);
		
		//JMenu mnNewMenu = new JMenu("File");
		//menuBar.add(mnNewMenu);
		
		/*JMenuItem mntmExit = new JMenuItem("Exit");
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
		*/
		menuBar.add(mnFile);
		
		mnFile.add(mntmNewMenuItem);
		
		mnFile.add(mntmNewMenuItem_1);
		
		mnFile.add(mntmExit);
		
		menuBar.add(mnEdit);
		
		mnEdit.add(mntmCut);
		
		mnEdit.add(mntmCopy);
		
		mnEdit.add(mntmPaste);
		
		mnEdit.add(mntmDelete);
		
		mnEdit.add(mntmCopyResult);
		
		menuBar.add(mnView);
		
		mnView.add(mntmJournalImpact);
		
		mnView.add(mntmAuthorImapct);
		
		mnView.add(mntmGeneralCitationSearch);
		
		menuBar.add(mnTool);
		
		mnTool.add(mntmClearCache);
		
		mnTool.add(mntmNewMenuItem_2);
		
		mnTool.add(mntmP);
		
		menuBar.add(mnHelp);
		
		mnHelp.add(mntmAboutThisProgram);
		
		
		
		
		
		
		
		frmGoogleScholarTool.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Journal Title:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(43, 24, 97, 29);
		frmGoogleScholarTool.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(136, 29, 748, 20);
		frmGoogleScholarTool.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblIssn = new JLabel("ISSN:");
		lblIssn.setBounds(43, 68, 56, 16);
		frmGoogleScholarTool.getContentPane().add(lblIssn);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 65, 748, 22);
		frmGoogleScholarTool.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(896, 29, 97, 25);
		frmGoogleScholarTool.getContentPane().add(btnSearch);
		
		JButton btnClearAll = new JButton("Clear all");
		btnClearAll.setBounds(896, 86, 97, 25);
		frmGoogleScholarTool.getContentPane().add(btnClearAll);
		
		Choice choice = new Choice();
		choice.add("Google Scholar");
		choice.add("Scopus");
		choice.add("ISI Web of Science");
		choice.setBounds(149, 182, 133, 22);
		frmGoogleScholarTool.getContentPane().add(choice);
		
		JLabel lblData = new JLabel("Data source:");
		lblData.setBounds(43, 188, 84, 16);
		frmGoogleScholarTool.getContentPane().add(lblData);
		lblNewLabel_1.setBounds(43, 150, 181, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setBounds(43, 110, 97, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_2);
		
		frmGoogleScholarTool.getContentPane().add(textField_2);
		
		frmGoogleScholarTool.getContentPane().add(textField_3);
		lblAnd.setBounds(291, 150, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblAnd);
		
		frmGoogleScholarTool.getContentPane().add(textField_4);
		lblResult.setForeground(Color.BLACK);
		lblResult.setBackground(new Color(255, 255, 255));
		lblResult.setOpaque(true);
		lblResult.setBounds(6, 227, 987, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblResult);
		lblNewLabel_3.setBounds(43, 255, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_3);
		lblNewLabel_4.setBounds(43, 283, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_4);
		lblYears.setBounds(43, 305, 42, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblYears);
		lblNewLabel_5.setBounds(43, 329, 70, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_5);
		PaperLabel.setBounds(95, 255, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(PaperLabel);
		citationLabel.setBounds(105, 283, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(citationLabel);
		yearLabel.setBounds(95, 305, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(yearLabel);
		citeYearLabel.setBounds(121, 329, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(citeYearLabel);
		lblNewLabel_6.setBounds(175, 255, 84, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_6);
		lblCites.setBounds(175, 283, 97, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblCites);
		lblPaperauthor.setBounds(175, 305, 97, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblPaperauthor);
		lblAuthorspaper.setBounds(175, 329, 107, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblAuthorspaper);
		citesPaperLabel.setBounds(259, 255, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(citesPaperLabel);
		citesAuthorLabel.setBounds(269, 283, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(citesAuthorLabel);
		paperAuthorLabel.setBounds(279, 305, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(paperAuthorLabel);
		authorPaperLabel.setBounds(289, 329, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(authorPaperLabel);
		lblHindex.setBounds(366, 255, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblHindex);
		lblNewLabel_7.setBounds(366, 283, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_7);
		lblNewLabel_8.setBounds(366, 305, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_8);
		lblNewLabel_9.setBounds(366, 329, 78, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_9);
		hIndexLabel.setBounds(430, 255, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(hIndexLabel);
		gIndexLabel.setBounds(430, 283, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(gIndexLabel);
		hinormLabel.setBounds(430, 305, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(hinormLabel);
		hIAnnualLabel.setBounds(440, 329, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(hIAnnualLabel);
		btnHelp.setBounds(902, 150, 91, 29);
		
		frmGoogleScholarTool.getContentPane().add(btnHelp);
	}
}
