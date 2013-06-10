package views.gui;

import com.toedter.calendar.JCalendar;
import controllers.DatePickerController;
import models.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple window for choosing date.
 */
public class DatePicker extends JFrame {

	private static final long serialVersionUID = 6758451286754259312L;
	
	private JPanel contentPane;
	private JCalendar jCalendar;
	private JSplitPane topAndBot, okAndCancel;
	private User currentUser;
	private static DatePicker instance = null;

    public static DatePicker getInstance(DatePickerController datePickerController, User currentUser) {
    	
    	if(instance == null) {
    		instance = new DatePicker(datePickerController, currentUser);
    	} else {
    		instance.dispose();
    		instance = new DatePicker(datePickerController, currentUser);
    	}
    	
    	return instance;
    }
    
    
	/**
	 * Create the frame.
	 */
	private DatePicker(DatePickerController datePickerController, User currentUser) {
        this.currentUser = currentUser;
		setName("DatePicker");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setResizable(false);
		setContentPane(contentPane);
		
		jCalendar = new JCalendar(this.currentUser.getUserProfile().getState().getFirstDay());
		JButton btnOk = new JButton("OK");
		datePickerController.setUp(jCalendar,this);
		btnOk.addActionListener(datePickerController);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Canceling...");
				dispose();
			}
		});
		topAndBot = new JSplitPane();
		topAndBot.setEnabled(false);
		okAndCancel = new JSplitPane();
		okAndCancel.setEnabled(false);
		
		contentPane.add(topAndBot, BorderLayout.CENTER);
		
		topAndBot.setOrientation(JSplitPane.VERTICAL_SPLIT);
		topAndBot.setTopComponent(jCalendar);
		topAndBot.setBottomComponent(okAndCancel);
		
		okAndCancel.setLeftComponent(btnOk);
		okAndCancel.setRightComponent(btnCancel);
		okAndCancel.setDividerLocation(100);
		
		pack();
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setVisible(true);
	}
	
}
