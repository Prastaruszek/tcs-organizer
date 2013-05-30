package views.gui;

import controllers.EventDetailsController;
import models.Event;
import models.Organizer;
import models.Resource;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class EventDetails extends JFrame {

	private static final long serialVersionUID = 4815288079833804727L;
	
	private JPanel contentPane;
	private Event event;
	private JLabel lblPriority;
	private JLabel lblEventTitle;
	private JLabel lblEventStart;
	private JLabel lblEventEnd;
	private JTextPane txtpnEventComment;
	private JList<Resource> resourceList;
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
		lblPriority.setBackground(event.getColor());
		lblPriority.setText(event.getRomanPriority());
        DefaultListModel<Resource> listModel = new DefaultListModel<>();
        for(Resource resource : event.getResourceList())
            listModel.addElement(resource);
        resourceList.setModel(listModel);
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
		
		lblPriority = new JLabel("IV");
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
		
		resourceList = new JList<>();
		resourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resourceList.addListSelectionListener(new ListSelectionListener() {
            int counter=0;
			public void valueChanged(ListSelectionEvent arg0) {
                if(counter++%2==0)
                    (resourceList.getSelectedValue()).open();
			}
		});
		
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
							.addContainerGap(204, Short.MAX_VALUE))
						.addComponent(lblEventTitle, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblComment)
					.addContainerGap(409, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnEventComment, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(resourceList, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
					.addGap(13))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(resourceList, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new EventManager(Organizer.getInstance().getCurrentUser(),event).setVisible(true);
            }
        });
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
	public JList<Resource> getResourceList() {
		return resourceList;
	}
}
