package controllers;

import com.toedter.calendar.JCalendar;
import models.DisplayState;
import models.Organizer;
import views.gui.DatePicker;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 15.05.13
 * Time: 23:29
 */

public class DatePickerController extends Controller {
    private JCalendar jCalendar;
    private DatePicker picker;

    public DatePickerController(JCalendar jCalendar, DatePicker picker) {
        this.jCalendar = jCalendar;
        this.picker = picker;
    }

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
        picker.dispose();
    }
}
