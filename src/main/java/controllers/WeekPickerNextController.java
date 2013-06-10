package controllers;

import models.DisplayState;
import models.Organizer;

import java.awt.event.ActionEvent;

/** Switches calendar to the next week.
 * @author n3v
 */
public class WeekPickerNextController extends Controller {
    @Override
    public void actionPerformed(ActionEvent e) {
        Organizer organizer = Organizer.getInstance();
        DisplayState state = organizer.getCurrentUser().getUserProfile().getState();
        state.setNextWeek();
        organizer.notifyObservers(state);
    }
}