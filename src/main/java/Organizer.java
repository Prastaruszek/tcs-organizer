import models.DisplayState;
import models.UserProfile;
import views.gui.Calendar;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 09.05.13
 * Time: 22:14
 */
public class Organizer {
    private UserProfile userProfile;

    private Organizer() {}

    public UserProfile getCurrentUser() {
        return userProfile;
    }

    private void initializeUser() {
        if ( userProfile == null ) {
            DisplayState state = new DisplayState();
            userProfile = new UserProfile(state);
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Calendar window = new Calendar();
                window.setVisibility(true);
            }
        });
    }
}
