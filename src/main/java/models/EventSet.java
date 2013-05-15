package models;

import java.util.Calendar;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 15.05.13
 * Time: 22:37
 */
public class EventSet extends HashSet<Event> {
    public EventSet between(Calendar startTime, Calendar endTime) {
        EventSet ret = new EventSet();
        for ( Event e : this )
            if ( e.isBetween(startTime, endTime) )
                ret.add(e);
        return ret;
    }
}
