package controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.Event;
import models.EventSet;
import models.User;
import views.gui.ImportManager;
import views.gui.components.JEventBox;
import views.gui.components.JLabelledBtn;

public class ImportManagerController extends Controller {
	private List<JLabelledBtn> events;
	private User currentUser;
	
	public ImportManagerController(List<JLabelledBtn> events, User currentUser){
		this.events = events;
		this.currentUser = currentUser;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		EventSet eS = new EventSet(), leftover;
		for(JLabelledBtn btn : events)
			eS.add(btn.getEvent());
		leftover = currentUser.getUserProfile().getEvents().addSet(eS);
		
		if(leftover != null && leftover.size()>0){
			ImportManager.getInstance(leftover, currentUser).setVisible(true);
		}
	}
	
}
