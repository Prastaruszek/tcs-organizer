package controllers;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import forms.EventForm;
import forms.ValidationException;
import models.Event;
import models.EventGroup;
import models.Organizer;

import models.UserProfile;
import views.gui.AddEvent;

public class AddEventController extends Controller {
	protected AddEvent addEvent;
    private UserProfile profile;

    public AddEventController(AddEvent addEvent) {
		super();
		this.addEvent = addEvent;
        this.profile = Organizer.getInstance().getCurrentUser().getUserProfile();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        Event event;
        EventGroup group = new EventGroup(addEvent.getEventTitle());
        EventForm form = new EventForm(profile);
        form.setStartTime(addEvent.getStartCalendar());
        form.setEndTime(addEvent.getEndCalendar());
        form.setComment(addEvent.getEventComment());
        form.setParent(group);
        try {
            event = form.save();
        } catch (ValidationException error) {
        	new JOptionPane("Invalid event.",
    				JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION).createDialog(addEvent,"Error").setVisible(true);
        }
        Organizer.getInstance().update();
        Organizer.getInstance().notifyObservers(profile.getState());
    }

}
