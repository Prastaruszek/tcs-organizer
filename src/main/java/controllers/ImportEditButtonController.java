package controllers;

import java.awt.event.ActionEvent;

import models.Event;
import models.User;

public class ImportEditButtonController extends Controller {

	private Event event;
	private User currentUser;
	
	public ImportEditButtonController(Event event, User currentUser) {
		this.event = event;
		this.currentUser = currentUser;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		views.gui.EventManager editWindow = views.gui.EventManager.getInstance(currentUser, event);
		editWindow.setVisible(true);
		editWindow.setFocusable(true);
		
	}

}
