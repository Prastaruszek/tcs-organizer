package factories;

import models.DisplayState;
import models.EventHandler;
import models.User;
import models.UserProfile;

public class UserProfileFactory {
    @SuppressWarnings("unused")
	private static Class<?> klass = UserProfile.class;

    public static UserProfile create() {
        User u = UserFactory.create();
        UserProfile ret = new UserProfile(new DisplayState(), new EventHandler(), u);
        u.setUserProfile(ret);
        return ret;
    }
}
