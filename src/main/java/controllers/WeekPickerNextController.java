package controllers;

import models.DisplayState;
import models.Organizer;

import java.awt.event.ActionEvent;

/**
 * @author n3v
 *  Switches callendar to the next week
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