package controllers;

import java.awt.event.ActionEvent;

import models.Organizer;
import models.UserProfile;
import views.gui.TaskManager;

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
       
    }

}
