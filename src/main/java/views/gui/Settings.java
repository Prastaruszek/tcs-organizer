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
import javax.swing.filechooser.FileNameExtensionFilter;

import controllers.SettingsController;

import models.Organizer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Settings extends JFrame {

	private static final long serialVersionUID = -6483555840304512880L;
	
	private JPanel contentPane;
	private JTextField chosenFolder = new JTextField(Organizer.getInstance().getCurrentUser().getUserProfile().getPath());
	private JLabel folderLabel = new JLabel("Data folder:");
	private final JTextField editVelocity = new JTextField();
	private JLabel lblIcon = new JLabel("<html><img src=\"file:" + new File(Calendar.DEFAULT_USER_ICON)+"\" width=70 height=70 /></html>");
	private String newPath = Organizer.getInstance().getCurrentUser().getUserProfile().getIconPath();
	
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
		editVelocity.setColumns(10);
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 250);
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
		
		JLabel lblVelocity = new JLabel("Velocity");
		
		editVelocity.setText(Organizer.getInstance().getCurrentUser().getUserProfile().getVelocity().toString());
		
		File icon = new File(Organizer.getInstance().getCurrentUser().getUserProfile().getIconPath());
		
		if(icon.canRead()) 
			lblIcon = new JLabel("<html><img src=\"file:"+icon+"\" width=70 height=70 /></html>");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(folderLabel)
								.addComponent(lblVelocity))
							.addGap(53)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(editVelocity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
									.addComponent(lblIcon)
									.addGap(42))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(chosenFolder, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
									.addContainerGap())))))
		);
		lblIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.setFileFilter(new FileNameExtensionFilter("(*.png, *.jpg, *.gif)", "png", "jpg", "gif"));
				if(file.showDialog(Settings.this, "Choose") == JFileChooser.APPROVE_OPTION) {
					String tmpPath = file.getSelectedFile().getAbsolutePath();
					
					File newIcon = new File(tmpPath);
					if ( !newIcon.canRead() )
						return;
					
					lblIcon.setText("<html><img src=\"file:"+newIcon+"\" width=70 height=70 /></html>");
					
					newPath = new String(tmpPath);
				}
			}
		});
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblIcon, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(40))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVelocity)
								.addComponent(editVelocity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(chosenFolder, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(folderLabel))
					.addGap(31)
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
	
	public Integer getVelocity() {
		try {
			return Integer.parseInt(editVelocity.getText());
		} catch (Exception e) {
			return Organizer.getInstance().getCurrentUser().getUserProfile().getVelocity();
		}
	}
	
	public String getIconPath() {
		return newPath;
	}
}
