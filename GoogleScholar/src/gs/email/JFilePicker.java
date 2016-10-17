package gs.email;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * Pick the file for attachment
 * 
 * @author YiTai Chen
 * 
 *   Reference code
 * 
 *   Title: Swing application for sending e-mail
 *   Author: www.codejava.net
 *   Date: 2016 
 *   Last Updated: 14 August 2015
 *   Availability: www.codejava.net
 * 
 * 
 * */
public class JFilePicker extends JPanel {
	private String textFieldLabel;
	private String buttonLabel;
	
	private JLabel label;
	private JTextField textField;
	private JButton button;
	
	private JFileChooser fileChooser;
	
	private int mode;
	public static final int MODE_OPEN = 1;
	public static final int MODE_SAVE = 2;
	
	public JFilePicker(String textFieldLabel, String buttonLabel) {
		this.textFieldLabel = textFieldLabel;
		this.buttonLabel = buttonLabel;
		
		fileChooser = new JFileChooser();
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// creates the GUI
		label = new JLabel(textFieldLabel);
		
		textField = new JTextField(30);
		button = new JButton(buttonLabel);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				buttonActionPerformed(evt);				
			}
		});
		
		add(label);
		add(textField);
		add(button);
		
	}
	
	private void buttonActionPerformed(ActionEvent evt) {
		if (mode == MODE_OPEN) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		} else if (mode == MODE_SAVE) {
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		}
	}

	public void addFileTypeFilter(String extension, String description) {
		FileTypeFilter filter = new FileTypeFilter(extension, description);
		fileChooser.addChoosableFileFilter(filter);
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public String getSelectedFilePath() {
		return textField.getText();
	}
	
	public JFileChooser getFileChooser() {
		return this.fileChooser;
	}
}