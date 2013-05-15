package views.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JButton;

import models.Organizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import com.toedter.calendar.JCalendar;
import controllers.DatePickerController;

public class DatePicker extends JFrame {

	private JPanel contentPane;
	private JCalendar jCalendar;
	private JSplitPane topAndBot, okAndCancel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatePicker frame = new DatePicker();
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
	public DatePicker() {
		setName("DatePicker");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new DatePickerController(jCalendar, this));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Canceling...");
				dispose();
			}
		});
		jCalendar = new JCalendar(Organizer.getInstance().getCurrentUser().getUserProfile().getState().getFirstDay());
		
		topAndBot = new JSplitPane();
		okAndCancel = new JSplitPane();
		
		contentPane.add(topAndBot, BorderLayout.CENTER);
		
		topAndBot.setOrientation(JSplitPane.VERTICAL_SPLIT);
		topAndBot.setTopComponent(jCalendar);
		topAndBot.setBottomComponent(okAndCancel);
		
		okAndCancel.setLeftComponent(btnOk);
		okAndCancel.setRightComponent(btnCancel);
		okAndCancel.setDividerLocation(100);
		
		pack();
	}
	
}
