package models;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 09.05.13
 * Time: 22:14
 */
public class Organizer extends Observable {
    private User currentUser;
    private static Organizer instance;
    private static UserManager users;

    private Organizer() {
        users = new UserManager();
        initializeUser();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UserManager getUsers() {
        return users;
    }

    private void initializeUser() {
        if ( currentUser == null ) {
            DisplayState state = new DisplayState();
            EventManager events = new EventManager();
            currentUser = new User("user1", "test");
            UserProfile profile = new UserProfile(state, events, currentUser);
            users.add(currentUser);
            //test
            users.add(new User("user2", "test"));
            users.add(new User("user3", "test"));
            users.add(new User("user4", "test"));
            //end test
            currentUser.setUserProfile(profile);
        }
    }

    public static Organizer getInstance() {
        if ( instance == null )
            instance = new Organizer();
        return instance;
    }

    public void update() {
        setChanged();
    }
}
