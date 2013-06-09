package views.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import models.Event;
import models.EventGroup;
import models.EventPriority;
import models.Organizer;
import models.Resource;
import models.ResourceFile;
import models.Task;
import models.User;
import views.gui.components.LimitedDocument;
import controllers.DatePickerController;
import controllers.TaskManagerController;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class TaskManager extends JFrame {

	private static final long serialVersionUID = -1113743261857828270L;
	
	private static TaskManager instance = null;
    private final Event event;

    private JPanel contentPane;
	private JTextField txtfldTitle;
	private JTextArea txtrComment;
	private JComboBox<EventPriority> importanceBox;
    private User currentUser;
    private JList<Resource> list = null;
    private LinkedList<ResourceFile> removeList;
    private Task task;
    private JLabel lblDurationVal;
    private JList taskJList;


    /**
	 * For testing purposes.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskManager frame = new TaskManager(new User("asdf", "asdf".toCharArray()),null,null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static TaskManager getInstance(User currentUser, Task task, Event event, JList<Task> taskJList) {
		if(instance == null) {
			instance = new TaskManager(currentUser, task, event, taskJList);
		} else {
			instance.dispose();
			instance = new TaskManager(currentUser, task, event, taskJList);
		}
		return instance;
	}

	
	/**
	 * Create the frame.
     * @param currentUser
     *
     * @param task If null then new event will be added. Otherwise given event will be edited;
     *
     * @param event Event connected to this task.
     */
	private TaskManager(User currentUser, Task task, Event event, JList<Task> taskJList) {
        this.event = event;
        this.currentUser = currentUser;
        this.taskJList = taskJList;
        // there is similar if at the end of the constructor because some values (calendars) had to be set before
        // creating components and some (text areas, list) after.
        if(task==null) {
        	setTitle("Event creation zone");
        }
        else {
        	setTitle("Event edition zone");
            this.task = task;
        }
        
        removeList = new LinkedList<>();

        setResizable(false);
		setBounds(100, 100, 397, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Title: ");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtfldTitle = new JTextField(new LimitedDocument(EventGroup.getMaxTitleLength()), "", 10);
		
		JLabel lblComment = new JLabel("Comment:");
		lblComment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanel panel = new JPanel();
		
		JLabel lblImportance = new JLabel("Importance:");
		lblImportance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		importanceBox = new JComboBox<>(new DefaultComboBoxModel<EventPriority>(EventPriority.values()));
		if(isEditing())
			importanceBox.setSelectedItem(task.getPriorityObject());
		
		JPanel panel_4 = new JPanel();
		
		JPanel panel_5 = new JPanel();
		
		txtrComment = new JTextArea();
		txtrComment.setWrapStyleWord(true);
		txtrComment.setLineWrap(true);
		txtrComment.setFont(new Font("Monospaced", Font.PLAIN, 12));
		JScrollPane commentHolder = new JScrollPane(txtrComment);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtfldTitle, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblComment, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(291, Short.MAX_VALUE))
				.addComponent(commentHolder, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblImportance)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(importanceBox, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(210))
				.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
				.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(txtfldTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblComment)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(commentHolder, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImportance)
						.addComponent(importanceBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnAddEvent = new JButton(isEditing()?"Save":"Add Task");
		btnAddEvent.addActionListener(new TaskManagerController(this));
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
					.addComponent(btnAddEvent)
					.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
					.addComponent(btnClose))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClose)
						.addComponent(btnAddEvent))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JButton btnAddResource = new JButton("Add resource");
		btnAddResource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddResourceDialog.getInstance(getResourcesJList()).setVisible(true);
			}
		});
		
		JButton btnRemoveresource = new JButton("Remove resource");
        btnRemoveresource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	if(list.getSelectedValue() instanceof ResourceFile)
            		removeList.add(((ResourceFile)list.getSelectedValue()));
                ((DefaultListModel<Resource>)list.getModel()).removeElement(list.getSelectedValue());
            }
        });
		list = new JList<>(new DefaultListModel<Resource>());
		JScrollPane listHolder = new JScrollPane(list);
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(btnAddResource)
					.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
					.addComponent(btnRemoveresource))
				.addComponent(listHolder, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddResource)
						.addComponent(btnRemoveresource))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(listHolder, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
        final SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

		JLabel lblDuration = new JLabel("Duration:");

		JLabel lblNewLabel = new JLabel("minutes");
		
		lblDurationVal = new JLabel("0");

        final JSlider sliderDuration = new JSlider();
        sliderDuration.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                lblDurationVal.setText(""+sliderDuration.getValue());
            }
        });
        sliderDuration.setValue(60);
        sliderDuration.setMaximum(360);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDuration)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sliderDuration, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDurationVal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addGap(7))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDuration)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(sliderDuration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDurationVal)
								.addComponent(lblNewLabel))))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
        if(task!=null){
            txtfldTitle.setText(task.getTitle());
            txtrComment.setText(task.getComment());
            for(Resource resource : task.getResources())
                ((DefaultListModel<Resource>)list.getModel()).addElement(resource);
        }
		setVisible(true);
	}
	public String getEventTitle() {
		return txtfldTitle.getText();
	}
	public String getEventComment() {
		return txtrComment.getText();
	}
	public EventPriority getEventPriority() {
		return (EventPriority) importanceBox.getSelectedItem();
	}

	protected JList<Resource> getResourcesJList() {
		return list;
	}

    public int getDuration(){
        return Integer.valueOf(lblDurationVal.getText());
    }

	public List<Resource> getResources() {
		ListModel<Resource> model = getResourcesJList().getModel();
        List<Resource> ret = new LinkedList<>();
        for(int i=0;i<model.getSize();i++)
            ret.add(model.getElementAt(i));
        return ret;
	}

    /**
     * Tells you whether the task is edited by manager or new one is being created.
     * @return true if editing
     */
    public boolean isEditing(){
        return task!=null;
    }

    public Task getTask(){
        return task;
    }
    public Event getEvent() {
        return event;
    }

    public LinkedList<ResourceFile> getRemovedList() {
    	return removeList;
    }
	protected JLabel getLblDurationVal() {
		return lblDurationVal;
	}

    public JList<Task> getTaskJList() {
        return taskJList;
    }
}
