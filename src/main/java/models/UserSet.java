package models;

import java.util.HashSet;
import java.util.Vector;

/** A set which stores instances of {@link User User} class.
 */
public class UserSet extends HashSet<User> {
	private static final long serialVersionUID = 3451957181511705129L;
	
	private final UserManager userManager;
	
	/** Constructs a new, empty set of users with a given user manager.
     * @param userManager manager which will handle the created set.
     */
    public UserSet(UserManager userManager) {
        this.userManager = userManager;
    }
    
    /** Creates and returns new user set containing all users in this set.
     * @return user set containing all users in this set
     */
    public UserSet all() {
        UserSet ret = new UserSet(userManager);
        for ( User u : this )
            ret.add(u);
        return ret;
    }
    
    /** Returns user with a given username.
     * @param username string representing the username.
     * @return user with the given username, null if there is no such user in this user set.
     */
    public User getByUsername(String username){
    	User res = null;
    	for(User u : this){
    		if(u.getUsername().equals(username)){
    			res = u;
    			break;
    		}
    	}
    	return res;
    }
    
    /** Returns vector containing names of all users in this set.
     * The usernames are returned in no particular order except the first element,
     * which is the name of the current user.
     * @return vector containing names of all users in this set.
     */
    public Vector<String> getUsernames() {
    	User currentUser = userManager.getCurrentUser();
    	Vector<String> names = new Vector<>(this.size());
    	if(currentUser != null)
    		names.add(currentUser.getUsername());
    	for(User u : this)
    		if(!u.equals(currentUser))
    			names.add(u.getUsername());
    	return names;
    }
    
    /** Returns string representation of this user set.
     * @return string representation of this user set.
     */
    public String toString(){
    	String res = "UserSet[";
    	for(User u : this)
    		res += (u + ", ");
    	res += "]";
    	return res;
    }
}
