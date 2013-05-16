package models;

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

    public boolean add(User user) {
        return users.add(user);
    }
}
