package models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/** This class wraps events which belong to the same group.
 */
public class EventGroup extends Model implements Serializable {
	
	private static final long serialVersionUID = -8544701639746640340L;
	private static final int maxTitleLength = 42;
	private String title;
    private List<Event> childEvents = new LinkedList<>();

    /** Creates EventGroup and sets its title
     * @param title title to be set
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
     * @param event event to be added
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

    /** Returns <code>true</code> if EventGroup consists of only 1 event
     * @return <code>true</code> if EventGroup consists of only 1 event,
     * <code>false</code> otherwise
     */
    public boolean isSingle(){
        return childEvents.size()<=1;
    }
    
    /** Returns maximum length of a title
     * @return maximum length of a title
     */
    public static int getMaxTitleLength() {
    	return maxTitleLength;
    }
}
