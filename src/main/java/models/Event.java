package models;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 04.05.13
 * Time: 22:52
 */
public class Event extends Model {
    private EventGroup parent;
    private String comment;
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;

    public Event(String title, String comment, GregorianCalendar startTime, GregorianCalendar endTime) {
        this.parent = new EventGroup(title);
        this.comment = comment;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }

    public void setEndTime(GregorianCalendar endTime) {
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
        return false;
    }
}
