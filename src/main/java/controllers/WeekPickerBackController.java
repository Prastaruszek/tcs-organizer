package controllers;

import models.DisplayState;
import models.Organizer;

import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 14.05.13
 * Time: 14:24
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
