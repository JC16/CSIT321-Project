import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.phantomjs.PhantomJSDriver;

import org.openqa.selenium.Dimension;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Choice;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.TextArea;
import javax.swing.JTable;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JournalSearch extends JFrame {

	private JFrame frmGoogleScholarTool;
	private JTextField journalt;
	private JTextField withphr;
	
	private JTable table = new JTable();
	private DefaultTableModel TableModel;

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
	private final JMenuItem mntmJournalImpact = new JMenuItem("Journal Search");
	private final JMenuItem mntmAuthorImapct = new JMenuItem("Author Search");
	private final JMenuItem mntmGeneralCitationSearch = new JMenuItem("General Search");
	private final JMenu mnTool = new JMenu("Tool");
	private final JMenuItem mntmNewMenuItem_2 = new JMenuItem("Clear data");
	private final JMenuItem mntmClearCache = new JMenuItem("Clear Cache");
	private final JMenuItem mntmP = new JMenuItem("Preference");
	private final JMenu mnHelp = new JMenu("Help");
	private final JMenuItem mntmAboutThisProgram = new JMenuItem("About this program");
	private final JLabel lblNewLabel_1 = new JLabel("Year of Publication between");
	private final JLabel lblNewLabel_2 = new JLabel("Exculde words");
	private final JTextField excl = new JTextField();
	private final JTextField ylo = new JTextField();
	private final JLabel lblAnd = new JLabel("to");
	private final JTextField yhi = new JTextField();
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
	private static Pattern yearPattern = Pattern.compile(" ([12][0-9][0-9][0-9])( |$)");
	private static Pattern citeidPattern = Pattern.compile("/scholar\\?cites=([\\d]*)\\&");
	private static Pattern doiPattern = Pattern.compile("id=doi:([^&]*)");
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JournalSearch window = new JournalSearch();
					window.frmGoogleScholarTool.setVisible(true);
					
			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	/**
	 *  GET DATA FROM JTABLE
	 * */
	private String getCellVal(int x, int y)
	{
		return TableModel.getValueAt(x, y).toString();
	}
	
	
	
	/**
	 * Write to EXCELL
	 * 
	 * Need to import Apache POI library
	 * 
	 * 
	 * */
	
	private void writeToExcel()
	{
		
		//Create workbook
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet ws = wb.createSheet();
		
		//Load DATA To TREEMAP
		
		TreeMap<String, Object[]> data = new TreeMap();
		
		//Get column name from Table
		data.put("0", new Object[]{TableModel.getColumnName(0),TableModel.getColumnName(1),TableModel.getColumnName(2)});
		
		//Get first row information
		data.put("1", new Object[]{getCellVal(0,0),getCellVal(0,1),getCellVal(0,2)});
		
		
	}
	
	
	public JournalSearch() 
	{
		initialize();
		frmGoogleScholarTool.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		yhi.setBounds(323, 144, 56, 28);
		yhi.setColumns(10);
		ylo.setBounds(226, 144, 56, 28);
		ylo.setColumns(10);
		excl.setBounds(136, 105, 748, 28);
		excl.setColumns(10);
		frmGoogleScholarTool = new JFrame();
		frmGoogleScholarTool.setFont(new Font("Dialog", Font.BOLD, 12));
		frmGoogleScholarTool.setTitle("Journal Search");
		frmGoogleScholarTool.setBounds(100, 100, 1030, 669);
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
		
		mntmJournalImpact.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					
				//do nothing
				
			}});
		
		mnView.add(mntmJournalImpact);
		
		mntmAuthorImapct.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					
				frmGoogleScholarTool.dispose();
				
				authorSearch frame = new authorSearch();
				frame.setVisible(true);
				
			}});
		
		
		mnView.add(mntmAuthorImapct);
		
		mntmGeneralCitationSearch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					
				frmGoogleScholarTool.dispose();
				
				GeneralSearch GFrame = new GeneralSearch();
				GFrame.setVisible(true);
				
				
			}});
		
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
		
		journalt = new JTextField();
		journalt.setBounds(136, 29, 748, 20);
		frmGoogleScholarTool.getContentPane().add(journalt);
		journalt.setColumns(10);
		
		JLabel lblIssn = new JLabel("With exact phrase:");
		lblIssn.setBounds(14, 65, 112, 23);
		frmGoogleScholarTool.getContentPane().add(lblIssn);
		
		withphr = new JTextField();
		withphr.setBounds(136, 65, 748, 22);
		frmGoogleScholarTool.getContentPane().add(withphr);
		withphr.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//search button
				
				int rowCount = TableModel.getRowCount();
				//Remove rows one by one from the end of the table
				for (int x = rowCount - 1; x >= 0; x--) 
				{
				    TableModel.removeRow(x);
				}
				
				
				//get journal title
				String title = journalt.getText();
				title = title.replace(' ', '+');
				
				//get phrase
				String phrase = withphr.getText();
				phrase = phrase.replace(' ', '+');
				
				//get year low and year high
				String yrlow = ylo.getText();
				String yrhi = yhi.getText();
				
				
				//exclude words
				String exclude = excl.getText();
				exclude = exclude.replace(' ', '+');
				
				try
				{
					
						//make these dynamic later
						String[] titleArray = {"", "","","","","","","","",""};
						String[] authorArray = {"", "","","","","","","","",""};
						String[] cbArray = {"", "","","","","","","","",""};
						String[] doiArray = {"", "","","","","","","","",""};
						
						
						
						int count = 0;
						//https://scholar.google.com/scholar?q=lol&hl=en&as_sdt=0,5
						String url = "https://scholar.google.com/scholar?as_q="+ title +"&as_epq=" + phrase + "&as_oq=&as_eq=" + exclude + "&as_occt=title&as_sauthors=&as_publication=&as_ylo=" + yrlow + "&as_yhi=" + yrhi + "&btnG=&hl=en&as_sdt=0%2C5";
						Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")  
						           .referrer("http://www.google.com")   
						           .timeout(12000) 
						           .followRedirects(true)
						           .get();
						//System.out.println(doc.title());
						
						
						Elements gstitle = doc.getElementsByClass("gs_ri");
						Elements elements = doc.select("div.gs_r");
						
						for (Element link:gstitle)
			            {
			            	String lnk = link.getElementsByClass("gs_rt").tagName("a").text();
			            	lnk = lnk.replace("[PDF]", "");
			            	lnk = lnk.replace("[HTML]", "");
			            	lnk = lnk.replace("[BOOK][B]", "");
			            	lnk = lnk.replace("[CITATION][C]", "");
			            	lnk = lnk.trim();
			            	//lnk = lnk.replaceAll("[B]", "");
			            	String lnk2 = link.getElementsByClass("gs_a").tagName("div").text();
			            	if (lnk.length()!=0 && lnk2.length() !=0 ) {
			            		titleArray[count] = lnk;
			            		authorArray[count] = lnk2;
			            		count++;			            		
			            	}		            	
			            }
						
						count = 0;
						int count2 = 0;
						
						
							
						
						for (Element element : elements)
						{
						gstitle = element.select(".gs_fl a[href]");
						for(Element link : gstitle)
						{
							String text = link.text();
							//System.out.println(text);
							if(text.startsWith("Cited by "))
							{
								
								String cited = text;
								cited = cited.substring(9);
								cbArray[count] = cited;
								count++;
																
							}
							
							
							
						}
												
						}
						
						
						
												
						for(int x = 0; x < titleArray.length; x++)
						{
							Matcher matcher = yearPattern.matcher(authorArray[x]);
							
							String year = "";
							if (matcher.find())
							{
								year = matcher.group();
							}
							else
							{
								year = "n/a";
							}
							TableModel.addRow(new Object[]{false, cbArray[x], authorArray[x], titleArray[x], year, doiArray[x]});
						}
						
						
						
						
					
				}				
				catch (Exception e)
				{
			        e.printStackTrace();
			    }
				
					
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnSearch.setBounds(896, 29, 97, 20);
		frmGoogleScholarTool.getContentPane().add(btnSearch);
		
		JButton btnClearAll = new JButton("Clear all");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//clear all button
				
				int rowCount = TableModel.getRowCount();
				//Remove rows one by one from the end of the table
				for (int x = rowCount - 1; x >= 0; x--) 
				{
				    TableModel.removeRow(x);
				}
			}
		});
		btnClearAll.setBounds(894, 66, 97, 20);
		frmGoogleScholarTool.getContentPane().add(btnClearAll);
		
		Choice choice = new Choice();
		choice.add("Google Scholar");
		choice.add("Scopus");
		choice.add("ISI Web of Science");
		choice.setBounds(149, 182, 157, 22);
		frmGoogleScholarTool.getContentPane().add(choice);
		
		JLabel lblData = new JLabel("Data source:");
		lblData.setBounds(43, 188, 84, 16);
		frmGoogleScholarTool.getContentPane().add(lblData);
		lblNewLabel_1.setBounds(43, 150, 181, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setBounds(43, 110, 97, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_2);
		
		frmGoogleScholarTool.getContentPane().add(excl);
		
		frmGoogleScholarTool.getContentPane().add(ylo);
		lblAnd.setBounds(291, 150, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblAnd);
		
		frmGoogleScholarTool.getContentPane().add(yhi);
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
		btnHelp.setBounds(896, 95, 91, 20);
		
		btnHelp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String help = "Journal Title: Enter the name of Journal you want to look up \n"
							+ "Jounral ISSN: Enter the ISSN of Journal you want to look up\n"
							+ "Exclude words: Enter any addtional words that most not appear in the return papers\n"
							+ "Year of publication: Enter the range of the year which the paper had been publish\n"
							+ "Data Source: The Data source for searching inforamtion";
				
				JOptionPane.showMessageDialog(frmGoogleScholarTool, help);
			}
			
		});
		
		
		frmGoogleScholarTool.getContentPane().add(btnHelp);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(43, 368, 929, 216);
		frmGoogleScholarTool.getContentPane().add(scroll);
		
		
		
		scroll.setViewportView(table);
		
		TableModel = new DefaultTableModel()
		{
			public Class<?> getColumnClass(int column)
			{
				switch(column)
				{
					case 0:
						return Boolean.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					case 3:
						return String.class;
					case 4:
						return String.class;
					case 5:
						return String.class;
						
					default:
							return String.class;
				}
			}
		};
		
		addColumn(TableModel,table);
		
	}
	
	 
	public void addColumn(DefaultTableModel TableModel, JTable table)
	{
		table.setModel(TableModel);
		TableModel.addColumn("Select");
		TableModel.addColumn("Cites");
		TableModel.addColumn("Author");
		TableModel.addColumn("Title");
		TableModel.addColumn("Year");
		TableModel.addColumn("D.O.I.");
		TableModel.addColumn("Publisher");
		TableModel.addColumn("Type");
	}
	
	
}
