package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashSet;

public class EventSet extends HashSet<Event> {
	private static final long serialVersionUID = 5541922632969721520L;
	
	private final EventManager eventManager;

	public EventSet(){
		eventManager = null;
	}
	
    public EventSet(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public EventSet between(Calendar _startTime, Calendar _endTime) {
        EventSet ret = new EventSet(eventManager);
        Calendar startTime = (Calendar) _startTime.clone();
        Calendar endTime = (Calendar) _endTime.clone();
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        endTime.set(Calendar.MINUTE, 59);
        for ( Event e : this )
            if ( e.isBetween(startTime, endTime) )
                ret.add(e);
        return ret;
    }

    public EventSet overlapping(Calendar _startTime, Calendar _endTime) {
        EventSet ret = new EventSet(eventManager);
        Calendar startTime = (Calendar) _startTime.clone();
        Calendar endTime = (Calendar) _endTime.clone();
        startTime.set(Calendar.HOUR_OF_DAY, 0);
        startTime.set(Calendar.MINUTE, 0);
        endTime.set(Calendar.HOUR_OF_DAY, 23);
        endTime.set(Calendar.MINUTE, 59);
        for ( Event e : this )
            if ( e.overlaps(startTime, endTime) )
                ret.add(e);
        return ret;
    }


    public EventSet all() {
        EventSet ret = new EventSet(eventManager);
        for ( Event e : this )
            ret.add(e);
        return ret;
    }
    
    public void exportEventSet(String exportPath, String name){
    	try {
    		String filename = exportPath + "/" + name;
    		Files.deleteIfExists(Paths.get(filename));
    		new File(exportPath).mkdirs();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(this);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public String toString(){
    	String res = "EventSet[";
    	for ( Event e : this )
            res += e;
    	res += "]";
    	return res;
    }
}
