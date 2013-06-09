package models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class EventGroup extends Model implements Serializable {
	
	private static final long serialVersionUID = -8544701639746640340L;
	private static final int maxTitleLength = 42;
	private String title;
    private List<Event> childEvents = new LinkedList<>();

    /** Creates EventGroup and sets its title
     * @param title
     */
    public EventGroup(String title) {
        this.title = title;
    }

    /** Returns title of this EventGroup
     * @return title of this EventGroup
     */
    public String getTitle() {
        return title;
    }

    /** Adds event into the group
     * @param event
     */
    public void addEvent(Event event) {
        childEvents.add(event);
    }

    /** 
     * 	Deletes whole EventGroup with all sub events
     */
    public void delete(){
        for(Event event : childEvents){
            event.delete();
        }
    }

    /** Returns true if EventGroup consists of only 1 event
     * @return true if EventGroup consists of only 1 event
     */
    public boolean isSingle(){
        return childEvents.size()<=1;
    }
    
    /** Returns maximum length of a title
     * @return maxTitleLength
     */
    public static int getMaxTitleLength() {
    	return maxTitleLength;
    }
}
