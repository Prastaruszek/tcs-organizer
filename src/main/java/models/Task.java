package models;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

/** Part of an event.
 */
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
    
	/** Sets the duration for the task
	 * @param durationInMinutes duration in minutes
	 */
	public void setDuration(int durationInMinutes){
		this.durationInMinutes = durationInMinutes;
    }
    
	/** Returns duration of the current task in minutes
	 * @return duration of the current task in minutes
	 */
	public int getDuration(){
		return durationInMinutes;
	}

	/** Returns priority of the current task as an int
	 * @return priority of the current task as an int
	 */
	public int getPriority() {
		return priority.getPriority();
	}
    
	/** Returns the priority of the current task as EventPriority object
	 * @return priority of the current task as EventPriority object
	 */
	public EventPriority getPriorityObject() {
		return priority;
	}
    
	/** Returns the priority of the task as a roman numeral
	 * @return priority of the task as a roman numeral
	 */
	public String getRomanPriority() {
		return priority.getRomanPriority();
	}
    
	/** Sets the priority of the current task (influences color)
	 * @param priority EventPriority object
	 */
	public void setPriority(EventPriority priority) {
		this.priority = priority;
	}
    
	/** Returns color with which this task should be drawn
	 * @return color with which this task should be drawn
	 */
	public Color getColor() {
		return priority.getColor(profile);
	}
    
	/** Returns the comment for the task
	 * @return comment for the task
	 */
	public String getComment() {
		return comment;
	}

	/** Sets the comment for the task
	 * @param comment comment to be set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**	 Returns the title of the current task
	 * @return title of the current task
	 */
	public String getTitle() {
		return this.title;
	}

    /** Sets title of the current task
     * @param title title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }
	/** Returns the profile related with the current task
	 * @return profile related with the current task
	 */
	public UserProfile getProfile() {
		return profile;
	}

	/** Returns the parent (EventGroup) of the current task
	 * @return parent of the current task
	 */
	public EventGroup getParent() {
		return parent;
	}

	/** Returns List of resources related with the current task
	 * @return List of resources related with the current task
	 */
	public List<Resource> getResources() {
        return resources;
	}

	/** Sets List of resources related with the current task
	 * @param resources resources to be set
	 */
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
    
	/** Returns string representation of this task
     * @return string representation of this task
     */
	public String toString(){
		String res = title + ", duration " + durationInMinutes + " minutes, resources: "+resources.size();
		return res;
	}


}
