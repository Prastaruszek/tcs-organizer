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
	private static final long serialVersionUID = 5541922632969721520L;
	
	private final EventManager eventManager;

    public EventSet(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public EventSet between(Calendar _startTime, Calendar _endTime) {
        EventSet ret = new EventSet(eventManager);
        Calendar startTime = (Calendar) _startTime.clone();
        Calendar endTime = (Calendar) _endTime.clone();
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        endTime.set(Calendar.HOUR_OF_DAY, 24);
        endTime.set(Calendar.MINUTE, 59);
        for ( Event e : this )
            if ( e.isBetween(startTime, endTime) )
                ret.add(e);
        return ret;
    }

    public EventSet all() {
        EventSet ret = new EventSet(eventManager);
        for ( Event e : this )
            ret.add(e);
        return ret;
    }
}
