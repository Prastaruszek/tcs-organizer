package models;

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

    public UserProfile() {}

    public UserProfile(DisplayState state, EventManager events, User user) {
        this.state = state;
        this.events = events;
        this.user = user;
        this.filesPath = System.getProperty("user.home") + "/.organiser/" + this.user.getUsername() + "/";
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
    	filesPath = newPath;
    }
    
    public String getPath() {
    	return filesPath;
    }
}
