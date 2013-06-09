package models;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

public class Task implements Serializable{
	
	private static final long serialVersionUID = 1237493344848846964L;
	private EventGroup parent;
    private String title;
	private String comment;
	private int durationInMinutes;
	private UserProfile profile;
	private EventPriority priority;

    private List<Resource> resources;

	public Task(EventGroup parent, String comment, int durationInMinutes,
			UserProfile profile, EventPriority priority, List<Resource> resources) {
		this.profile = profile;
		this.parent = parent;
		this.comment = comment;
		this.durationInMinutes = durationInMinutes;
		this.priority = priority;
        this.resources = resources;
    }
	
	public Task(Event event){
		this.profile = event.getProfile();
		this.parent = event.getParent();
		this.comment = event.getComment();
		this.durationInMinutes = (int) ((event.getEndTime().getTimeInMillis() - event.getStartTime().getTimeInMillis()) / 60000L);
		this.priority = event.getPriorityObject();
        this.resources = event.getResources();
	}
    
	public void setDuration(int durationInMinutes){
		this.durationInMinutes = durationInMinutes;
    }
    
	public int getDuration(){
		return durationInMinutes;
	}

	public int getPriority() {
		return priority.getPriority();
	}
    
	public EventPriority getPriorityObject() {
		return priority;
	}
    
	public String getRomanPriority() {
		return priority.getRomanPriority();
	}
    
	public void setPriority(EventPriority priority) {
		this.priority = priority;
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
		return this.title;
	}

    public void setTitle(String title) {
        this.title = title;
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
    
	public String toString(){
		String res = title + ", duration " + durationInMinutes + " minutes, resources: "+resources.size();
		return res;
	}


}
