package controllers;

import forms.EventForm;
import forms.ValidationException;
import models.*;
import views.gui.EventManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EventManagerController extends Controller {
	protected EventManager eventManager;
    private UserProfile profile;

    public EventManagerController(views.gui.EventManager eventManager) {
		super();
		this.eventManager = eventManager;
        this.profile = Organizer.getInstance().getCurrentUser().getUserProfile();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        Event event;
        EventGroup group;
        if(!eventManager.isEditing())
            group = new EventGroup(eventManager.getEventTitle());
        else
            group = eventManager.getEvent().getParent();
        EventForm form = new EventForm(eventManager.getEvent(), profile);
        form.setStartTime(eventManager.getStartCalendar());
        form.setEndTime(eventManager.getEndCalendar());
        form.setComment(eventManager.getEventComment());
        form.setParent(group);
        form.setResources(eventManager.getResources());
        try {
            event = form.save();
            for(Resource resource : event.getResourceList()){
                 if(resource instanceof ResourceFile)
                     ((ResourceFile)resource).copyToResourcesDirectory();
            }
            eventManager.dispose();
        } catch (ValidationException error) {
            String errorMesssages = form.getErrorsDisplay();
        	JDialog errors = new JOptionPane(errorMesssages,
    				JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION).createDialog(eventManager, "Error");
            errors.pack();
            errors.setVisible(true);
        }
        Organizer.getInstance().update();
        Organizer.getInstance().notifyObservers(profile.getState());
    }

}
