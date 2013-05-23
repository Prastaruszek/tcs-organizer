package models;

import java.io.Serializable;
import java.util.Calendar;

public class EventManager implements Serializable{
	
	private static final long serialVersionUID = -2897014193380647665L;
	private final EventSet events;

    public EventManager() {
        this.events = new EventSet(this);
    }

    public EventSet all() {
        return events.all();
    }

    public EventSet between(Calendar startTime, Calendar endTime) {
        return events.between(startTime, endTime);
    }

    public boolean add(Event e) {
        return events.add(e);
    }

	public void removeEvent(Event e) {
		events.remove(e);
	}
   
    public String toString(){
    	String res = "EventMananger[";
    	res += events;
    	res += "]";
    	return res;
    }

}
