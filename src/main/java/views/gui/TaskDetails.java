package views.gui;

import controllers.TaskDetailsController;
import models.Event;
import models.Organizer;
import models.Resource;
import models.Task;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;

public class TaskDetails extends JFrame {

	private static final long serialVersionUID = 4815288079833804727L;

	private JPanel contentPane;
	private Task task;
    private Event event;
    private JList<Task> taskJList;
	private JLabel lblPriority;
	private JTextArea txtrEventTitle;
	private JLabel lblEventStart;
	private JSplitPane infoPane;
	private JTextArea txtrEventComment;
	private JList<Resource> resourceList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TaskDetails frame = new TaskDetails(null, null, null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}

	/**
	 * Create the frame.
     * @param _task
     */
	public TaskDetails(Task _task, final Event event, final JList<Task> taskJList) {
		setTitle("Details");
        this.event = event;
        this.task = _task;
        this.taskJList = taskJList;
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
		
		JLabel lblDuration = new JLabel("Duration:");
		
		lblEventStart = new JLabel("" + task.getDuration());
		lblEventStart.setFont(new Font("Dialog", Font.ITALIC, 12));
		
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
		
		resourceList = new JList<>();
		resourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resourceList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(resourceList.getSelectedValue() == null)
					return;
				resourceList.getSelectedValue().open();
				resourceList.clearSelection();
			}
		});
		JScrollPane resourceHolder = new JScrollPane(resourceList);
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
							.addComponent(lblDuration)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblEventStart))
						.addComponent(txtrEventTitle, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
					.addGap(15))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblComment)
					.addContainerGap(409, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(infoPane)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPriority, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(txtrEventTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDuration)
								.addComponent(lblEventStart))))
					.addGap(18)
					.addComponent(lblComment)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infoPane, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TaskManager.getInstance(Organizer.getInstance().getCurrentUser(), task, event, taskJList).setVisible(true);
                dispose();
            }
        });
		panel.add(btnEdit);
		
		JButton btnRemove = new JButton("Remove");
        TaskDetailsController eventDetailsController = new TaskDetailsController(this);
		btnRemove.addActionListener(eventDetailsController.getRemoveListener());
		panel.add(btnRemove);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnClose);
		contentPane.setLayout(gl_contentPane);

        txtrEventTitle.setText(task.getTitle());
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm");

        txtrEventComment.setText(task.getComment());
        txtrEventComment.setCaretPosition(0);
        lblPriority.setBackground(task.getColor());
        lblPriority.setText(task.getRomanPriority());
        DefaultListModel<Resource> listModel = new DefaultListModel<>();
        for(Resource resource : task.getResources())
            listModel.addElement(resource);
        resourceList.setModel(listModel);
        setVisible(true);
	}

    /**
     * Returns displayed task.
     * @return Task
     */
	public Task getTask() {
		return task;
	}

    /**
     * Returns event connected to this task.
     * @return Event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Returns JList of tasks from event details connected  to this task.
     * @return JList
     */
    public JList<Task> getTaskJList() {
        return taskJList;
    }

    /**
     * Returns JList of resources which displays resources of this task.
     * @return JList
     */
    public JList<Resource> getResourceList() {
		return resourceList;
	}
}
