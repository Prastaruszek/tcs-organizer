package controllers;

import forms.EventForm;
import forms.ValidationException;
import models.*;
import views.gui.EventManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * <code>EventManagerController</code> controls actions invoked by <code>views.gui.EventManager</code>.
 * It allows editing and adding events (if <code>views.gui.EventManager</code> is not in edit mode).
 *
 *
 * @author laiqu
 *
 * @see Event# Model managed by this controller.
 * @see views.gui.EventManager# View for this controller.
 * @see TaskManagerController# Similar class for tasks.
 */
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
        
        for(ResourceFile f : eventManager.getRemovedList())
        	f.removeFromResourcesDirectory();
        
        if(!eventManager.isRepeating()){
            EventForm form = new EventForm(eventManager.getEvent(), profile);
            form.setStartTime(eventManager.getStartCalendar());
            form.setEndTime(eventManager.getEndCalendar());
            form.setTitle(eventManager.getEventTitle());
            form.setComment(eventManager.getEventComment());
            form.setParent(group);
            form.setTasks(eventManager.getTasks());
            form.setPriority(eventManager.getEventPriority());
            forms.add(form);
        } else {
            boolean[] repeatDays = {eventManager.isSundayRepeat(),eventManager.isMondayRepeat(),
                                    eventManager.isTuesdayRepeat(),eventManager.isWednesdayRepeat(),
                                    eventManager.isThursdayRepeat(),eventManager.isFridayRepeat(),
                                    eventManager.isSaturdayRepeat()};
            Calendar startCalendar = eventManager.getStartCalendar();
            Calendar endCalendar = eventManager.getEndCalendar();
            while (!repeatDays[startCalendar.get(Calendar.DAY_OF_WEEK)-1]) {
                startCalendar.add(Calendar.DAY_OF_YEAR,1);
                endCalendar.add(Calendar.DAY_OF_YEAR,1);
            }
            while (startCalendar.before(eventManager.getDateUntilCalendar())) {
                EventForm form = new EventForm(eventManager.getEvent(), profile);
                form.setStartTime((Calendar) startCalendar.clone());
                form.setEndTime((Calendar) endCalendar.clone());
                form.setTitle(eventManager.getEventTitle());
                form.setComment(eventManager.getEventComment());
                form.setParent(group);
                form.setTasks(eventManager.getTasks());
                form.setPriority(eventManager.getEventPriority());
                forms.add(form);
                do {
                    startCalendar.add(Calendar.DAY_OF_YEAR,1);
                    endCalendar.add(Calendar.DAY_OF_YEAR,1);
                } while(!repeatDays[startCalendar.get(Calendar.DAY_OF_WEEK)-1]);
            }
        }
        EventForm failForm = null;
        Collection<Event> events = new LinkedList<>();
        try {
            for(EventForm form : forms)
                if(!form.isValid()){
                    failForm = form;
                    failForm.save(); // since form is invalid it will throw validation exception with proper error message
                }
            for(EventForm form : forms){
                failForm = form;
                event = form.save();
                events.add(event);
                new File(profile.getPath()+event.getRandom()).mkdirs();
                for(Resource resource : event.getResources()){
                     if(resource instanceof ResourceFile) {
                    	 ((ResourceFile)resource).appendPath(event.getRandom());
                         ((ResourceFile)resource).copyToResourcesDirectory();
                     }
                }
            }
            eventManager.dispose();
        } catch (ValidationException error) {
            for(Event ignored : events)
                ignored.delete();
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
