package models;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 09.05.13
 * Time: 22:14
 */
public class Organizer extends Observable {
    private UserProfile userProfile;
    private static Organizer instance;

    private Organizer() {
        initializeUser();
    }

    public UserProfile getCurrentUser() {
        return userProfile;
    }

    private void initializeUser() {
        if ( userProfile == null ) {
            DisplayState state = new DisplayState();
            userProfile = new UserProfile(state);
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
