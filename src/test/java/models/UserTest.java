package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 23.05.13
 * Time: 00:06
 */
public class UserTest {
    private String username;
    private String password;
    private User user;

    @Before
    public void setUp() throws Exception {
        username = "john_doe";
        password = "secret";
        user = new User(username, password);
    }

    @Test
    public void testGetUsername() throws Exception {
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testHasPassword() throws Exception {
        assertTrue(user.hasPassword(password));
        assertFalse(user.hasPassword("testysomgupie"));
    }

    @Test
    public void testSetPassword() throws Exception {
        String newPassword = "terces";
        assertTrue(user.hasPassword(password));
        user.setPassword(newPassword);
        assertFalse(user.hasPassword(password));
        assertTrue(user.hasPassword(newPassword));

    }
}
