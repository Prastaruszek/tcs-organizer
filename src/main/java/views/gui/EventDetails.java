package views.gui;

import controllers.EventDetailsController;
import models.Event;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class EventDetails extends JFrame {

	private static final long serialVersionUID = 4815288079833804727L;
	
	private JPanel contentPane;
	private Event event;
	private JLabel lblEventTitle;
	private JLabel lblEventStart;
	private JLabel lblEventEnd;
	private JTextPane txtpnEventComment;
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
		this.event = event;
		lblEventTitle.setText(event.getTitle());
		SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm");
		lblEventStart.setText(df.format(event.getStartTime().getTime()));
		lblEventEnd.setText(df.format(event.getEndTime().getTime()));
		txtpnEventComment.setText(event.getComment());
		//TODO set priority label
		setVisible(true);
	}
	/**
	 * Create the frame.
	 */
	public EventDetails() {
		setBounds(100, 100, 500, 343);
		setMinimumSize(new Dimension(getWidth(), getHeight()));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JLabel lblPriority = new JLabel("IV");
		lblPriority.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriority.setForeground(new Color(255, 255, 255));
		lblPriority.setFont(new Font("Dialog", Font.BOLD, 26));
		lblPriority.setBackground(new Color(51, 153, 204));
		lblPriority.setOpaque(true);
		lblPriority.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		lblEventTitle = new JLabel("Event title");
		lblEventTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JLabel lblFrom = new JLabel("From:");
		
		lblEventStart = new JLabel("Event start");
		lblEventStart.setFont(new Font("Dialog", Font.ITALIC, 12));
		
		JLabel lblTo = new JLabel("To:");
		
		lblEventEnd = new JLabel("Event end");
		lblEventEnd.setFont(new Font("Dialog", Font.ITALIC, 12));
		
		JLabel lblComment = new JLabel("Comment:");
		
		txtpnEventComment = new JTextPane();
		txtpnEventComment.setBackground(UIManager.getColor("TextPane.inactiveForeground"));
		txtpnEventComment.setText("This is the comment");
		txtpnEventComment.setEditable(false);
		txtpnEventComment.setMargin(new Insets(5, 5, 5, 5));
		txtpnEventComment.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
							BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(lblPriority, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblFrom)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEventStart)
							.addGap(18)
							.addComponent(lblTo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEventEnd)
							.addContainerGap(182, Short.MAX_VALUE))
						.addComponent(lblEventTitle, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblComment)
					.addContainerGap(412, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnEventComment, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblPriority, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEventTitle)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFrom)
								.addComponent(lblEventStart)
								.addComponent(lblTo)
								.addComponent(lblEventEnd))))
					.addGap(18)
					.addComponent(lblComment)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnEventComment, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnEdit = new JButton("Edit");
		panel.add(btnEdit);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new EventDetailsController(this));
		panel.add(btnRemove);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnClose);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
	public Event getEvent() {
		return event;
	}
}
