package views.gui;

import controllers.EventDetailsController;
import models.Event;
import models.Organizer;
import models.Resource;
import models.Task;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class EventDetails extends JFrame {

	private static final long serialVersionUID = 4815288079833804727L;
	
	private JPanel contentPane;
	private Event event;
	private JLabel lblPriority;
	private JTextArea txtrEventTitle;
	private JLabel lblEventStart;
	private JLabel lblEventEnd;
	private JSplitPane infoPane;
	private JTextArea txtrEventComment;
	private JList<Task> tasksList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EventDetails frame = new EventDetails(null);
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
	public EventDetails(Event _event ) {
		setTitle("Details");
        this.event = _event;
		setBounds(100, 100, 500, 360);
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
		
		txtrEventTitle = new JTextArea("Event title");
		txtrEventTitle.setWrapStyleWord(true);
		txtrEventTitle.setLineWrap(true);
		txtrEventTitle.setBackground(UIManager.getColor("TextField.inactiveBackground"));
		txtrEventTitle.setEditable(false);
		txtrEventTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		txtrEventTitle.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				txtrEventTitle.setMinimumSize(new Dimension(0, 0));
			}
		});
		
		JLabel lblFrom = new JLabel("From:");
		
		lblEventStart = new JLabel("Event start");
		lblEventStart.setFont(new Font("Dialog", Font.ITALIC, 12));
		
		JLabel lblTo = new JLabel("To:");
		
		lblEventEnd = new JLabel("Event end");
		lblEventEnd.setFont(new Font("Dialog", Font.ITALIC, 12));
		
		JLabel lblComment = new JLabel("Comment:");
		
		infoPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		infoPane.setDividerLocation(100);
		infoPane.setResizeWeight(0.66);
		
		txtrEventComment = new JTextArea();
		txtrEventComment.setWrapStyleWord(true);
		txtrEventComment.setLineWrap(true);
		txtrEventComment.setBackground(UIManager.getColor("TextField.inactiveBackground"));
		txtrEventComment.setText("This is the comment");
		txtrEventComment.setEditable(false);
		txtrEventComment.setMargin(new Insets(5, 5, 5, 5));
		JScrollPane commentHolder = new JScrollPane(txtrEventComment);
		infoPane.add(commentHolder);
		
		tasksList = new JList<>();
		tasksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tasksList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                if (tasksList.getSelectedValue() == null)
                    return;
                new TaskDetails(tasksList.getSelectedValue(), event, tasksList).setVisible(true);
                tasksList.clearSelection();
            }
        });
		JScrollPane resourceHolder = new JScrollPane(tasksList);
		infoPane.add(resourceHolder);
		
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
                                        .addComponent(txtrEventTitle)))
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblComment)
                                .addContainerGap(407, Short.MAX_VALUE))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(infoPane, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                                .addContainerGap())
        );
		gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(lblPriority, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(txtrEventTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblFrom)
                                                        .addComponent(lblEventStart)
                                                        .addComponent(lblTo)
                                                        .addComponent(lblEventEnd))))
                                .addGap(18)
                                .addComponent(lblComment)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(infoPane, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
        );
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                EventManager.getInstance(Organizer.getInstance().getCurrentUser(), event).setVisible(true);
                dispose();
            }
        });
		panel.add(btnEdit);
		
		JButton btnRemove = new JButton("Remove");
        EventDetailsController eventDetailsController = new EventDetailsController(this);
		btnRemove.addActionListener(eventDetailsController.getRemoveListener());
		panel.add(btnRemove);

        if ( event != null && !event.getParent().isSingle()) { // we need null check because otherwise window builder will crush during parsing
            JButton btnRemoveGroup = new JButton("Remove group");
            btnRemoveGroup.addActionListener(eventDetailsController.getRemoveGroupListener());
            panel.add(btnRemoveGroup);
        }
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
		panel.add(btnClose);
		contentPane.setLayout(gl_contentPane);

        txtrEventTitle.setText(event.getTitle());
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm");
        lblEventStart.setText(df.format(event.getStartTime().getTime()));
        lblEventEnd.setText(df.format(event.getEndTime().getTime()));
        txtrEventComment.setText(event.getComment());
        txtrEventComment.setCaretPosition(0);
        lblPriority.setBackground(event.getColor());
        lblPriority.setText(event.getRomanPriority());
        DefaultListModel<Task> listModel = new DefaultListModel<>();
        for(Task task : event.getTasks())
            listModel.addElement(task);
        tasksList.setModel(listModel);
        setVisible(true);
	}
	
	public Event getEvent() {
		return event;
	}
	public JList<Task> getTasksList() {
		return tasksList;
	}
}
