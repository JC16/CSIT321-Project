import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import gs.scopus.SearchArticle;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class authorSearch extends JFrame {

	private JPanel contentPane;

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
	private final JPanel panel = new JPanel();
	private final JLabel lblAuthorNames = new JLabel("Title");
	private final JTextField titleText = new JTextField();
	private final JButton btnSearch = new JButton("Search");
	private final JButton button_1 = new JButton("Clear all");
	private final JLabel label_3 = new JLabel("Year of Publication between");
	private final JLabel lblExculdeTheseAuthors = new JLabel("Published in:");
	private final JTextField textField_2 = new JTextField();
	private final JTextField textField_3 = new JTextField();
	private final JLabel label_5 = new JLabel("to");
	private final JTextField textField_4 = new JTextField();
	private final JLabel label_6 = new JLabel("Result", SwingConstants.CENTER);
	private final JLabel label_7 = new JLabel("Papers:");
	private final JLabel label_8 = new JLabel("Citation: ");
	private final JLabel label_9 = new JLabel("Years: ");
	private final JLabel label_10 = new JLabel("Cites/Year:");
	private final JLabel label_11 = new JLabel("");
	private final JLabel label_12 = new JLabel("");
	private final JLabel label_13 = new JLabel("");
	private final JLabel label_14 = new JLabel("");
	private final JLabel label_15 = new JLabel("Cites/Paper:");
	private final JLabel label_16 = new JLabel("Cites/Author: ");
	private final JLabel label_17 = new JLabel("Paper/Author:");
	private final JLabel label_18 = new JLabel("Authors/Paper:");
	private final JLabel label_19 = new JLabel("");
	private final JLabel label_20 = new JLabel("");
	private final JLabel label_21 = new JLabel("");
	private final JLabel label_22 = new JLabel("");
	private final JLabel label_23 = new JLabel("h-Index:");
	private final JLabel label_24 = new JLabel("g-Index");
	private final JLabel label_25 = new JLabel("hI,norm:");
	private final JLabel label_26 = new JLabel("hI, annual:");
	private final JLabel label_27 = new JLabel("");
	private final JLabel label_28 = new JLabel("");
	private final JLabel label_29 = new JLabel("");
	private final JLabel label_30 = new JLabel("");
	private final JScrollPane scrollPane = new JScrollPane();
	
	SearchArticle scopus = new SearchArticle();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public authorSearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1041, 673);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
		
		setTitle("Scopus");
		
		setJMenuBar(menuBar);
		
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
					
				dispose();
				
				JournalSearch jurFrame = new JournalSearch();
				
				
			}});
		
		mnView.add(mntmJournalImpact);
		
		mntmAuthorImapct.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					
				//do nothing
				
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
		lblAuthorNames.setBounds(16, 46, 126, 29);
		
		panel.add(lblAuthorNames);
		titleText.setColumns(10);
		titleText.setBounds(131, 51, 712, 20);
		
		panel.add(titleText);
		btnSearch.setBounds(875, 51, 77, 20);
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String searchTitle = titleText.getText();
				
				try {
					scopus.SetKeyWord(searchTitle);
					scopus.SetApi("12073f3b09b9676bde9e2d7cff098aa0");
					scopus.SetMAX(100);
					scopus.Search();
					scopus.WriteToExcel();
					scopus.RemoveFile();
					
					
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
		button_1.setBounds(875, 85, 91, 20);
		
		panel.add(button_1);
		label_3.setBounds(16, 137, 181, 16);
		
		panel.add(label_3);
		lblExculdeTheseAuthors.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblExculdeTheseAuthors.setBounds(16, 86, 84, 16);
		
		panel.add(lblExculdeTheseAuthors);
		textField_2.setColumns(10);
		textField_2.setBounds(131, 86, 712, 20);
		
		panel.add(textField_2);
		textField_3.setColumns(10);
		textField_3.setBounds(198, 131, 67, 28);
		
		panel.add(textField_3);
		label_5.setBounds(277, 137, 23, 16);
		
		panel.add(label_5);
		textField_4.setColumns(10);
		textField_4.setBounds(297, 131, 67, 28);
		
		panel.add(textField_4);
		label_6.setOpaque(true);
		label_6.setForeground(Color.BLACK);
		label_6.setBackground(Color.WHITE);
		label_6.setBounds(6, 248, 1009, 16);
		
		panel.add(label_6);
		label_7.setBounds(33, 276, 61, 16);
		
		panel.add(label_7);
		label_8.setBounds(33, 300, 61, 16);
		
		panel.add(label_8);
		label_9.setBounds(183, 300, 42, 16);
		
		panel.add(label_9);
		label_10.setBounds(34, 328, 70, 16);
		
		panel.add(label_10);
		label_11.setBounds(106, 276, 61, 16);
		
		panel.add(label_11);
		label_12.setBounds(116, 300, 61, 16);
		
		panel.add(label_12);
		label_13.setBounds(116, 328, 61, 16);
		
		panel.add(label_13);
		label_14.setBounds(269, 276, 61, 16);
		
		panel.add(label_14);
		label_15.setBounds(183, 276, 84, 16);
		
		panel.add(label_15);
		label_16.setBounds(183, 332, 97, 16);
		
		panel.add(label_16);
		label_17.setBounds(347, 276, 97, 16);
		
		panel.add(label_17);
		label_18.setBounds(347, 300, 107, 16);
		
		panel.add(label_18);
		label_19.setBounds(239, 300, 61, 16);
		
		panel.add(label_19);
		label_20.setBounds(292, 332, 61, 16);
		
		panel.add(label_20);
		label_21.setBounds(449, 328, 61, 16);
		
		panel.add(label_21);
		label_22.setBounds(463, 276, 61, 16);
		
		panel.add(label_22);
		label_23.setBounds(347, 332, 61, 16);
		
		panel.add(label_23);
		label_24.setBounds(566, 276, 61, 16);
		
		panel.add(label_24);
		label_25.setBounds(566, 300, 61, 16);
		
		panel.add(label_25);
		label_26.setBounds(566, 328, 78, 16);
		
		panel.add(label_26);
		label_27.setBounds(449, 300, 61, 16);
		
		panel.add(label_27);
		label_28.setBounds(639, 276, 61, 16);
		
		panel.add(label_28);
		label_29.setBounds(639, 300, 61, 16);
		
		panel.add(label_29);
		label_30.setBounds(639, 332, 61, 16);
		
		panel.add(label_30);
		scrollPane.setBounds(29, 371, 964, 216);
		
		panel.add(scrollPane);
		
		
		JTable table = new JTable();
		
		scrollPane.setViewportView(table);
		
		DefaultTableModel TableModel = new DefaultTableModel()
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
					case 4:
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
		TableModel.addColumn("Cites");
		TableModel.addColumn("Per year");
		TableModel.addColumn("Rank");
		TableModel.addColumn("Author");
		TableModel.addColumn("Title");
		TableModel.addColumn("Year");
		TableModel.addColumn("Publication");
		TableModel.addColumn("Publisher");
		TableModel.addColumn("Type");
	}
}
