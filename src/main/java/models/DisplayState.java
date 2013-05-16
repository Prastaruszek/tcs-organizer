package models;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 09.05.13
 * Time: 22:31
 */
public class DisplayState extends Model {
    private final int firstDayOfWeek = Calendar.MONDAY;
    private Calendar firstDay;
    private Calendar lastDay;

    public DisplayState() {
        Calendar c = new GregorianCalendar();
        _setWeek(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );
    }

    public Calendar getFirstDay() {
        return firstDay;
    }

    public Calendar getLastDay() {
        return lastDay;
    }

    private void _setWeek(int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month, day);
        calendar.setFirstDayOfWeek(firstDayOfWeek);
        firstDay = (Calendar) calendar.clone();
        firstDay.add(Calendar.DAY_OF_YEAR, (-7-calendar.get(Calendar.DAY_OF_WEEK) + 2)%7);
        lastDay = (Calendar) firstDay.clone();
        lastDay.add(Calendar.DAY_OF_WEEK, 6);
    }

    public void setWeek(int year, int month, int day) {
        _setWeek(year, month, day);
        Organizer.getInstance().update();
    }

    public void setNextWeek() {
        firstDay.add(Calendar.DAY_OF_YEAR, 7);
        lastDay.add(Calendar.DAY_OF_YEAR, 7);
        Organizer.getInstance().update();
    }

    public void setPreviousWeek() {
        firstDay.add(Calendar.DAY_OF_YEAR, -7);
        lastDay.add(Calendar.DAY_OF_YEAR, -7);
        Organizer.getInstance().update();
    }

    public String getRangeDisplay() {
        DateFormat d = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
        String from = d.format(getFirstDay().getTime());
        String to = d.format(getLastDay().getTime());
        return from + " to " + to;
    }
}