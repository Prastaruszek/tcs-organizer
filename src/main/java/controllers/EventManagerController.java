package controllers;

import forms.EventForm;
import forms.ValidationException;
import models.*;
import views.gui.EventManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

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
        List<EventForm> forms = new LinkedList <>();
        if(!eventManager.isRepeating()){
            EventForm form = new EventForm(eventManager.getEvent(), profile);
            form.setStartTime(eventManager.getStartCalendar());
            form.setEndTime(eventManager.getEndCalendar());
            form.setComment(eventManager.getEventComment());
            form.setParent(group);
            form.setResources(eventManager.getResources());
            form.setPriority(eventManager.getEventPriority());
            forms.add(form);
        } else {
            boolean[] repeatDays = {eventManager.isSundayRepeat(),eventManager.isMondayRepeat(),
                                    eventManager.isTuesdayRepeat(),eventManager.isWednesdayRepeat(),
                                    eventManager.isThursdayRepeat(),eventManager.isFridayRepeat(),
                                    eventManager.isSaturdayRepeat()};
            Calendar startCalendar = eventManager.getStartCalendar();
            Calendar endCalendar = eventManager.getEndCalendar();
            do {
                startCalendar.add(Calendar.DATE,1);
                endCalendar.add(Calendar.DATE,1);
            } while(!repeatDays[startCalendar.get(Calendar.DAY_OF_WEEK)]);
            while(startCalendar.before(eventManager.getDateUntilCalendar())){
                EventForm form = new EventForm(eventManager.getEvent(), profile);
                form.setStartTime((Calendar) startCalendar.clone());
                form.setEndTime((Calendar) endCalendar.clone());
                form.setComment(eventManager.getEventComment());
                form.setParent(group);
                form.setResources(eventManager.getResources());
                form.setPriority(eventManager.getEventPriority());
                forms.add(form);
                do {
                    startCalendar.add(Calendar.DATE,1);
                    endCalendar.add(Calendar.DATE,1);
                } while(!repeatDays[startCalendar.get(Calendar.DAY_OF_WEEK)-1]);
            }
        }
        EventForm failForm = null;
        try {
            for(EventForm form : forms)
                if(!form.isValid()){
                    failForm = form;
                    failForm.save(); // since form is invalid it will throw validation exception with proper error message
                }
            for(EventForm form : forms){
                event = form.save();
                for(Resource resource : event.getResourceList()){
                     if(resource instanceof ResourceFile)
                         ((ResourceFile)resource).copyToResourcesDirectory();
                }
            }
            eventManager.dispose();
        } catch (ValidationException error) {
            String errorMesssages = failForm.getErrorsDisplay();
        	JDialog errors = new JOptionPane(errorMesssages,
    				JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION).createDialog(eventManager, "Error");
            errors.pack();
            errors.setVisible(true);
        }
        Organizer.getInstance().update();
        Organizer.getInstance().notifyObservers(profile.getState());
    }

}
