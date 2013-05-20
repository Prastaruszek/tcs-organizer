package models;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 15.05.13
 * Time: 22:48
 */
public class User extends Model implements Serializable{
	
	private static final long serialVersionUID = 7429787799167627798L;
	private String username;
    private String password;
    private UserProfile userProfile;
    
    public User(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile profile) {
        userProfile = profile;
    }

    //Auto-generated hashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	//Auto-generated equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
