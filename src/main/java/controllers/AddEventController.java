package controllers;

import java.awt.event.ActionEvent;

import forms.EventForm;
import forms.ValidationException;
import models.Event;
import models.EventGroup;
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
        Event event;
        EventGroup group = new EventGroup(addEvent.getEventTitle());
        EventForm form = new EventForm();
        form.setStartTime(addEvent.getStartCalendar());
        form.setEndTime(addEvent.getEndCalendar());
        form.setComment(addEvent.getEventComment());
        form.setParent(group);
        try {
            event = form.save();
        } catch (ValidationException error) {
            error.printStackTrace();
        }
        Organizer.getInstance().update();
        Organizer.getInstance().notifyObservers(Organizer.getInstance().getCurrentUser().getUserProfile().getState());
    }

}
