package views.gui;

import controllers.CalendarController;
import controllers.WeekPickerBackController;
import controllers.WeekPickerController;
import controllers.WeekPickerNextController;
import models.DisplayState;
import models.Event;
import models.Organizer;
import models.User;
import views.gui.components.JEventDisplay;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class Calendar implements Observer {

	public static final String SRC_MAIN_IMAGES_DATE_PICKER_ICON_GIF = "src/main/images/DatePickerIcon.gif";
	public static final String DEFAULT_USER_ICON = "src/main/images/DefaultUserIcon.png";
	private JFrame frame;
	private JEventDisplay eventDisplay;
	private JButton btnThereWillBe;
	private JLabel lblLogin;
	private JLabel lblUsername;
	private JLabel lblUserPic;
	private JLabel lblVelocity;
    private User currentUser;
	
    public JEventDisplay getEventDisplay() {
		return eventDisplay;
	}

	/**
	 * Create the application.
     * @param u
     */
	public Calendar(User u) {
        currentUser = u;
        initialize();
	}

    public void setVisibility(boolean value) {
        frame.setVisible(value);
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Organizer.getInstance();
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 550);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		
		JPanel panel = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		eventDisplay = new JEventDisplay();
		eventDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EventDetails((Event) arg0.getSource()).setVisible(true);
			}
		});
		eventDisplay.setToolTipText("");
		
		lblUserPic = new JLabel("<html><img src=\"file:" + new File(Calendar.DEFAULT_USER_ICON)+"\" /></html>");
		
		File icon = new File(currentUser.getUserProfile().getIconPath());
		if(icon.canRead())
			lblUserPic.setText("<html><img src=\"file:"+icon+"\" width=70 height=70 /></html>");
		
		lblLogin = new JLabel("You are logged in as :");
		lblUsername = new JLabel(currentUser.getUsername());
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(eventDisplay, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
							.addGap(11))
						.addComponent(eventDisplay, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
		);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventManager.getInstance(currentUser,null).setVisible(true);
			}
		});
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.getInstance(currentUser).setVisible(true);
			}
		});
		
		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Import clicked");
			}
		});
		
		JButton btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportManager.getInstance().setVisible(true);
			}
		});
		
		JButton btnChangeUser = new JButton("Log out");
		btnChangeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new LoginManager().setVisible(true);
				frame.dispose();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(lblLogin, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
				.addComponent(btnAddEvent, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
				.addComponent(btnSettings, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
				.addComponent(btnExport, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
				.addComponent(btnImport, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
				.addComponent(btnChangeUser, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblUserPic)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblLogin)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addComponent(lblUserPic))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(34)
							.addComponent(lblUsername)))
					.addGap(18)
					.addComponent(btnAddEvent)
					.addGap(18)
					.addComponent(btnSettings)
					.addGap(18)
					.addComponent(btnImport)
					.addGap(18)
					.addComponent(btnExport)
					.addGap(18)
					.addComponent(btnChangeUser)
					.addGap(145))
		);
		panel_1.setLayout(gl_panel_1);
		
		lblVelocity = new JLabel("Velocity: " + currentUser.getUserProfile().getVelocity().toString());
		lblVelocity.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_2.add(lblVelocity);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new WeekPickerBackController());
		panel.add(btnPrevious);

		btnThereWillBe = new JButton(currentUser.getUserProfile().getState().getRangeDisplay());
		btnThereWillBe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                DatePicker.getInstance(new WeekPickerController(), currentUser).setVisible(true);
			}
		});
        btnThereWillBe.setOpaque(true);
        btnThereWillBe.setPreferredSize(new Dimension(250, 25));
		panel.add(btnThereWillBe);


		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new WeekPickerNextController());
		panel.add(btnNext);
		CalendarController calendarController = new CalendarController(eventDisplay);
		Organizer.getInstance().addObserver(calendarController);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
	        public void windowClosing(WindowEvent e) {
	        	Organizer.getInstance().saveToFile();
	        }
	        
			@Override
	        public void windowClosed(WindowEvent e) {
				Organizer.getInstance().saveToFile();
			}
	    });
		
		frame.getContentPane().setLayout(groupLayout);
		frame.setVisible(true);
        update(null,currentUser.getUserProfile().getState());
	}

    @Override
    public void update(Observable o, Object arg) {
    	if(arg != null) {
    		DisplayState state = (DisplayState) arg;
    		btnThereWillBe.setText(state.getRangeDisplay());
    	}
        lblVelocity.setText("Velocity: " + currentUser.getUserProfile().getVelocity().toString());
        
		lblUserPic.setText("<html><img src=\"file:" + new File(Calendar.DEFAULT_USER_ICON)+"\" /></html>");
        
		File icon = new File(currentUser.getUserProfile().getIconPath());
		if(icon.canRead())
			lblUserPic.setText("<html><img src=\"file:"+icon+"\" width=70 height=70 /></html>");

    }
    
	protected JButton getLblThereWillBe() {
		return btnThereWillBe;
	}
}
