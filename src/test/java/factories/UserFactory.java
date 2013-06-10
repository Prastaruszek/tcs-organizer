package factories;

import models.User;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 23.05.13
 * Time: 00:16
 */
public class UserFactory {
    @SuppressWarnings("unused")
	private static Class<?> klass = User.class;

    public static User create() {
        User ret = new User("john_doe", "secret".toCharArray());
        return ret;
    }
}
