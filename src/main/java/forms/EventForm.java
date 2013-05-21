package forms;

import models.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Calendar;

public final class EventForm extends ModelForm<Event> {
    private EventGroup parent;
    private String comment;
    private Calendar startTime;
    private Calendar endTime;

    public EventForm(Event instance) {

    }

    public EventForm() {
        this(null);
    }

    @Override
    public boolean isValid() {
        clean();

        if ( parent == null )
            return false;
        if ( comment == null )
            return false;
        if ( startTime == null )
            return false;
        if ( endTime == null )
            return false;

        if ( parent.getTitle().isEmpty())
        	return false;
        if ( !startTime.before(endTime) )
        	return false;

        EventSet events = Organizer.getInstance().getCurrentUser().getUserProfile().getEvents().all();
        events.remove(instance);
        for(Event event : events ){
            if(event.isBetween(startTime,endTime))
                return false;
            if(event.getStartTime().before(endTime)&&event.getStartTime().after(endTime))
                return false;
            if(event.getStartTime().before(startTime)&&event.getStartTime().after(startTime))
                return false;
        }

        if ( instance == null ) {
            setInstance(new Event(
                parent,
                comment,
                startTime,
                endTime,
                Organizer.getInstance().getCurrentUser().getUserProfile()
            ));
        } else {
            instance.setEndTime(endTime);
            instance.setStartTime(startTime);
        }

        return true;
    }

    @SuppressWarnings("unused")
    private void cleanStartTime() {
        if ( startTime == null )
            return;
        startTime.set(Calendar.SECOND, 0);
    }

    @SuppressWarnings("unused")
    private void cleanEndTime() {
        if ( endTime == null )
            return;
        endTime.set(Calendar.SECOND, 0);
    }

    @Override
    public Event save() throws ValidationException {
        super.save();
        EventManager manager = Organizer.getInstance().getCurrentUser().getUserProfile().getEvents();
        manager.add(getInstance());
        return getInstance();
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setParent(EventGroup parent) {
        this.parent = parent;
    }
}
