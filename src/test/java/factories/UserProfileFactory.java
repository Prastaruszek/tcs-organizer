package factories;

import models.DisplayState;
import models.EventManager;
import models.User;
import models.UserProfile;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 05.05.13
 * Time: 00:31
 */
public class UserProfileFactory {
    private static Class<?> klass = UserProfile.class;

    public static UserProfile create() {
        User u = new User("john_doe", "secret");
        UserProfile ret = new UserProfile(new DisplayState(), new EventManager(), u);
        u.setUserProfile(ret);
        return ret;
    }
}
