package controllers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import forms.EventForm;
import forms.TaskForm;
import forms.ValidationException;
import models.*;
import views.gui.TaskManager;

import javax.swing.*;

public class TaskManagerController extends Controller {
	protected TaskManager taskManager;
    private UserProfile profile;

    public TaskManagerController(views.gui.TaskManager taskManager) {
		super();
		this.taskManager = taskManager;
        this.profile = Organizer.getInstance().getCurrentUser().getUserProfile();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        Event event;

        for(ResourceFile f : taskManager.getRemovedList())
            f.removeFromResourcesDirectory();
        TaskForm form = new TaskForm(taskManager.getTask(), taskManager.getEvent());
        form.setDuration(taskManager.getDuration());
        form.setTitle(taskManager.getEventTitle());
        form.setComment(taskManager.getEventComment());
        form.setResources(taskManager.getResources());
        form.setPriority(taskManager.getEventPriority());

        try {
            form.save();
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
