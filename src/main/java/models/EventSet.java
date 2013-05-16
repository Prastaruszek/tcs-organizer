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
    private final EventManager eventManager;

    public EventSet(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public EventSet between(Calendar startTime, Calendar endTime) {
        EventSet ret = new EventSet(eventManager);
        for ( Event e : this )
            if ( e.isBetween(startTime, endTime) )
                ret.add(e);
        return ret;
    }

    public EventSet all() {
        return this;
    }
}
