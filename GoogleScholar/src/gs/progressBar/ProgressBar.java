package gs.progressBar;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ProgressBar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @throws MalformedURLException 
	 * @author YiTai Chen
	 * 
	 * 
	 * The frame used to generate the progress bar
	 * 
	 */
	public ProgressBar() throws MalformedURLException {
		
		URL url = this.getClass().getResource("/progress/ProgressBar.gif");
		ImageIcon icon = new ImageIcon(url);
	    JLabel label = new JLabel(icon);
	    label.setBounds(5, 20, 66, 66);
	
		this.setUndecorated(true);
		this.getContentPane().add(label);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.toFront();
	}

}
