package models;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 15.05.13
 * Time: 22:48
 */
public class User extends Model {
    private String username;
    private String password;
    private UserProfile userProfile;

    public String getUsername() {
        return username;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
}