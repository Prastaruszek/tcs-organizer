package controllers;

import models.DisplayState;
import models.Organizer;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/** Sets the calendar for the specific date.
 * @author n3v
 */
public class WeekPickerController extends DatePickerController {
    @Override
    public void actionPerformed(ActionEvent e) {
        Date data = jCalendar.getDate();
        Organizer organizer = Organizer.getInstance();
        DisplayState state = organizer.getCurrentUser().getUserProfile().getState();
        Calendar c = new GregorianCalendar();
        c.setTime(data);
        state.setWeek(
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_MONTH)
        );
        organizer.notifyObservers(state);
        super.actionPerformed(e);
    }
}
