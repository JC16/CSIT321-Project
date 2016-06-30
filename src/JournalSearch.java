import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class JournalSearch extends JFrame {

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
	private final JLabel lblJournalTitle = new JLabel("Journal Title:");
	private final JTextField textField = new JTextField();
	private final JButton button = new JButton("Search");
	private final JButton button_1 = new JButton("Clear all");
	private final Choice choice = new Choice();
	private final JLabel label_2 = new JLabel("Data source:");
	private final JLabel label_3 = new JLabel("Year of Publication between");
	private final JLabel lblExculdeTheseAuthors = new JLabel("ISSN:");
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
	private final JButton button_2 = new JButton("Help");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblExculdeWords = new JLabel("Exculde words");
	
	/**
	 * Launch the application.
	 */

	

	/**
	 * Create the frame.
	 */
	public JournalSearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 999, 646);

		setTitle("Journal Search");
		
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
					
				panel.setVisible(false);
				
				JournalSearch jurFrame = new JournalSearch();
				jurFrame.setVisible(true);
				
			}});
		
		mnView.add(mntmJournalImpact);
		
		mntmAuthorImapct.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					
				panel.setVisible(false);
				
				authorSearch frame = new authorSearch();
				frame.setVisible(true);
				
			}});
		
		
		mnView.add(mntmAuthorImapct);
		
		mntmGeneralCitationSearch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					
				panel.setVisible(false);
				
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
		lblJournalTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblJournalTitle.setBounds(16, 46, 126, 29);
		
		panel.add(lblJournalTitle);
		textField.setColumns(10);
		textField.setBounds(178, 51, 722, 20);
		
		panel.add(textField);
		button.setBounds(896, 50, 97, 25);
		
		panel.add(button);
		button_1.setBounds(896, 100, 97, 25);
		
		panel.add(button_1);
		choice.setBounds(106, 199, 157, 22);
		
		panel.add(choice);
		label_2.setBounds(16, 199, 84, 16);
		
		panel.add(label_2);
		label_3.setBounds(16, 159, 181, 16);
		
		panel.add(label_3);
		lblExculdeTheseAuthors.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblExculdeTheseAuthors.setBounds(16, 92, 42, 16);
		
		panel.add(lblExculdeTheseAuthors);
		textField_2.setColumns(10);
		textField_2.setBounds(178, 86, 722, 28);
		
		panel.add(textField_2);
		textField_3.setColumns(10);
		textField_3.setBounds(207, 153, 56, 28);
		
		panel.add(textField_3);
		label_5.setBounds(275, 159, 61, 16);
		
		panel.add(label_5);
		textField_4.setColumns(10);
		textField_4.setBounds(305, 153, 56, 28);
		
		panel.add(textField_4);
		label_6.setOpaque(true);
		label_6.setForeground(Color.BLACK);
		label_6.setBackground(Color.WHITE);
		label_6.setBounds(6, 248, 987, 16);
		
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
		button_2.setBounds(896, 173, 97, 29);
		
		panel.add(button_2);
		scrollPane.setBounds(16, 367, 964, 216);
		
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
		textField_1.setColumns(10);
		textField_1.setBounds(178, 119, 722, 28);
		
		panel.add(textField_1);
		lblExculdeWords.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblExculdeWords.setBounds(16, 125, 107, 16);
		
		panel.add(lblExculdeWords);
		
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
