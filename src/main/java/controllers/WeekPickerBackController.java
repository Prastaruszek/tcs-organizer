package controllers;

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
        Organizer.getInstance().getCurrentUser().getState().setPreviousWeek();
        Organizer.getInstance().notifyObservers(Organizer.getInstance().getCurrentUser().getState());
    }
}
