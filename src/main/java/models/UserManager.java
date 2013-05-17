package models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 16.05.13
 * Time: 21:04
 */
public class UserManager {
    UserSet users;

    UserManager() {
        users = new UserSet(this);
    }

    public User validateUser(String username, String rawPassword) {
        return users.validateUser(username, rawPassword);
    }

    public UserSet all() {
        return users;
    }

    public Set<String> getUsernames() {
    	HashSet<String> names = new HashSet<>();
    	for(User u : users)
    		names.add(u.getUsername());
    	return names;
    }
    
    public boolean add(User user) {
        return users.add(user);
    }
}
