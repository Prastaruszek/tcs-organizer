package views.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Event;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class EventDetails extends JFrame {

	private static final long serialVersionUID = 4815288079833804727L;
	
	private JPanel contentPane;
	private Event event;
	private JLabel lblEventEnd;
	private JLabel lblEventStart;
	private JLabel lblEventTitle;
	private JLabel lblEventComment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventDetails frame = new EventDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public EventDetails(Event event){
		this();
		this.event=event;
		lblEventTitle.setText(event.getTitle());
		lblEventComment.setText(event.getComment());
		lblEventStart.setText(event.getStartTime().getTime().toString());
		lblEventEnd.setText(event.getEndTime().getTime().toString());
		setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public EventDetails() {
		setBounds(100, 100, 500, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JLabel lblTitle = new JLabel("Title: ");
		
		lblEventTitle = new JLabel("Event Title");
		
		JLabel lblComment = new JLabel("Comment: ");
		
		lblEventComment = new JLabel("Event Comment");
		
		JLabel lblStart = new JLabel("Start: ");
		
		lblEventStart = new JLabel("Event Start");
		
		JLabel lblEnd = new JLabel("End: ");
		
		lblEventEnd = new JLabel("Event End");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblStart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEventStart)
					.addGap(131))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblEnd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEventEnd)
					.addGap(146))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEventTitle))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblComment)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEventComment)))
					.addContainerGap(123, Short.MAX_VALUE))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(lblEventTitle))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblComment)
						.addComponent(lblEventComment))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStart)
						.addComponent(lblEventStart))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnd)
						.addComponent(lblEventEnd))
					.addPreferredGap(ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnEdit = new JButton("Edit");
		panel.add(btnEdit);
		
		JButton btnRemove = new JButton("Remove");
		panel.add(btnRemove);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnClose);
		contentPane.setLayout(gl_contentPane);
	}
	protected JLabel getLblEventEnd() {
		return lblEventEnd;
	}
	protected JLabel getLblEventStart() {
		return lblEventStart;
	}
	protected JLabel getLblEventTitle() {
		return lblEventTitle;
	}
	protected JLabel getLblEventComment() {
		return lblEventComment;
	}
}
