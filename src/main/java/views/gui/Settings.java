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

/**
 * Creates a settings window.
 */
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
    @SuppressWarnings("rawtypes")
	private JComboBox endBox;
    @SuppressWarnings("rawtypes")
	private JComboBox startBox;

    /**
	 * Launch the settings view
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings(new User("asdf", "asdf".toCharArray()));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


    public static Settings getInstance(User user) {
        if ( instance == null ) {
            instance = new Settings(user);
        } else {
            instance.dispose();
            instance = new Settings(user);
        }
        return instance;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Settings(User currentUser) {
		setResizable(false);
        this.currentUser = currentUser;
        editVelocity = new JTextField();
        editVelocity.setToolTipText("Unused. Wait for next version.");
        editVelocity.setColumns(10);
		setTitle("Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 466, 292);
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

        chosenFolder = new JTextField(currentUser.getUserProfile().getPath());
        
        chosenFolder.addMouseListener(new MouseAdapter() {

        	/**
        	 *  Opens directory chooser dialog on mouse click
        	 */ 
        	
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();
				file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(file.showDialog(Settings.this, "Choose!") == JFileChooser.APPROVE_OPTION) {
					chosenFolder.setText(file.getSelectedFile().getAbsolutePath());
				}
			}
			
		});
        
		chosenFolder.setEditable(false);
		
		JPanel panel = new JPanel();
		
		JLabel lblVelocity = new JLabel("Velocity:");
		lblVelocity.setToolTipText("Unused. Wait for next version.");
		
        JLabel folderLabel = new JLabel("Data folder:");
        folderLabel.setToolTipText("Data folder stores all event resources.");
		
		editVelocity.setText(currentUser.getUserProfile().getVelocity().toString());
		
		File icon = new File(currentUser.getUserProfile().getIconPath());

        lblIcon = new JLabel("<html><img src=\"file:" + new File(Calendar.DEFAULT_USER_ICON)+"\" width=70 height=70 /></html>");
        if(icon.canRead())
			lblIcon = new JLabel("<html><img src=\"file:"+icon+"\" width=70 height=70 /></html>");
        lblIcon.setToolTipText("Click to change user picture.");
		JLabel colorLabel = new JLabel("Color:");
		
		modColors = new Color[5];
		
		/**
		 * Gets actual priority colors from user profile
		 */
		
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
			
			/**
			 * Opens JColorChooser to set color for current priority in colorBox
			 */
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Color newColor = JColorChooser.showDialog(null, "Choose a color", colorButton.getBackground());
				
				if(newColor != null) {
					
					modColors[colorBox.getSelectedIndex()] = newColor;
					
					colorBox.setSelectedIndex(colorBox.getSelectedIndex());
				}	
			}
		
		});
		
		colorBox = new JComboBox<>(new DefaultComboBoxModel<String>(prior));
		colorBox.addActionListener(new ActionListener() {
			
			/**
			 * Updates the color of colorButton on change of priority
			 */

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				colorButton.setBackground(modColors[colorBox.getSelectedIndex()]);
				
			}
			
		});
		colorBox.setSelectedIndex(0);
		
		JLabel lblPriority = new JLabel("priority");
		
		JLabel lblDisplayHoursBetween = new JLabel("Display hours between:");
		
		startBox = new JComboBox();
		startBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"}));
		startBox.setSelectedIndex(currentUser.getUserProfile().getDisplayFirstHour());
		JLabel lblNewLabel = new JLabel("and");
		endBox = new JComboBox();
		endBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		endBox.setSelectedIndex(currentUser.getUserProfile().getDisplayLastHour()-1);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(folderLabel)
								.addComponent(lblVelocity)
								.addComponent(colorLabel))
							.addGap(53)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(editVelocity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(colorButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(colorBox, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPriority, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblIcon)
									.addGap(42))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(chosenFolder, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
									.addContainerGap())))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
							.addContainerGap())))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(13)
					.addComponent(lblDisplayHoursBetween)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(startBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(endBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(181, Short.MAX_VALUE))
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
								.addComponent(colorBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPriority))
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
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDisplayHoursBetween)
						.addComponent(startBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(endBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
		);
        newPath = currentUser.getUserProfile().getIconPath();
        lblIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                file.setFileFilter(new FileNameExtensionFilter("(*.png, *.jpg, *.gif)", "png", "jpg", "gif"));
				if(file.showDialog(Settings.this, "Change your icon!") == JFileChooser.APPROVE_OPTION) {
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
		
		/**
		 * Proceed to save new data
		 */
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new SettingsController(this));
		panel.add(btnOK);
		
		/**
		 *  Avoids saving anything
		 */
		
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
	
	/** Returns path to user save location
	 * @return String with current user specified save folder
	 */
	public String getPath() {
		return chosenFolder.getText();
	}
	
	/** Returns Velocity
	 * @return Integer with actual Velocity
	 */
	public Integer getVelocity() {
		try {
			return Integer.parseInt(editVelocity.getText());
		} catch (Exception e) {
			return currentUser.getUserProfile().getVelocity();
		}
	}
	
	/** Returns user icon path 
	 * @return String with current user icon path
	 */
	public String getIconPath() {
		return newPath;
	}
	
	/** Returns Colors for events priority
	 * @return Color[] with actual color parameters
	 */
	public Color[] getColorChoices() {
		
		return modColors;
		
	}

    /**
     * Returns first hour for events display
     * @return int with current first hour
     */
    public int getDisplayFirstHour() {
        return Integer.valueOf((String)getStartBox().getSelectedItem());
    }

    /**
     * Returns last hour for events display
     * @return int with current last hour
     */
    public int getDisplayLastHour() {
        return Integer.valueOf((String)getEndBox().getSelectedItem());
    }
    @SuppressWarnings("rawtypes")
	protected JComboBox getEndBox() {
		return endBox;
	}
    @SuppressWarnings("rawtypes")
	protected JComboBox getStartBox() {
		return startBox;
	}
}
