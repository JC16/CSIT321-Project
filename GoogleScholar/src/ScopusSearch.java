import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gs.scopus.Result;
import gs.scopus.SearchArticle;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.table.TableModel;

public class ScopusSearch extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Define interface variable
	 * 
	 * */
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
	private final JMenuItem mntmJournalImpact = new JMenuItem("Google Scholar Search");
	private final JMenuItem mntmAuthorImapct = new JMenuItem("Scopus Search");
	private final JMenuItem mntmGeneralCitationSearch = new JMenuItem("General Search");
	private final JMenu mnTool = new JMenu("Tool");
	private final JMenuItem mntmNewMenuItem_2 = new JMenuItem("Clear data");
	private final JMenuItem mntmClearCache = new JMenuItem("Clear Cache");
	private final JMenuItem mntmP = new JMenuItem("Preference");
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
	
	/**
	 * Launch the application.
	 */

	/**
	 * Constructor of the Scopus search
	 * Create the frame.
	 * 
	 */
	public ScopusSearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1074, 727);
		
		setTitle("Scopus");
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		
		mnFile.add(mntmNewMenuItem);
		
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
					
				dispose();
				
				GoogleScholarSearch jurFrame = new GoogleScholarSearch();
				
				
			}});
		
		mnView.add(mntmJournalImpact);
		
		mntmAuthorImapct.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					
			}});
		
		
		mnView.add(mntmAuthorImapct);
		
		mntmGeneralCitationSearch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					
				dispose();
				
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
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		lblAuthorNames.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblAuthorNames.setBounds(43, 46, 37, 29);
		
		panel.add(lblAuthorNames);
		titleText.setColumns(10);
		titleText.setBounds(131, 51, 793, 20);
		
		panel.add(titleText);
		btnSearch.setBounds(938, 85, 91, 20);
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String searchTitle = titleText.getText();
				
				String author =authorText.getText();
				
				String DOI = DOIText.getText();
				
				String ISBN = ISBNText.getText();
				
				String sourceType = (String)SourceCombo.getSelectedItem();
				
				String sourceTitle = SourceTitleText.getText();
				
				String year = YearFrom.getText();
				
				try {
					
					scopus.SetApi("12073f3b09b9676bde9e2d7cff098aa0");
					scopus.SetMAX(100);
					
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
					
					for(Result x : scopus.GetResults())
					{
						TableModel.addRow(new Object[]{x.GetYear(),x.GetAuthor(),x.GetTitle(),x.GetUrl()});
					}
				
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
		button_1.setBounds(938, 194, 91, 20);
		
		
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
			}
		});
		
		panel.add(button_1);
		lblYearOfPublication.setBounds(43, 269, 124, 16);
		
		panel.add(lblYearOfPublication);
		lblExculdeTheseAuthors.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblExculdeTheseAuthors.setBounds(43, 86, 84, 16);
		
		panel.add(lblExculdeTheseAuthors);
		authorText.setColumns(10);
		authorText.setBounds(131, 86, 793, 20);
		
		panel.add(authorText);
		YearFrom.setColumns(10);
		YearFrom.setBounds(166, 263, 67, 28);
		
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
		lblWhereMyWord.setBounds(43, 114, 151, 16);
		
		panel.add(lblWhereMyWord);
		allbtn.setBounds(206, 110, 192, 23);
		
		panel.add(allbtn);
		titlebtn.setBounds(421, 110, 199, 23);
		
		panel.add(titlebtn);
		lblDoi.setBounds(43, 142, 61, 16);
		
		ButtonGroup group = new ButtonGroup();
		group.add(allbtn);
		group.add(titlebtn);
		
		panel.add(lblDoi);
		DOIText.setColumns(10);
		DOIText.setBounds(131, 136, 793, 20);
		
		panel.add(DOIText);
		lblIsbn.setBounds(43, 167, 61, 16);
		
		panel.add(lblIsbn);
		ISBNText.setColumns(10);
		ISBNText.setBounds(131, 161, 793, 20);
		
		panel.add(ISBNText);
		lblSourceType.setBounds(43, 195, 84, 16);
		
		panel.add(lblSourceType);
		lblSourceTitle.setBounds(43, 230, 84, 16);
		
		panel.add(lblSourceTitle);
		SourceTitleText.setColumns(10);
		SourceTitleText.setBounds(131, 225, 793, 20);
		
		panel.add(SourceTitleText);
		lblNewLabel.setBounds(43, 329, 61, 16);
		
		panel.add(lblNewLabel);
		resultLabel.setBounds(102, 329, 61, 16);
		
		panel.add(resultLabel);
		SourceCombo.setBounds(131, 191, 178, 27);
				
		panel.add(SourceCombo);
			
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
