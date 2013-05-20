package models;

import java.io.Serializable;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 16.05.13
 * Time: 21:04
 */
public class UserManager implements Serializable{

	private static final long serialVersionUID = 6782682832616034376L;
	UserSet users;
    User currentUser;

    UserManager() {
        users = new UserSet(this);
        initializeUser();
    }

    private void initializeUser() {
        if ( currentUser == null ) {
            DisplayState state = new DisplayState();
            EventManager events = new EventManager();
            currentUser = new User("user1", "test");
            UserProfile profile = new UserProfile(state, events, currentUser);
            this.add(currentUser);
            currentUser.setUserProfile(profile);
            //test
            for(int i = 2; i < 5; i++) {
            	state = new DisplayState();
            	events = new EventManager();
            	User u = new User("user" + i, "test");
            	profile = new UserProfile(state, events, u);
            	this.add(u);
            	u.setUserProfile(profile);
            }
            //end test
        }
    }
    
    public User getCurrentUser() {
    	return currentUser;
    }
    
    public User validateUser(String username, String rawPassword) {
    	User u = users.validateUser(username, rawPassword);
    	if(u != null)
    		currentUser = u;
        return u;
    }
    
    public UserSet all() {
        return users;
    }

    public Vector<String> getUsernames() {
    	return users.getUsernames();
    }
    
    public boolean add(User user) {
        return users.add(user);
    }
}
