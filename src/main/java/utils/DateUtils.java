package utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: laiq
 * Date: 5/30/13
 * Time: 5:13 PM
 */
public class DateUtils {
    public static String dateDisplay(Calendar calendar) {
        return ""+calendar.get(GregorianCalendar.DAY_OF_MONTH)+"."+
                   (calendar.get(GregorianCalendar.MONTH)+1)+"."+
                   calendar.get(GregorianCalendar.YEAR);
    }
}
