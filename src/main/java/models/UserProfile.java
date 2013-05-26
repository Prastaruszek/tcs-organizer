package models;

import views.gui.Calendar;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 05.05.13
 * Time: 00:10
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

    public UserProfile() {}

    public UserProfile(DisplayState state, EventManager events, User user) {
        this.state = state;
        this.events = events;
        this.user = user;
        this.filesPath = System.getProperty("user.home") + "/.organiser/" + this.user.getUsername() + "/";
        this.iconPath = Calendar.DEFAULT_USER_ICON;
        this.velocity = 9000;
    }

    public boolean hasProvidedSchedule() {
        return hasProvidedSchedule;
    }

    public void provideSchedule() {
        this.hasProvidedSchedule = true;
    }

    public DisplayState getState() {
        return state;
    }

    public EventManager getEvents() {
        return events;
    }

    public User getUser() {
        return user;
    }
    
    public void setPath(String newPath) {
    	this.filesPath = newPath;
    }
    
    public String getPath() {
    	return filesPath;
    }
    
    public String getIconPath() {
    	return iconPath;
    }
    
    public void setIconPath(String path) {
    	this.iconPath = path;
    }
    
    public void setVelocity(Integer v) {
    	this.velocity = v;
    }
    
    public Integer getVelocity() {
    	return velocity;
    }
    
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
