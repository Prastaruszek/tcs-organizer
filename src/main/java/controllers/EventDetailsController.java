package controllers;

import java.awt.event.ActionEvent;

import models.Event;
import models.Organizer;

public class EventDetailsController extends Controller {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void removeEvent(Event e){
		e.delete();
		Organizer.getInstance().update();
		Organizer.getInstance().notifyObservers(Organizer.getInstance().getCurrentUser().getUserProfile().getState());
	}
}
