package models;

import java.util.HashSet;

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
        return null;
    }

    public UserSet all() {
        return this;
    }
}
