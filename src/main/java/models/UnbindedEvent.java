package models;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

public class UnbindedEvent implements Serializable{
	
	private static final long serialVersionUID = 1237493344848846964L;
	private EventGroup parent;
	private String comment;
	private int durationInMinutes;
	private UserProfile profile;
	private EventPriority priority;

    private List<Resource> resources;

	public UnbindedEvent(EventGroup parent, String comment, int durationInMinutes,
			UserProfile profile, EventPriority priority, List<Resource> resources) {
		this.profile = profile;
		this.parent = parent;
		this.comment = comment;
		this.durationInMinutes = durationInMinutes;
		this.priority = priority;
        this.resources = resources;
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
    
	public String toString(){
		String res = "UnbindedEvent[";
		res += durationInMinutes;
		res += "]";
		return res;
	}
}
