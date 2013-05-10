import models.DisplayState;
import models.Event;
import models.UserProfile;
import views.gui.Calendar;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

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
                // itz only test
                Event[] ev = {
                		new models.Event("matematyka dyskretna", "kutasowy",
                		new GregorianCalendar(2013,4,10,11,20), new GregorianCalendar(2013,4,10,15,15)),
                		new models.Event("abc", "asd", 
                		new GregorianCalendar(2013,4,7,8,0 ), new GregorianCalendar(2013,4,10,11,19)),
                		new models.Event("abcd", "asd", 
                        		new GregorianCalendar(2013,4,6,12,0 ), new GregorianCalendar(2013,4,6,13,0)),
                        new models.Event("abcde", "asd", 
                        		new GregorianCalendar(2013,4,6,8,0 ), new GregorianCalendar(2013,4,6,8,10))
                };
                // end of testz
                window.getEventDisplay().setEvents(Arrays.asList(ev));
                window.setVisibility(true);
            }
        });
    }
}
