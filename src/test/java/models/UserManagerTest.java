package models;

import factories.UserFactory;
import factories.UserProfileFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 23.05.13
 * Time: 00:13
 */
public class UserManagerTest {
    UserManager manager;
    User user;

    @Before
    public void setUp() throws Exception {
        manager = new UserManager();
        user = UserFactory.create();
        manager.add(user);
    }

    @Test
    public void testValidateUserReturnsUserObjectForExistingUser() throws Exception {
        user.setPassword("secret");
        assertNull(manager.getCurrentUser());
        assertEquals(user, manager.validateUser(user.getUsername(), "secret"));
        assertNull(manager.getCurrentUser());

    }

    @Test
    public void testValidateUserReturnsNullForNonExistingUser() throws Exception {
        user.setPassword("secret");
        assertNull(manager.getCurrentUser());
        assertNull(manager.validateUser(user.getUsername(), "invalid"));
        assertNull(manager.getCurrentUser());
    }

    @Test
    public void testGetByUsernameReturnsUserObjectForExistingUser() throws Exception {
        assertEquals(user, manager.getByUsername(user.getUsername()));
    }

    @Test
    public void testGetByUsernameReturnsNullForNonExistingUse() throws Exception {
        assertNull(manager.getByUsername(user.getUsername() + '_'));
    }
}
