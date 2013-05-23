package forms;

import models.*;

import java.util.Calendar;

public final class EventForm extends ModelForm<Event> {
    private EventGroup parent;
    private String comment;
    private Calendar startTime;
    private Calendar endTime;
    private UserProfile profile;

    public EventForm(Event instance, UserProfile profile) {
        if ( profile == null )
            throw new NullPointerException("Profile cannot be null");
        this.instance = instance;
        this.profile = profile;
    }

    public EventForm(UserProfile profile) {
        this(null, profile);
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

        if ( parent.getTitle().isEmpty()) {
            this.getErrors().appendError("__all__", "Title is required");
        	return false;
        }
        if ( !startTime.before(endTime) ) {
            this.getErrors().appendError("__all__", "End time has to be after start time");
        	return false;
        }

        EventSet events = profile.getEvents().all();
        events.remove(instance);
        for(Event event : events ){
            boolean overlap = false;
            if(event.isBetween(startTime,endTime))
                overlap = true;
            if(event.getStartTime().before(endTime)&&event.getStartTime().after(endTime))
                overlap = true;
            if(event.getStartTime().before(startTime)&&event.getStartTime().after(startTime))
                overlap = true;
            if ( overlap ) {
                this.getErrors().appendError("__all__", "Event overlaps existing one");
                return false;
            }
        }

        if ( instance == null ) {
            setInstance(new Event(
                parent,
                comment,
                startTime,
                endTime,
                profile
            ));
        } else {
            if ( instance.getProfile() != profile )
                return false;
            if ( instance.getParent() != parent )
                return false;
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
        boolean isCreate = instance == null;
        super.save();
        if ( isCreate ) {
            EventManager manager = profile.getEvents();
            manager.add(getInstance());
        } else {
            instance.setEndTime(endTime);
            instance.setStartTime(startTime);
        }
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
