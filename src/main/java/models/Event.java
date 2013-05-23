package models;

import java.io.Serializable;
import java.util.Calendar;

public class Event extends Model implements Serializable {
	
	private static final long serialVersionUID = -4422979245490541800L;
    private EventGroup parent;
    private String comment;
    private Calendar startTime;
    private Calendar endTime;
    private UserProfile profile;

    public Event(EventGroup parent, String comment, Calendar startTime, Calendar endTime, UserProfile profile) {
        this.profile = profile;
        this.parent = parent;
        this.comment = comment;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return this.parent.getTitle();
    }

    public UserProfile getProfile() {
        return profile;
    }

    public EventGroup getParent() {
        return parent;
    }

    public Event save() {
        if ( profile == null )
            throw new IllegalArgumentException("Profile violates not null constraint");
        profile.getEvents().add(this);
        return this;
    }

    public long duration() {
        return (endTime.getTimeInMillis()-startTime.getTimeInMillis())/1000;
    }

    public boolean isBetween(Calendar startTime, Calendar endTime) {
        Calendar eventStart = getStartTime();
        Calendar eventEnd = getEndTime();
        if ( eventStart == null || eventEnd == null )
            return false;
        return (eventStart.equals(startTime) || eventStart.after(startTime)) &&
               (eventEnd.equals(endTime) || eventEnd.before(endTime));
    }
    
    public String toString(){
    	String res = "Event[";
    	res += startTime.getTime();
    	res += endTime.getTime();
    	res += "]";
    	return res;
    }
}
