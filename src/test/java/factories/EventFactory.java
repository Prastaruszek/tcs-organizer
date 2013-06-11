package factories;

import models.*;

import java.util.GregorianCalendar;
import java.util.LinkedList;

public class EventFactory {
    @SuppressWarnings("unused")
	private static Class<?> klass = Event.class;

    public static Event create() {
        GregorianCalendar startTime = new GregorianCalendar();
        GregorianCalendar endTime = (GregorianCalendar) startTime.clone();
        endTime.add(GregorianCalendar.HOUR, 1);
        EventGroup group = new EventGroup("Title");
        return new Event(group, "Comment", startTime, endTime, UserProfileFactory.create(), EventPriority.getEnum(1), new LinkedList<Task>());
    }
}
