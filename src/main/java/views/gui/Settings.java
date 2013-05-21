package views.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import controllers.SettingsController;

import models.Organizer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Settings extends JFrame {

	private static final long serialVersionUID = -6483555840304512880L;
	
	private JPanel contentPane;
	private JTextField chosenFolder = new JTextField(Organizer.getInstance().getCurrentUser().getUserProfile().getPath());
	private JLabel folderLabel = new JLabel("Data folder:");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Settings() {
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 220);
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
				
		chosenFolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();
				file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(file.showDialog(Settings.this, "Choose") == JFileChooser.APPROVE_OPTION) {
					chosenFolder.setText(file.getSelectedFile().getAbsolutePath());
				}
			}
		});
		chosenFolder.setEditable(false);
		
		JPanel panel = new JPanel();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(folderLabel)
							.addGap(62)
							.addComponent(chosenFolder, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(folderLabel)
						.addComponent(chosenFolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
		);
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new SettingsController(this));
		panel.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCancel);
		
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
	public String getPath() {
		return chosenFolder.getText();
	}

}
