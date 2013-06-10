package controllers;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.Event;
import models.EventSet;
import views.gui.ImportManager;
import views.gui.components.JEventBox;
import views.gui.components.JLabelledBtn;

public class ImportManagerController extends Controller {
	private List<JLabelledBtn> events;
	private models.EventManager manager;
	
	public ImportManagerController(List<JLabelledBtn> events, models.EventManager manager){
		this.events = events;
		this.manager = manager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		EventSet eS = new EventSet(), leftover;
		for(JLabelledBtn btn : events)
			eS.add(btn.getEvent());
		leftover = manager.addSet(eS);
		
		if(leftover != null && leftover.size()>0){
			ImportManager.getInstance(leftover, manager).setVisible(true);
		}
	}
	
}
