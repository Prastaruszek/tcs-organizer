package models;

import views.gui.Calendar;

import java.awt.Color;
import java.io.Serializable;

/** This class stores unique properties and management tools for a particular user.
 */
public class UserProfile extends Model implements Serializable{
	
	private static final long serialVersionUID = -251344167894262924L;
	private DisplayState state;
    private EventManager events;
    private User user;
    private boolean hasProvidedSchedule;
    private String filesPath;
    private String iconPath;
    private Integer velocity;
    private Color[] priorityColor = new Color[5];

    /** Constructs a new user profile.
     */
    public UserProfile() {}

    /** Constructs a new user profile, assigned to the given user, with a given state and event manager.
     * @param state state to be associated with the user.
     * @param events event manager to be associated with the user.
     * @param user user which the created user profile is assigned to.
     */
    public UserProfile(DisplayState state, EventManager events, User user) {
        this.state = state;
        this.events = events;
        this.user = user;
        this.filesPath = System.getProperty("user.home") + "/.organiser/" + this.user.getUsername() + "/";
        this.iconPath = Calendar.DEFAULT_USER_ICON;
        this.velocity = 9000;
    }

    /** Checks if the user has provided schedule.
     * @return true if the user has provided schedule, false otherwise.
     */
    public boolean hasProvidedSchedule() {
        return hasProvidedSchedule;
    }

    /** Marks that user has provided schedule.
     */
    public void provideSchedule() {
        this.hasProvidedSchedule = true;
    }

    /** Returns display state of this user profile.
     * @return display state of this user profile.
     */
    public DisplayState getState() {
        return state;
    }

    /** Returns event manager of this user profile.
     * @return event manager of this user profile.
     */
    public EventManager getEvents() {
        return events;
    }

    /** Returns user which this user profile is assigned to.
     * @return user which this user profile is assigned to.
     */
    public User getUser() {
        return user;
    }
    
    /** Sets path to folder where user items will be stored.
     * @param newPath string representing path.
     */
    public void setPath(String newPath) {
    	this.filesPath = newPath;
    }
    
    /** Returns path to folder where user items are stored.
	 * @return string with path to folder where user items are stored.
	 */
    public String getPath() {
    	return filesPath;
    }
    
    /** Returns user icon path.
	 * @return string with user icon path.
	 */
    public String getIconPath() {
    	return iconPath;
    }
    
    /** Sets user icon path.
     * @param path string representing icon path.
     */
    public void setIconPath(String path) {
    	this.iconPath = path;
    }
    
    /** Sets user velocity.
     * @param v velocity to be set.
     */
    public void setVelocity(Integer v) {
    	this.velocity = v;
    }
    
    /** Returns user velocity.
     * @return user velocity.
     */
    public Integer getVelocity() {
    	return velocity;
    }
    
    /** Returns color for events with a given priority.
     * @param priority priority number, must be in [1,5].
     * @return color for events with a given priority.
     * @throws IndexOutOfBoundsException if priority is not in [1,5].
     */
    public Color getPriorityColor(int priority) {
    	return priorityColor[priority-1];
    }
    
    /** Sets color for events with a given priority.
     * @param priority priority number, must be in [1,5].
     * @param newColor color to be set for events with a given priority.
     */
    public void setPriorityColor(int priority, Color newColor) {
    	if(priority < 1 || priority > 5)
    		return;
    	if(newColor != null)
    		priorityColor[priority-1] = newColor;
    }
    
    /** Returns string representation of this user profile.
     * @return string representation of this user profile.
     */
    public String toString(){
    	String res = "UserProfile[";
    	res += (state + ", ");
    	res += (events + ", ");
    	res += ("schedule:" + hasProvidedSchedule + ", ");
    	res += ("velocity:" + velocity);
    	res += "]";
    	return res;
    }
}
