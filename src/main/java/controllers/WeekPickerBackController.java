package controllers;

import models.DisplayState;
import models.Organizer;

import java.awt.event.ActionEvent;


/** Sets previous week on the calendar.
 * @author n3v
 */
public class WeekPickerBackController extends Controller {
    @Override
    public void actionPerformed(ActionEvent e) {
        Organizer organizer = Organizer.getInstance();
        DisplayState state = organizer.getCurrentUser().getUserProfile().getState();
        state.setPreviousWeek();
        organizer.notifyObservers(state);
    }
}
