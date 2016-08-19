import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.InetSocketAddress;
import java.net.Proxy;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;


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
	private final JLabel lblNewLabel_2 = new JLabel("With at least one of the words:");
	private final JTextField atleastone = new JTextField();
	private final JTextField ylo = new JTextField();
	private final JLabel lblAnd = new JLabel("to");
	private final JTextField yhi = new JTextField();
	private final JLabel lblResult = new JLabel("Result",SwingConstants.CENTER);
	private final JLabel lblNewLabel_3 = new JLabel("Number of result found:");
	private final JLabel PaperLabel = new JLabel("");
	private final JButton btnHelp = new JButton("Help");
	private static Pattern yearPattern = Pattern.compile(" ([12][0-9][0-9][0-9])( |$)");
	private static Pattern citeidPattern = Pattern.compile("/scholar\\?cites=([\\d]*)\\&");
	private static Pattern doiPattern = Pattern.compile("id=doi:([^&]*)");
	private ArrayList<String> titleArray = new ArrayList<String>();
	private  ArrayList<String> authorArray = new ArrayList<String>();
	private  ArrayList<String> cbArray = new ArrayList<String>();
	private  ArrayList<String> doiArray = new ArrayList<String>();
	private  ArrayList<String> gs_cited_by = new ArrayList<String>();
	private  ArrayList<String> gs_abs = new ArrayList<String>();
	private final JLabel lblNewLabel_4 = new JLabel("Without the words:");
	private final JTextField excl = new JTextField();
	private final JLabel lblNewLabel_5 = new JLabel("Where my words occur:");
	private final JRadioButton awbtn = new JRadioButton("Anywhere in the article");
	private final JRadioButton ttlbtn = new JRadioButton("In the title of the article");
	private final JLabel lblReturnArticlesAuthored = new JLabel("Return articles authored by:");
	private final JLabel lblNewLabel_6 = new JLabel("Return articles published in:");
	private final JTextField AuthorField = new JTextField();
	private final JTextField publishedField = new JTextField();
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

	
	/**
	 * Write to EXCELL
	 * 
	 * Need to import Apache POI library
	 * 
	 * 
	 * */
	
	private void writeToExcel(JTable table, File file)
	{
		
		try{
			TableModel model = table.getModel();
			//file.setPosixFilePermisions(f1.toPath(), EnumSet.of(OWNER_READ, OWNER_WRITE, OWNER_EXECUTE, GROUP_READ, GROUP_EXECUTE));
	        FileWriter excel = new FileWriter(file);

	        for(int i = 0; i < model.getColumnCount(); i++){
	            excel.write(model.getColumnName(i) + "\t");
	        }

	        excel.write("\n");
	        
	        System.out.println(model.getRowCount());
	        System.out.println(model.getColumnCount());
	        
	        for(int i=0; i< model.getRowCount(); i++) {
	            for(int j=0; j < model.getColumnCount(); j++) {
	            	//System.out.println(model.getValueAt(i,j).toString());
	                excel.write(model.getValueAt(i,j).toString()+"\t");
	            }
	            excel.write("\n");
	        }

	        excel.close();

	    }catch(IOException e){ System.out.println(e); }
		
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
		yhi.setBounds(336, 280, 56, 28);
		yhi.setColumns(10);
		ylo.setBounds(248, 280, 56, 28);
		ylo.setColumns(10);
		
		atleastone.setBounds(248, 99, 638, 28);
		atleastone.setColumns(10);
		frmGoogleScholarTool = new JFrame();
		frmGoogleScholarTool.setFont(new Font("Dialog", Font.BOLD, 12));
		frmGoogleScholarTool.setTitle("Google Scholar Search");
		frmGoogleScholarTool.setBounds(100, 100, 1074, 727);
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
		
		mntmNewMenuItem_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				 JFileChooser fc = new JFileChooser();
	                int option = fc.showSaveDialog(JournalSearch.this);
	                if(option == JFileChooser.APPROVE_OPTION){
	                    String filename = fc.getSelectedFile().getName(); 
	                    String path = fc.getSelectedFile().getParentFile().getPath();

						int len = filename.length();
						String ext = "";
						String file = "";

						if(len > 4){
							ext = filename.substring(len-4, len);
						}

						if(ext.equals(".xls")){
							file = path + "/" + filename; 
						}else{
							file = path + "/" + filename + ".xls"; 
						}
						//System.out.println(filename);
						//System.out.println(path);
						writeToExcel(table, new File(file));
	                }
			}
		});
		
		mnFile.add(mntmNewMenuItem_1);
		
		mntmExit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.exit(0);
			}
		});
		
		
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
		
		JLabel lblNewLabel = new JLabel("With all of the words:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblNewLabel.setBounds(43, 24, 139, 29);
		frmGoogleScholarTool.getContentPane().add(lblNewLabel);
		
		journalt = new JTextField();
		journalt.setBounds(248, 28, 638, 20);
		frmGoogleScholarTool.getContentPane().add(journalt);
		journalt.setColumns(10);
		
		JLabel lblIssn = new JLabel("With exact phrase:");
		lblIssn.setBounds(43, 65, 123, 23);
		frmGoogleScholarTool.getContentPane().add(lblIssn);
		
		withphr = new JTextField();
		withphr.setBounds(248, 65, 638, 22);
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
				
				
				//checkboxes
				String btndot = "";
				
				if(awbtn.isSelected() == true)
				{
					btndot = "any";
					
				}
				else if(ttlbtn.isSelected() == true)
				{
					btndot = "title";					
				}
				
				
				//published in
				String category = publishedField.getText();
				category = category.replace(' ', '+');
				
				
				
				//authored by
				String authorlist = AuthorField.getText();
				authorlist = authorlist.replace(' ', '+');
				
				
				//at least one of the words
				String atleast = atleastone.getText();
				atleast = atleast.replace(' ', '+');
				
				//get year low and year high
				String yrlow = ylo.getText();
				String yrhi = yhi.getText();
				
				//exclude words
				String exclude = excl.getText();
				exclude = exclude.replace(' ', '+');
				
				
				
				
//				private String[] titleArray =new String[0];
//				private String[] authorArray = new String[0];
//				private String[] cbArray = new String[0];
//				private String[] doiArray = new String[0];
//				private String[] gs_cited_by = new String[0];
//				private String[] gs_abs = new String[0];
				int count = 0;
				int tot_j_num=0;
				try
				{
					
//					
//					private ArrayList<String> titleArray = new ArrayList<String>();
//					private  ArrayList<String> authorArray = new ArrayList<String>();
//					private  ArrayList<String> cbArray = new ArrayList<String>();
//					private  ArrayList<String> doiArray = new ArrayList<String>();
//					private  ArrayList<String> gs_cited_by = new ArrayList<String>();
//					private  ArrayList<String> gs_abs = new ArrayList<String>();

						do{
						
						System.out.println(count);
						
						//int proxyPort = 8080;
						//String proxyAdress = "150.101.163.235";
						//Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(proxyAdress, proxyPort));
						
						
						//https://scholar.google.com/scholar?q=lol&hl=en&as_sdt=0,5
							String url = "https://scholar.google.com/scholar?as_q="+title+"&as_epq="+phrase+"&as_oq="+ atleast + "&as_eq=" + exclude + "&as_occt=" + btndot +"&as_sauthors=" + authorlist + "&as_publication=" + category + "&as_ylo="+yrlow+"&as_yhi="+yrhi+"&start="+count+"&btnG=&hl=en&as_sdt=0%2C5&google_abuse=GOOGLE_ABUSE_EXEMPTION%3DID%3Df44924af39543aff:TM%3D1470099997:C%3Dc:IP%3D203.10.91.86-:S%3DAPGng0sxcpuaPbdkZfpOfmudLAW2Y5x5RA%3B+path%3D/%3B+domain%3Dgoogle.com%3B+expires%3DTue,+02-Aug-2016+04:06:37+GMT";
						//String url = "https://scholar.google.com/scholar?as_q="+ title +"&as_epq=" + phrase + "&as_oq=&as_eq=" + exclude + "&as_occt=title&as_sauthors=&as_publication=&as_ylo=" + yrlow + "&as_yhi=" + yrhi +"&start="+count+ "&btnG=&hl=en&as_sdt=0%2C5";

							System.out.println(url);
							Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")  
						           //.proxy(proxy)
								   .referrer("http://www.google.com")   
						           .timeout(30000) 
						           .followRedirects(true)
						           .get();
						System.out.println(doc.title());
						if(count<10){
				            Element gs_ab_md = doc.getElementById("gs_ab_md");
				            String Jnum = gs_ab_md.text();
				            String[] a = Jnum.split("[A-Za-z]+");
				            String [] tot_temp = a[1].split(",");
				            String tot_j = "";
				            for (int j = 0; j < tot_temp.length;j++){
				                tot_j+=tot_temp[j];
				            }
				            tot_j=tot_j.trim();

				            tot_j_num = Integer.parseInt(tot_j);
				            System.out.println(tot_j_num);
						}
						
						Elements gstitle = doc.getElementsByClass("gs_ri");
						Elements elements = doc.select("div.gs_r");
						
						for (Element link:gstitle)
			            {
			            	String gs_rt = link.getElementsByClass("gs_rt").tagName("a").text();
			            	gs_rt = gs_rt.replace("[PDF]", "");
			            	gs_rt = gs_rt.replace("[HTML]", "");
			            	gs_rt = gs_rt.replace("[BOOK][B]", "");
			            	gs_rt = gs_rt.replace("[CITATION][C]", "");
			            	gs_rt = gs_rt.trim();
			            	//lnk = lnk.replaceAll("[B]", "");
			            	String gs_rs = link.getElementsByClass("gs_rs").text();
			                
			            	String gs_a = link.getElementsByClass("gs_a").tagName("div").text();
			            	
			            	if (gs_rt.length()!=0 && gs_a.length() !=0 ) {
			            		titleArray.add(gs_rt);
			            		authorArray.add(gs_a);
			            		gs_abs.add(gs_rs);		            		
			            	}		            	


			            }
						
						
						//int count2 = 0;
						
						
						
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
								cbArray.add(cited);
								System.out.println(cited);
																
							}
							
						}
									
						}
						
						
						Elements gs_fl_2 = doc.getElementsByClass("gs_fl");
			            
			            Elements gs_select = gs_fl_2.select("a[href]");

			            for (Element all_url: gs_select){
			                int find_url = String.valueOf(all_url).indexOf("/scholar?cites=");
			                if (find_url != -1){
			                    String temp_url = all_url.toString();
			                    //temp_html[counter] = temp_url;
			                    String [] split_half = temp_url.split("<a href=\"");
			                    for (int o = 0; o < split_half.length;o++){
			                        if (split_half[o].trim() !="" && split_half[o]!=null){
			                            //System.out.println(test[o]);
			                            temp_url = split_half[o];
			                            String[] test2 = temp_url.split("\"",2);
			                            for (int k = 0; k < test2.length;k++){

			                                find_url = test2[k].indexOf("/scholar?cites=");
			                                if (find_url!=-1) {
			                                    gs_cited_by.add("https://scholar.google.com.au/"+test2[k]);
			                                }
			                            }

			                        }
			                    }
			                }
			            }
			            
						
						count+=10;
						System.out.println(count);
						
				}while(count<100);
						
						for(int x = 0; x < titleArray.size(); x++)
						{
							Matcher matcher = yearPattern.matcher(authorArray.get(x));
							
							String year = "";
							if (matcher.find())
							{
								year = matcher.group();
							}
							else
							{
								year = "n/a";
							}

							//TableModel.addRow(new Object[]{false, "col3", authorArray[x], titleArray[x], year});
							//TableModel.addRow(new Object[]{false, "col3", authorArray[x], titleArray[x], year,"hello","hello","hello"});

							TableModel.addRow(new Object[]{false, cbArray.get(x), authorArray.get(x), titleArray.get(x),year, gs_abs.get(x),gs_cited_by.get(x)});

						}
						
				
				}catch (Exception e)
				{
			        e.printStackTrace();
			    }
		
			}
		});
		btnSearch.setBounds(933, 67, 97, 20);
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
		btnClearAll.setBounds(933, 157, 97, 20);
		frmGoogleScholarTool.getContentPane().add(btnClearAll);
		lblNewLabel_1.setBounds(43, 286, 181, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setBounds(43, 105, 193, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_2);
		
		frmGoogleScholarTool.getContentPane().add(atleastone);
		
		frmGoogleScholarTool.getContentPane().add(ylo);
		lblAnd.setBounds(313, 286, 18, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblAnd);
		
		frmGoogleScholarTool.getContentPane().add(yhi);
		lblResult.setForeground(Color.BLACK);
		lblResult.setBackground(new Color(255, 255, 255));
		lblResult.setOpaque(true);
		lblResult.setBounds(6, 320, 1062, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblResult);
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(43, 348, 146, 20);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_3);
		PaperLabel.setBounds(162, 335, 61, 16);
		
		frmGoogleScholarTool.getContentPane().add(PaperLabel);
		btnHelp.setBounds(933, 242, 97, 20);
		
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
		scroll.setBounds(43, 387, 987, 262);
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
				
//				case 0:
//					return String.class;
//				case 1:
//					return String.class;
//				case 2:
//					return String.class;
//				case 3:
//					return String.class;
//				case 4:
//					return String.class;
//					
//				default:
//						return String.class;
				
				}
			}
		};
		
		addColumn(TableModel,table);
		lblNewLabel_4.setBounds(43, 141, 123, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_4);
		excl.setColumns(10);
		excl.setBounds(248, 135, 638, 28);
		
		frmGoogleScholarTool.getContentPane().add(excl);
		lblNewLabel_5.setBounds(43, 173, 153, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_5);
		awbtn.setBounds(248, 175, 181, 23);
		awbtn.setSelected(true);
		frmGoogleScholarTool.getContentPane().add(awbtn);
		ttlbtn.setBounds(437, 175, 181, 23);
		
		frmGoogleScholarTool.getContentPane().add(ttlbtn);
		lblReturnArticlesAuthored.setBounds(43, 215, 181, 16);
		
		ButtonGroup group = new ButtonGroup();
		group.add(awbtn);
		group.add(ttlbtn);
		
		frmGoogleScholarTool.getContentPane().add(lblReturnArticlesAuthored);
		lblNewLabel_6.setBounds(43, 243, 181, 16);
		
		frmGoogleScholarTool.getContentPane().add(lblNewLabel_6);
		AuthorField.setColumns(10);
		AuthorField.setBounds(248, 209, 638, 28);
		
		frmGoogleScholarTool.getContentPane().add(AuthorField);
		publishedField.setColumns(10);
		publishedField.setBounds(248, 240, 638, 28);
		
		frmGoogleScholarTool.getContentPane().add(publishedField);
		
	}
	
	 
	public void addColumn(DefaultTableModel TableModel, JTable table)
	{
		table.setModel(TableModel);
		TableModel.addColumn("Select");
		TableModel.addColumn("Cites");
		TableModel.addColumn("Author");
		TableModel.addColumn("Title");
		TableModel.addColumn("Year");
		TableModel.addColumn("Abstract");
		TableModel.addColumn("URL");	
	}
}
