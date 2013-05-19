package controllers;

import java.awt.event.ActionEvent;

import models.Event;
import models.EventSet;
import models.Organizer;

import views.gui.AddEvent;

public class AddEventController extends Controller {
	protected AddEvent addEvent;
	
	public AddEventController(AddEvent addEvent) {
		super();
		this.addEvent = addEvent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Event event = new Event(addEvent.getEventTitle(),addEvent.getEventComment(),
				addEvent.getStartCalendar(),addEvent.getEndCalendar(),
				Organizer.getInstance().getCurrentUser().getUserProfile());
		System.out.println(event.getTitle());
		System.out.println(event.getComment());
		System.out.println(event.getStartTime());
		System.out.println(event.getEndTime());
	}

}
