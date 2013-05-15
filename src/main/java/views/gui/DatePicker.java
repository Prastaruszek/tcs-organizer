package views.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Action;

import com.toedter.calendar.JCalendar;

public class DatePicker extends JFrame {

	private JPanel contentPane;
	private JCalendar jCalendar;
	private Date data;
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane topAndBot = new JSplitPane();
		topAndBot.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(topAndBot, BorderLayout.CENTER);
		
		jCalendar = new JCalendar();
		JSplitPane okAndCancel = new JSplitPane();
		topAndBot.setLeftComponent(jCalendar);
		topAndBot.setRightComponent(okAndCancel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Showing...");
				data = jCalendar.getDate(); //jak to przekazac?
				dispose();
			}
		});
		okAndCancel.setLeftComponent(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Canceling...");
				dispose();
			}
		});
		okAndCancel.setRightComponent(btnCancel);
		pack();
	}
	
}
