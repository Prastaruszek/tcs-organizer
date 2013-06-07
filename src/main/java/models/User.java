package models;

import java.io.Serializable;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User extends Model implements Serializable{
	
	private static final long serialVersionUID = 7429787799167627798L;
	private String username;
    private String password;
    private UserProfile userProfile;
    
    /** Creates a User with given username and proper password(will be stored as a hash).
     * @param username A username.
     * @param rawPass A proper password.
     */
    public User(String username, char[] rawPass) {
    	this.username = username;
    	this.password = hashIt(rawPass);
    }
    
    /** Returns the Username as a String.
     * @return The Username as a String.
     */
    public String getUsername() {
        return username;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile profile) {
        userProfile = profile;
    }
    
    /** Checks if a given proper password is equal to the User's one.
     * @param rawPass A proper password.
     * @return True, if password check was successful. False otherwise.
     */
    public boolean hasPassword(char[] rawPass){
        return password.equals(hashIt(rawPass));
    }
    
    /** Changes the User's password.
     * @param rawPass Proper password(will be stored as a hash).
     */
    public void setPassword(char[] rawPass){
    	password = hashIt(rawPass);
    }
    
    /** Returns hashed password of the User.
     * @return Hashed password as a String.
     */
    public String getPassword(){
    	return password;
    }
    
    /** Takes a char array and applies basic hashing function to it.
     * @param rawPass A char array.
     * @return String after digestion.
     */
    public static String hashIt(char[] rawPass) {
    	byte[] bytePass = Charset.forName("UTF-8").encode(CharBuffer.wrap(rawPass)).array();
    	byte[] bytePassAfter;
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
    
	/** Returns the User's HashCode.
	 * @return User's HashCode.
	 */
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
