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
    private String password;
    private UserProfile userProfile;
    
    public User(String username, String rawPass) {
    	this.username = username;
    	this.password = hashIt(rawPass);
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
        return password.equals(hashIt(rawPass));
    }
    
    public void setPassword(String rawPass){
    	password = hashIt(rawPass);
    }
    
    public String getPassword(){
    	return password;
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
        for (byte aB : b) {
            result += Integer.toString((aB & 0xff) + 0x100, 16).substring(1);
        }
    	 return result;
    }
    
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
	
	public String toString(){
		String res = "User[";
		res += ("name:" + username + ", ");
		res += userProfile;
		res += "]";
		return res;
	}
}
