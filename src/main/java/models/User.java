package models;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 15.05.13
 * Time: 22:48
 */
public class User extends Model implements Serializable{
	
	private static final long serialVersionUID = 7429787799167627798L;
	private String username;
    private String hashedPass;
    private UserProfile userProfile;
    
    public User(String username, String rawPass) {
    	this.username = username;
    	this.hashedPass = hashIt(rawPass);
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
    
    public boolean hasPassword(String rawPass){
    	if(hashedPass == null || hashedPass.equals(hashIt(rawPass)))
    		return true;
    	return false;
    }
    
    public void setPassword(String rawPass){
    	hashedPass = hashIt(rawPass);
    }
    
    public String getPassword(){
    	return hashedPass;
    }
    
    public static String hashIt(String rawPass) {
    	byte[] bytePass = rawPass.getBytes(), bytePassAfter;
        MessageDigest md = null;
        try { md = MessageDigest.getInstance("SHA-1"); }
        catch(NoSuchAlgorithmException e) { e.printStackTrace(); }
        String after = "k1$u3&r3!w7#a6m6a6c" + byteArrayToHexString(md.digest(bytePass)) + "kurloiwga7&";
        bytePassAfter = after.getBytes();
        return byteArrayToHexString(md.digest(bytePassAfter));
    }
    
    private static String byteArrayToHexString(byte[] b) {
    	 String result = "";
    	 for (int i=0; i < b.length; i++) {
    		 result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
    	 }
    	 return result;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hashedPass == null) ? 0 : hashedPass.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (hashedPass == null) {
			if (other.hashedPass != null)
				return false;
		} else if (!hashedPass.equals(other.hashedPass))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
