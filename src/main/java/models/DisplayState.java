package models;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
        setWeek(
            c.get(Calendar.YEAR),
            c.get(Calendar.MONTH),
            c.get(Calendar.DAY_OF_YEAR)
        );
    }

    public Calendar getFirstDay() {
        return firstDay;
    }

    public Calendar getLastDay() {
        return lastDay;
    }

    public void setWeek(int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month, day);
        calendar.setFirstDayOfWeek(firstDayOfWeek);
        firstDay = (Calendar) calendar.clone();
        firstDay.add(Calendar.DAY_OF_YEAR, -calendar.get(Calendar.DAY_OF_WEEK) + 2);
        lastDay = (Calendar) firstDay.clone();
        lastDay.add(Calendar.DAY_OF_WEEK, 6);
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
}