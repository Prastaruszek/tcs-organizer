package models;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 16.05.13
 * Time: 21:04
 */
public class UserManager {
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
            //test
            this.add(new User("user2", "test"));
            this.add(new User("user3", "test"));
            this.add(new User("user4", "test"));
            //end test
            currentUser.setUserProfile(profile);
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
