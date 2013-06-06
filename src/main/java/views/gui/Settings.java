package views.gui;

import controllers.SettingsController;
import models.EventPriority;
import models.User;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Settings extends JFrame {

	private static final long serialVersionUID = -6483555840304512880L;
	
	private JPanel contentPane;
	private JTextField chosenFolder;
	private JTextField editVelocity;
	private JLabel lblIcon;
	private JComboBox<String> colorBox;
	private String newPath;
    private User currentUser;
    private static Settings instance;
    private JButton colorButton;
    private Color[] modColors;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings(new User("asdf", "asdf"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    private Settings(User currentUser) {
        init(currentUser);
    }

    public static Settings getInstance(User user) {
        if ( instance == null ) {
            instance = new Settings(user);
        } else {
            instance.init(user);
        }
        return instance;
    }

	private void init(User currentUser) {
		
        this.currentUser = currentUser;
        editVelocity = new JTextField();
        editVelocity.setColumns(10);
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 250);
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

        chosenFolder = new JTextField(currentUser.getUserProfile().getPath());
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
		
		JLabel lblVelocity = new JLabel("Velocity:");
		
        JLabel folderLabel = new JLabel("Data folder:");
		
		editVelocity.setText(currentUser.getUserProfile().getVelocity().toString());
		
		File icon = new File(currentUser.getUserProfile().getIconPath());

        lblIcon = new JLabel("<html><img src=\"file:" + new File(Calendar.DEFAULT_USER_ICON)+"\" width=70 height=70 /></html>");
        if(icon.canRead())
			lblIcon = new JLabel("<html><img src=\"file:"+icon+"\" width=70 height=70 /></html>");
		
		JLabel colorLabel = new JLabel("Color:");
		
		modColors = new Color[5];
		
		String[] prior = new String[5];
		for(int i=0; i<5; ++i) {
			prior[i] = EventPriority.values()[i].getRomanPriority();
			modColors[i] = EventPriority.values()[i].getColor(Settings.this.currentUser.getUserProfile());
		}
		
		colorButton = new JButton(" ");
		colorButton.setContentAreaFilled(true);
		colorButton.setOpaque(true);
		colorButton.setBackground(Color.PINK);
		colorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Color newColor = JColorChooser.showDialog(null, "Choose a color", colorButton.getBackground());
				
				if(newColor != null) {
					
					modColors[colorBox.getSelectedIndex()] = newColor;
					
					colorBox.setSelectedIndex(colorBox.getSelectedIndex());
				}	
			}
		
		});
		
		colorBox = new JComboBox<>(prior);
		colorBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				colorButton.setBackground(modColors[colorBox.getSelectedIndex()]);
				
			}
			
		});
		colorBox.setSelectedIndex(0);
		
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
								.addComponent(lblVelocity)
								.addComponent(colorLabel))
							.addGap(53)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(editVelocity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
											.addComponent(colorButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(colorBox, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
									.addComponent(lblIcon)
									.addGap(42))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(chosenFolder, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
									.addContainerGap())))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIcon, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
								.addComponent(colorLabel)
								.addComponent(colorButton)
								.addComponent(colorBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
        newPath = currentUser.getUserProfile().getIconPath();
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
			return currentUser.getUserProfile().getVelocity();
		}
	}
	
	public String getIconPath() {
		return newPath;
	}
	
	public Color[] getColorChoices() {
		
		return modColors;
		
	}
}
