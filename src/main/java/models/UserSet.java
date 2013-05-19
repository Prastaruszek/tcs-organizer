package models;

import java.util.HashSet;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 15.05.13
 * Time: 22:48
 */
public class UserSet extends HashSet<User> {
    private final UserManager userManager;

    public UserSet(UserManager userManager) {
        this.userManager = userManager;
    }

    public User validateUser(String username, String rawPassword) {
    	for(User u : this)
    		if(u.equals(new User(username, rawPassword)))
    			return u;
        return null;
    }

    public UserSet all() {
        return this;
    }
    
    public Vector<String> getUsernames() {
    	User currentUser = Organizer.getInstance().getCurrentUser();
    	Vector<String> names = new Vector<>(this.size());
    	names.add(currentUser.getUsername());
    	for(User u : this)
    		if(!u.equals(currentUser))
    			names.add(u.getUsername());
    	return names;
    }
}
