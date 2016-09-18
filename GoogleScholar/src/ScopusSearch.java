import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gs.progressBar.ProgressBar;
import gs.scopus.Result;
import gs.scopus.SearchArticle;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultEditorKit;

public class ScopusSearch extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Define interface variable
	 * 
	 * */
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save as CSV");
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	private final JMenu mnEdit = new JMenu("Edit");
	private final JMenuItem mntmCut = new JMenuItem(new DefaultEditorKit.CutAction());
	private final JMenuItem mntmCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
	private final JMenuItem mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
	private final JMenu mnView = new JMenu("Go");
	private final JMenuItem mntmJournalImpact = new JMenuItem("Google Scholar Search");
	private final JMenuItem mntmAuthorImapct = new JMenuItem("Scopus Search");
	private final JMenu mnHelp = new JMenu("Help");
	private final JMenuItem mntmAboutThisProgram = new JMenuItem("About this program");
	private final JPanel panel = new JPanel();
	private final JLabel lblAuthorNames = new JLabel("Title");
	private final JTextField titleText = new JTextField();
	private final JButton btnSearch = new JButton("Search");
	private final JButton button_1 = new JButton("Clear all");
	private final JLabel lblYearOfPublication = new JLabel("Year of Publication");
	private final JLabel lblExculdeTheseAuthors = new JLabel("Author");
	private final JTextField authorText = new JTextField();
	private final JTextField YearFrom = new JTextField();
	private final JLabel label_6 = new JLabel("Result", SwingConstants.CENTER);
	private final JScrollPane scrollPane = new JScrollPane();
	
	SearchArticle scopus = new SearchArticle();
	private final JLabel lblWhereMyWord = new JLabel("Where my word occur");
	private final JRadioButton allbtn = new JRadioButton("Anywhere in the article");
	private final JRadioButton titlebtn = new JRadioButton("In the title of the article");
	private final JLabel lblDoi = new JLabel("DOI");
	private final JTextField DOIText = new JTextField();
	private final JLabel lblIsbn = new JLabel("ISBN");
	private final JTextField ISBNText = new JTextField();
	private final JLabel lblSourceType = new JLabel("Source Type");
	private final JLabel lblSourceTitle = new JLabel("Source Title");
	private final JTextField SourceTitleText = new JTextField();
	private final JLabel lblNewLabel = new JLabel("Result:");
	private final JLabel resultLabel = new JLabel("0");
	
	private DefaultTableModel TableModel;
	private JTable table = new JTable();
	
	String[] message = {"Journal","Book","Book Series","Conference Proceeding","Report","Trade Publication"};
	
	private final JComboBox SourceCombo = new JComboBox(message);
	private final JLabel lblMaximumResultPer = new JLabel("Maximum result per search");
	private final JTextField MaxText = new JTextField();
	private ProgressBar bar;
	private final JMenuItem mntmShowDefaultCsv = new JMenuItem("Show Default CSV");
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Constructor of the Scopus search
	 * Create the frame.
	 * 
	 */
	public ScopusSearch() {
		MaxText.setBounds(223, 261, 78, 28);
		MaxText.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1074, 727);
		setLocationRelativeTo(null);
		setTitle("Scopus");
		setResizable(false);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
	
		
		try {
			bar = new ProgressBar();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		mntmNewMenuItem_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				 JFileChooser fc = new JFileChooser();
	                int option = fc.showSaveDialog(ScopusSearch.this);
	                if(option == JFileChooser.APPROVE_OPTION){
	                    String filename = fc.getSelectedFile().getName(); 
	                    String path = fc.getSelectedFile().getParentFile().getPath();

						int len = filename.length();
						String ext = "";
						String file = "";

						if(len > 4){
							ext = filename.substring(len-4, len);
						}

						if(ext.equals(".csv")){
							file = path + "/" + filename; 
						}else{
							file = path + "/" + filename + ".csv"; 
						}
						//System.out.println(filename);
						//System.out.println(path);
						writeToExcel(table, new File(file));
	                }
			}
		});
		
		
		mnFile.add(mntmNewMenuItem_1);
		
		mntmShowDefaultCsv.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String dir = "Working Directory = " +
			              System.getProperty("user.dir");
				
//				System.out.println("Working Directory = " +
//			              System.getProperty("user.dir"));
				
				JFrame frame = new JFrame("Scholar search tool");
			    
			    // show a joptionpane dialog using showMessageDialog
			    JOptionPane.showMessageDialog(frame,
			        "The default CSV file is saved in: \n'" + dir + "ScopusTemp.csv"+".");
				
			}
			
		});
		
		
		mnFile.add(mntmShowDefaultCsv);
		
		mnFile.add(mntmExit);
		
		menuBar.add(mnEdit);
		
		mntmCut.setText("Cut");
		mntmCut.setMnemonic(KeyEvent.VK_T);
		
		mnEdit.add(mntmCut);
		
		mntmCopy.setText("Copy");
		mntmCopy.setMnemonic(KeyEvent.VK_C);
		
		mnEdit.add(mntmCopy);
		
		mntmPaste.setText("Paste");
		mntmPaste.setMnemonic(KeyEvent.VK_P);
		
		mnEdit.add(mntmPaste);
		
		menuBar.add(mnView);
		
		mntmJournalImpact.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					
				dispose();
				
				GoogleScholarSearch jurFrame = new GoogleScholarSearch();
				
			}});
		
		mnView.add(mntmJournalImpact);
		
		mntmAuthorImapct.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					
			}});
		
		
		mnView.add(mntmAuthorImapct);
		
		menuBar.add(mnHelp);
		
		mnHelp.add(mntmAboutThisProgram);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		lblAuthorNames.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblAuthorNames.setBounds(43, 18, 37, 29);
		
		panel.add(lblAuthorNames);
		titleText.setColumns(10);
		titleText.setBounds(131, 22, 793, 20);
		
		panel.add(titleText);
		btnSearch.setBounds(950, 58, 91, 20);
		
		btnSearch.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(final java.awt.event.ActionEvent e) {
				
				try {
					
		
				// TODO Auto-generated method stub
						
				String searchTitle = titleText.getText();
				
				String author =authorText.getText();
				
				String DOI = DOIText.getText();
				
				String ISBN = ISBNText.getText();
				
				String sourceType = (String)SourceCombo.getSelectedItem();
				
				String sourceTitle = SourceTitleText.getText();
				
				String maxResult = MaxText.getText();
				
				String year = YearFrom.getText();
				
					
					scopus.SetApi("12073f3b09b9676bde9e2d7cff098aa0");
					//scopus.SetMAX(100);
					
					if(maxResult.length() >0)
					{
						scopus.SetMAX(Integer.parseInt(maxResult));
					}
					else{
						scopus.SetMAX(25);
					}
					//System.out.println(Integer.parseInt(maxResult));
					
					bar.setVisible(true);
					
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					
					
					if(!searchTitle.isEmpty() && allbtn.isSelected())
					{
						scopus.SetKeyWord(searchTitle);
						scopus.Search(0);
					}
					else if(!searchTitle.isEmpty()&&titlebtn.isSelected())
					{
						scopus.SetKeyWord(searchTitle);
						scopus.Search(1);
					}
					else if(!author.isEmpty())
					{
						scopus.SetKeyWord(author);
						scopus.Search(2);
					}
					else if(!ISBN.isEmpty())
					{
						scopus.SetKeyWord(ISBN);
						scopus.Search(3);
					}
					else if(!searchTitle.isEmpty() && !year.isEmpty())
					{
						scopus.SetKeyWord(searchTitle);
						scopus.SetYear(year);
						scopus.Search(4);
					}
					else if(!DOI.isEmpty())
					{
						scopus.SetKeyWord(DOI);
						scopus.Search(5);
					}
					else if(!searchTitle.isEmpty() && !author.isEmpty())
					{
						scopus.SetKeyWord(searchTitle);
						scopus.SetAuthor(author);
						scopus.Search(6);
					}
					else if(!searchTitle.isEmpty() && !sourceType.isEmpty())
					{
						scopus.SetKeyWord(searchTitle);
						scopus.SetType(checkType(sourceType));
						scopus.Search(7);
					}
					else if(!sourceTitle.isEmpty())
					{
						scopus.SetKeyWord(sourceTitle);
						scopus.Search(8);
					}
					
					scopus.WriteToExcel();
					
					for(Result x : scopus.GetResults())
					{
						TableModel.addRow(new Object[]{x.GetYear(),x.GetAuthor(),x.GetTitle(),x.GetUrl()});
					}
					
					resultLabel.setText(Integer.toString(TableModel.getRowCount()));
					
					bar.setVisible(false);
					
					setCursor(null);
				
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
			
		});
		
		panel.add(btnSearch);
		button_1.setBounds(950, 101, 91, 20);
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//clear all button
				
				int rowCount = TableModel.getRowCount();
				//Remove rows one by one from the end of the table
				for (int x = rowCount - 1; x >= 0; x--) 
				{
				    TableModel.removeRow(x);
				}
				TableModel.setRowCount(0);
				scopus.RemoveFile();
				
				resultLabel.setText("0");
				
				File dir = new File("./SearchResult");
				dir.mkdir();
				
			}
		});
		
		panel.add(button_1);
		lblYearOfPublication.setBounds(43, 238, 124, 16);
		
		panel.add(lblYearOfPublication);
		lblExculdeTheseAuthors.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblExculdeTheseAuthors.setBounds(43, 59, 84, 16);
		
		panel.add(lblExculdeTheseAuthors);
		authorText.setColumns(10);
		authorText.setBounds(131, 59, 793, 20);
		
		panel.add(authorText);
		YearFrom.setColumns(10);
		YearFrom.setBounds(223, 232, 78, 28);
		
		panel.add(YearFrom);
		label_6.setOpaque(true);
		label_6.setForeground(Color.BLACK);
		label_6.setBackground(Color.WHITE);
		label_6.setBounds(6, 301, 1062, 16);
		
		panel.add(label_6);
		scrollPane.setBounds(43, 362, 986, 275);
		
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		/**
		 * Create a table for store result
		 * */
		
		TableModel = new DefaultTableModel()
		{
			public Class<?> getColumnClass(int column)
			{
				switch(column)
				{
					case 0:
						return String.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					case 3:
						return String.class;		
					default:
							return String.class;
				}
			}
		};
		
		addColumn(TableModel,table);
		lblWhereMyWord.setBounds(43, 86, 151, 16);
		
		panel.add(lblWhereMyWord);
		allbtn.setBounds(195, 82, 192, 23);
		
		panel.add(allbtn);
		titlebtn.setBounds(399, 82, 199, 23);
		
		panel.add(titlebtn);
		lblDoi.setBounds(43, 114, 61, 16);
		
		ButtonGroup group = new ButtonGroup();
		group.add(allbtn);
		group.add(titlebtn);
		
		panel.add(lblDoi);
		DOIText.setColumns(10);
		DOIText.setBounds(131, 112, 793, 20);
		
		panel.add(DOIText);
		lblIsbn.setBounds(43, 142, 61, 16);
		
		panel.add(lblIsbn);
		ISBNText.setColumns(10);
		ISBNText.setBounds(131, 140, 793, 20);
		
		panel.add(ISBNText);
		lblSourceType.setBounds(43, 176, 84, 16);
		
		panel.add(lblSourceType);
		lblSourceTitle.setBounds(43, 208, 84, 16);
		
		panel.add(lblSourceTitle);
		SourceTitleText.setColumns(10);
		SourceTitleText.setBounds(131, 206, 793, 20);
		
		panel.add(SourceTitleText);
		lblNewLabel.setBounds(43, 329, 61, 16);
		
		panel.add(lblNewLabel);
		resultLabel.setBounds(102, 329, 61, 16);
		
		panel.add(resultLabel);
		SourceCombo.setBounds(131, 172, 178, 27);
				
		panel.add(SourceCombo);
		lblMaximumResultPer.setBounds(43, 266, 184, 16);
		
		panel.add(lblMaximumResultPer);
		
		panel.add(MaxText);
			
	}
	
	/**
	 * 
	 * Function to check the source type
	 * 
	 * Input String combo box result
	 * 
	 * Output String to determine the type
	 * 
	 * */
	public String checkType(String comboResult)
	{
		
		if(comboResult.equals("Journal"))
		{
			return "j";
		}
		else if(comboResult.equals("Book"))
		{
			return "b";
		}
		else if(comboResult.equals("Book Series"))
		{
			return "k";
		}
		else if(comboResult.equals("Conference Proceeding"))
		{
			return "p";
		}
		else if(comboResult.equals("Report"))
		{
			return "r";
		}
		else
		{
			return "d";
		}
		
	}
	
	/**
	 * The add column function
	 * Add the column for Jtable
	 * 
	 * */
	public void addColumn(DefaultTableModel TableModel, JTable table)
	{
		table.setModel(TableModel);
		table.setAutoCreateRowSorter(true);
		TableModel.addColumn("Year");
		TableModel.addColumn("Author");
		TableModel.addColumn("Title");
		TableModel.addColumn("URL");
	}
	/**
	 * 
	 * Write to excel function
	 * Input Java Jtable and File.
	 * 
	 * THis function will write the JTable information to excel file
	 * 
	 * */
	private void writeToExcel(JTable table, File file)
	{
		
		try{
			TableModel model = table.getModel();
			//file.setPosixFilePermisions(f1.toPath(), EnumSet.of(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, GROUP_READ, GROUP_EXECUTE));
	        FileWriter excel = new FileWriter(file);

	        for(int i = 0; i < model.getColumnCount(); i++){
	            excel.write(model.getColumnName(i) + ",");
	        }

	        excel.write("\n");
	        
	        System.out.println(model.getRowCount());
	        System.out.println(model.getColumnCount());
	        
	        for(int i=0; i< model.getRowCount(); i++) {
	            for(int j=0; j < model.getColumnCount(); j++) {
	            	//("\"" + x.GetYear() + "\"" + ",");
	                excel.write("\"" +model.getValueAt(i,j).toString()+ "\"" + ",");
	            }
	            excel.write("\n");
	        }

	        excel.close();

	    }catch(IOException e){ System.out.println(e); }
		
	}
	
}
