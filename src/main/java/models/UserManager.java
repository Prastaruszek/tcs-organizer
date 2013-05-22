package models;

import java.io.Serializable;
import java.util.Vector;

public class UserManager implements Serializable{

	private static final long serialVersionUID = 6782682832616034376L;
	UserSet users;
    User currentUser;

    UserManager() {
        users = new UserSet(this);
    }

    public void initializeUser(String username, String rawPassword) {
    	User user = new User(username, rawPassword);
        DisplayState state = new DisplayState();
        EventManager events = new EventManager();
        UserProfile profile = new UserProfile(state, events, user);
        user.setUserProfile(profile);
        this.add(user);
    }
    
    public User getCurrentUser() {
    	return currentUser;
    }
    
    public void setCurrentUser(User user) {
    	currentUser = user;
    }
    
    public User validateUser(String username, String rawPassword) {
    	User res = getByUsername(username);
    	if(res != null && res.hasPassword(rawPassword))
    		return res;
    	return null;
    }
    
    public User getByUsername(String username){
    	return users.getByUsername(username);
    }
    
    public UserSet all() {
        return users.all();
    }

    public Vector<String> getUsernames() {
    	return users.getUsernames();
    }
    
    public boolean add(User user) {
        return users.add(user);
    }
    
    public boolean remove(String username, String rawPassword) {
    	User user = validateUser(username, rawPassword);
    	if(user == null)
    		return false;
    	if(user == currentUser)
    		currentUser = null;
    	users.remove(user);
    	return true;
    }
}
