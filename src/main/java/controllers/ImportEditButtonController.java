package controllers;

import java.awt.event.ActionEvent;

import views.gui.components.JLabelledBtn;

import models.User;

public class ImportEditButtonController extends Controller {

	private JLabelledBtn jLabelledBtn;
	private User currentUser;
	
	public ImportEditButtonController(JLabelledBtn jLabelledBtn, User currentUser) {
		this.jLabelledBtn = jLabelledBtn;
		this.currentUser = currentUser;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		views.gui.EventManager editWindow = views.gui.EventManager.getInstance(currentUser, jLabelledBtn.getEvent());
		jLabelledBtn.getButton().setText("Edited");
		editWindow.setVisible(true);
		
	}

}
