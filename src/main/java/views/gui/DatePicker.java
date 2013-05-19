package views.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import models.Organizer;

import com.toedter.calendar.JCalendar;

import controllers.DatePickerController;

public class DatePicker extends JFrame {

	private static final long serialVersionUID = 6758451286754259312L;
	
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
					//DatePicker frame = new DatePicker();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DatePicker(DatePickerController datePickerController) {
		setName("DatePicker");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		jCalendar = new JCalendar(Organizer.getInstance().getCurrentUser().getUserProfile().getState().getFirstDay());
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
		okAndCancel = new JSplitPane();
		
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
