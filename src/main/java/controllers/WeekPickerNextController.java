package controllers;

import models.Organizer;

import java.awt.event.ActionEvent;
import java.text.DateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 14.05.13
 * Time: 15:24
 */
public class WeekPickerNextController extends Controller {
    @Override
    public void actionPerformed(ActionEvent e) {
        Organizer.getInstance().getCurrentUser().getState().setNextWeek();
        Organizer.getInstance().notifyObservers(Organizer.getInstance().getCurrentUser().getState());
    }
}