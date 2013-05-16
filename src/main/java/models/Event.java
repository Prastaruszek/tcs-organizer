package models;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 04.05.13
 * Time: 22:52
 */
public class Event extends Model {
    private EventGroup parent;
    private String comment;
    private Calendar startTime;
    private Calendar endTime;

    public Event(String title, String comment, Calendar startTime, Calendar endTime) {
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
