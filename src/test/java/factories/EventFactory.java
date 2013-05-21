package factories;

import models.Event;
import models.EventGroup;

import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 05.05.13
 * Time: 00:40
 */
public class EventFactory {
    private static Class<?> klass = Event.class;

    public static Event create() {
        GregorianCalendar startTime = new GregorianCalendar();
        GregorianCalendar endTime = (GregorianCalendar) startTime.clone();
        endTime.add(GregorianCalendar.HOUR, 1);
        EventGroup group = new EventGroup("Title");
        return new Event(group, "Comment", startTime, endTime, UserProfileFactory.create());
    }
}
