package models;

import factories.UserProfileFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 05.05.13
 * Time: 00:12
 */
public class UserProfileTest {
    @org.junit.Test
    public void testProvideSchedule() throws Exception {
        UserProfile profile = UserProfileFactory.create();
        assertFalse(profile.hasProvidedSchedule());
        profile.provideSchedule();
        assertTrue(profile.hasProvidedSchedule());
    }
}
