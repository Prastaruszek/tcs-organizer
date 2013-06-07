package models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class EventGroup extends Model implements Serializable {
	
	private static final long serialVersionUID = -8544701639746640340L;
	private static final int maxTitleLength = 42;
	private String title;
    private List<Event> childEvents = new LinkedList<>();

    public EventGroup(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void addEvent(Event event) {
        childEvents.add(event);
    }

    public void delete(){
        for(Event event : childEvents){
            event.delete();
        }
    }

    public boolean isSingle(){
        return childEvents.size()<=1;
    }
    
    public static int getMaxTitleLength() {
    	return maxTitleLength;
    }
}
