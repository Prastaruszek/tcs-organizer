import models.*;
import views.gui.Calendar;

import java.awt.*;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 14.05.13
 * Time: 14:35
 */
public class Main {
    /**
     * Launch the application.
     */
    private static Organizer organizer = Organizer.getInstance();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Calendar window = new Calendar();
                organizer.addObserver(window);
                // itz only test
                models.Event[] ev = {
                        new models.Event("matematyka dyskretna", "kutasowy",
                                new GregorianCalendar(2013, 4, 10, 11, 20), new GregorianCalendar(2013, 4, 10, 15, 15)),
                        new models.Event("abc", "asd",
                                new GregorianCalendar(2013, 4, 7, 8, 0), new GregorianCalendar(2013, 4, 10, 11, 19)),
                        new models.Event("abcd", "asd",
                                new GregorianCalendar(2013, 4, 6, 12, 0), new GregorianCalendar(2013, 4, 6, 13, 0)),
                        new models.Event("abcde", "asd",
                                new GregorianCalendar(2013, 4, 6, 8, 0), new GregorianCalendar(2013, 4, 6, 8, 10))
                };
                // end of testz
                window.getEventDisplay().setEvents(Arrays.asList(ev));
                window.setVisibility(true);
            }
        });
    }
}
