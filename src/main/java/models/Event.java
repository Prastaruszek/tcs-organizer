package models;

import java.awt.Color;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class Event extends Model implements Serializable {
	
	private static final long serialVersionUID = -4422979245490541800L;
    private EventGroup parent;
    private String comment;
    private Calendar startTime;
    private Calendar endTime;
    private UserProfile profile;
    private EventPriority priority;

    private List<Resource> resources;

    public Event(EventGroup parent, String comment, Calendar startTime,
                 Calendar endTime, UserProfile profile, EventPriority priority, List<Resource> resources) {
        this.profile = profile;
        this.parent = parent;
        this.comment = comment;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.resources = resources;
    }

    public Calendar getStartTime() {
        return (Calendar) startTime.clone();
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = (Calendar) startTime.clone();
    }

    public Calendar getEndTime() {
        return (Calendar) endTime.clone();
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = (Calendar) endTime.clone();
    }

    public int getPriority() {
    	return priority.getPriority();
    }
    
    public String getRomanPriority() {
    	return priority.getRomanPriority();
    }
    
    public Color getColor() {
    	return priority.getColor(profile);
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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public Event save() {
        if ( profile == null )
            throw new IllegalArgumentException("Profile violates not null constraint");
        profile.getEvents().add(this);
        return this;
    }

    public void delete() {
    	for(Resource r : resources)
    		if(r instanceof ResourceFile)
    			((ResourceFile) r).removeFromResourcesDirectory();
        profile.getEvents().removeEvent(this);
    }

    public long duration() {
        return (endTime.getTimeInMillis()-startTime.getTimeInMillis())/1000;
    }

    public boolean isBetween(Calendar startTime, Calendar endTime) {
        Calendar eventStart = getStartTime();
        Calendar eventEnd = getEndTime();
        if ( eventStart == null || eventEnd == null )
            return false;
        return startTime.getTimeInMillis() <= eventStart.getTimeInMillis() &&
               eventEnd.getTimeInMillis() <= endTime.getTimeInMillis();
    }
    
    public String toString(){
    	String res = "Event[";
    	res += startTime.getTime();
    	res += endTime.getTime();
    	res += "]";
    	return res;
    }

    public boolean overlaps(Calendar startTime, Calendar endTime) {
        Calendar eventStart = getStartTime();
        Calendar eventEnd = getEndTime();
        if ( eventStart == null || eventEnd == null )
            return false;
        if ( isBetween(startTime, endTime) )
            return true;
        if ( startTime.getTimeInMillis() <= eventEnd.getTimeInMillis() &&
             eventEnd.getTimeInMillis() <= endTime.getTimeInMillis() )
            return true;
        return startTime.getTimeInMillis() <= eventStart.getTimeInMillis() &&
               eventStart.getTimeInMillis() <= endTime.getTimeInMillis();
    }

    public List<Resource> getResourceList() {
        return resources;
    }
}
