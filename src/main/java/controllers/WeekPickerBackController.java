package controllers;

import models.DisplayState;
import models.Organizer;

import java.awt.event.ActionEvent;


/**
 * @author n3v
 * Sets previous week on the calendar
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
