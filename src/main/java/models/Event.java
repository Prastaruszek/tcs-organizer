package models;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 04.05.13
 * Time: 22:52
 */
public class Event extends Model implements Serializable {
	
	private static final long serialVersionUID = -4422979245490541800L;
	private EventGroup parent;
    private String comment;
    private Calendar startTime;
    private Calendar endTime;
    private UserProfile profile;

    public Event(String title, String comment, Calendar startTime, Calendar endTime, UserProfile profile) {
        this.profile = profile;
        this.parent = new EventGroup(title);
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
}
