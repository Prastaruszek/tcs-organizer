package controllers;

import java.awt.event.ActionEvent;

import forms.TaskForm;
import forms.ValidationException;
import models.*;
import views.gui.TaskManager;

import javax.swing.*;

/**
 * Controller used to create new Task from user specific data
 */
public class TaskManagerController extends Controller {
	protected TaskManager taskManager;
    private UserProfile profile;

    /** Creates new Task from data specified by user
     * @param taskManager
     */
    public TaskManagerController(views.gui.TaskManager taskManager) {
		super();
		this.taskManager = taskManager;
        this.profile = Organizer.getInstance().getCurrentUser().getUserProfile();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

        for(ResourceFile f : taskManager.getRemovedList())
            f.removeFromResourcesDirectory();
        TaskForm form = new TaskForm(taskManager.getTask(), profile);
        form.setDuration(taskManager.getDuration());
        form.setTitle(taskManager.getEventTitle());
        form.setComment(taskManager.getEventComment());
        form.setResources(taskManager.getResources());
        form.setPriority(taskManager.getEventPriority());

        try {
            Task task = form.save();
            if ( !taskManager.isEditing()) {
                ((DefaultListModel<Task>)taskManager.getTaskJList().getModel()).addElement(task);
            }
            taskManager.dispose();
        } catch (ValidationException error) {
            String errorMesssages = form.getErrorsDisplay();
            JDialog errors = new JOptionPane(errorMesssages,
                    JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION).createDialog(taskManager, "Error");
            errors.pack();
            errors.setVisible(true);
        }
        Organizer.getInstance().update();
        Organizer.getInstance().notifyObservers(profile.getState());
    }

}