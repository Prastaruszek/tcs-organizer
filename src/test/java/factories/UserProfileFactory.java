package factories;

import models.DisplayState;
import models.EventManager;
import models.User;
import models.UserProfile;

public class UserProfileFactory {
    private static Class<?> klass = UserProfile.class;

    public static UserProfile create() {
        User u = UserFactory.create();
        UserProfile ret = new UserProfile(new DisplayState(), new EventManager(), u);
        u.setUserProfile(ret);
        return ret;
    }
}
