package forms;

import models.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * <code>EventForm</code> is responsible for process of events
 * creation. <code>instance</code> field is reference to Event
 * associated with this form (in case its null new object is
 * created).
 *
 * @author stnatic
 * @see ModelForm
 */
public final class EventForm extends ModelForm<Event> {
    private EventGroup parent;
    private String title;
    private String comment;
    private Calendar startTime;
    private Calendar endTime;
    private UserProfile profile;
    private List<Task> tasks;
    private EventPriority priority;

    /**
     * Creates new <code>EventForm</code> instance and allows to specify
     * existing Event instance for modification.
     * @param instance <code>Event</code> instance associated with form
     * @param profile <code>UserProfile</code> for which event is created
     */
    public EventForm(Event instance, UserProfile profile) {
        if ( profile == null )
            throw new NullPointerException("Profile cannot be null");
        this.instance = instance;
        this.profile = profile;
    }

    /**
     * Creates <code>EventForm</code> with no instance (new Event will
     * be created after #save() call.
     *
     * @param profile <code>UserProfile</code> for which event is created
     */
    public EventForm(UserProfile profile) {
        this(null, profile);
    }

    /**
     * Validates entered form data. <code>true</code> as return
     * value means, that Event can be safely saved.
     *
     * @return <code>true</code> if entered form data is valid and
     * <code>false</code> otherwise.
     * @see #save()
     */
    @Override
    public boolean isValid() {
        clean();

        if ( parent == null )
            return false;
        if ( comment == null )
            return false;
        if ( title == null )
            return false;

        boolean valid = true;

        if ( startTime == null ){
            this.getErrors().appendError("Time", "Start time is invalid.");
            valid = false;
        }
        if ( endTime == null ){
            this.getErrors().appendError("Time", "End time is invalid.");
            valid = false;
        }

        if ( title.isEmpty()) {
            this.getErrors().appendError("Title", "Cannot be empty");
        	valid = false;
        }
        if ( startTime != null && endTime != null && !startTime.before(endTime) ) {
            this.getErrors().appendError("General", "End time has to be after start time");
            valid = false;
        }

        EventSet events = profile.getEvents().all();
        if(startTime != null && endTime != null ){
            for ( Event event : events ) {
                if ( event == instance )
                    continue;
                boolean overlap = false;
                if(event.isBetween(startTime,endTime))
                    overlap = true;
                if(event.getStartTime().before(endTime)&&event.getEndTime().after(endTime))
                    overlap = true;
                if(event.getStartTime().before(startTime)&&event.getEndTime().after(startTime))
                    overlap = true;
                if ( overlap ) {
                    SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm");
                    this.getErrors().appendError("General", "Event overlaps existing one " + event.getTitle() +  "<br>" +
                            event.getTitle()+" starts: "+ df.format(event.getStartTime().getTime())+
                            " ends: " + df.format(event.getEndTime().getTime()) );
                    valid =  false;
                    break;
                }
            }
        }

        if ( instance != null ) {
            if ( instance.getProfile() != profile )
                return false;
            if ( instance.getParent() != parent )
                return false;
        }

        return valid;
    }

    /**
     * Parses raw Calendar object for this event. Sets
     * startTime seconds and milliseconds to 0.
     */
    @SuppressWarnings("unused")
    private void cleanStartTime() {
        if ( startTime == null )
            return;
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Parses raw Calendar object for this event. Sets
     * endTime seconds and milliseconds to 0.
     */
    @SuppressWarnings("unused")
    private void cleanEndTime() {
        if ( endTime == null )
            return;
        endTime.set(Calendar.SECOND, 0);
        endTime.set(Calendar.MILLISECOND, 0);
    }

    /**
     *
     * @return <code>Event</code> instance after modification/instantiation.
     * @throws ValidationException in case form was not valid.
     * @see ValidationException
     */
    @Override
    public Event save() throws ValidationException {
        boolean isCreate = instance == null;
        if ( instance == null ) {
            setInstance(new Event(
                    parent,
                    comment,
                    startTime,
                    endTime,
                    profile,
                    priority,
                    tasks
            ));
        }
        super.save();
        if ( isCreate ) {
            EventManager manager = profile.getEvents();
            getInstance().getParent().addEvent(getInstance());
            manager.add(getInstance());
        } else {
            instance.setEndTime(endTime);
            instance.setStartTime(startTime);
            instance.setTitle(title);
            instance.setComment(comment);
            instance.setTasks(tasks);
            instance.setPriority(priority);
        }
        return getInstance();
    }

    /**
     * Sets form endTime to parameter value
     * @param endTime
     */
    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    /**
     * Sets form startTime to parameter value
     * @param startTime
     */
    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets form title to parameter value
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets form comment to parameter value
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Sets form parent to parameter value
     * @param parent
     */
    public void setParent(EventGroup parent) {
        this.parent = parent;
    }

    /**
     * Sets form tasks to parameter value
     * @param tasks
     */
    public void setTasks(List<Task> tasks){
    	this.tasks = new LinkedList<>();
    	
    	for(Task t : tasks) {
    		this.tasks.add(t);
    	}
    }

    /**
     * Sets form priority to parameter value
     * @param priority
     */
    public void setPriority(EventPriority priority) {
    	this.priority = priority;
    }
}
