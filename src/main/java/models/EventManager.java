package models;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
	
	public static EventSet importEventSet(String filename){
    	EventSet res = null;
    	try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			res = (EventSet) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return res;
    }
   
    public String toString(){
    	String res = "EventMananger[";
    	res += events;
    	res += "]";
    	return res;
    }

}
