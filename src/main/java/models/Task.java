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
    
	/** Sets the duration for the task
	 * @param durationInMinutes
	 */
	public void setDuration(int durationInMinutes){
		this.durationInMinutes = durationInMinutes;
    }
    
	/** Returns duration of the current task in minutes
	 * @return durationInMinutes
	 */
	public int getDuration(){
		return durationInMinutes;
	}

	/** Returns priority of the current task as an int
	 * @return priority
	 */
	public int getPriority() {
		return priority.getPriority();
	}
    
	/** Returns the priority of the current task as EventPriority object
	 * @return priority
	 */
	public EventPriority getPriorityObject() {
		return priority;
	}
    
	/** Returns the priority of the task as a roman numeral
	 * @return priority
	 */
	public String getRomanPriority() {
		return priority.getRomanPriority();
	}
    
	/** Sets the priority of the current task (influences color)
	 * @param priority
	 */
	public void setPriority(EventPriority priority) {
		this.priority = priority;
	}
    
	/** Returns color with which should this task be drawn
	 * @return Color
	 */
	public Color getColor() {
		return priority.getColor(profile);
	}
    
	/** Returns the comment for the task
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/** Sets the comment for the task
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**	 Returns the title of the current task
	 * @return title
	 */
	public String getTitle() {
		return this.title;
	}

    /** Sets title of the current task
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
	/** Returns the profile related with the current task
	 * @return profile
	 */
	public UserProfile getProfile() {
		return profile;
	}

	/** Returns the parent (EventGroup) of the current task
	 * @return parent
	 */
	public EventGroup getParent() {
		return parent;
	}

	/** Returns List of resources related with the current tast
	 * @return resources
	 */
	public List<Resource> getResources() {
        return resources;
	}

	/**	 Sets List of resources related with the current task
	 * @param resources
	 */
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
    
	public String toString(){
		String res = title + ", duration " + durationInMinutes + " minutes, resources: "+resources.size();
		return res;
	}


}
