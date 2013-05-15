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
    private static UserSet users;

    private Organizer() {
        users = new UserSet();
        initializeUser();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UserSet getUsers() {
        return users;
    }

    private void initializeUser() {
        if ( currentUser == null ) {
            DisplayState state = new DisplayState();
            EventSet events = new EventSet();
            currentUser = new User();
            UserProfile profile = new UserProfile(state, events, currentUser);
            users.add(currentUser);
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
