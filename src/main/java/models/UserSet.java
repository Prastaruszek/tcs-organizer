package models;

import java.util.HashSet;
import java.util.Vector;

public class UserSet extends HashSet<User> {
	private static final long serialVersionUID = 3451957181511705129L;
	
	private final UserManager userManager;

    public UserSet(UserManager userManager) {
        this.userManager = userManager;
    }

    public UserSet all() {
        UserSet ret = new UserSet(userManager);
        for ( User u : this )
            ret.add(u);
        return ret;
    }
    
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
    
    public String toString(){
    	String res = "UserSet[";
    	for(User u : this)
    		res += (u + ", ");
    	res += "]";
    	return res;
    }
}
