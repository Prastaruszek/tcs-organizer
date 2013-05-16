package models;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 16.05.13
 * Time: 21:10
 */
public class EventManager {
    private final EventSet events;

    public EventManager() {
        this.events = new EventSet(this);
    }

    public EventSet between(Calendar startTime, Calendar endTime) {
        return events.between(startTime, endTime);
    }

    public boolean add(Event e) {
        return events.add(e);
    }
}
