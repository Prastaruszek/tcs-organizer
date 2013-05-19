package models;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 09.05.13
 * Time: 22:14
 */
public class Organizer extends Observable {
    private static Organizer instance;
    private static UserManager users;

    private Organizer() {
        users = new UserManager();
    }

    public User getCurrentUser() {
        return users.getCurrentUser();
    }

    public UserManager getUsers() {
        return users;
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
