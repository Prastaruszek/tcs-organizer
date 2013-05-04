package factories;

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
        return new UserProfile();
    }
}
