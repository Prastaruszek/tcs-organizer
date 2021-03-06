package models;

import java.util.Calendar;

/** This interface is used as a parent for methods which add single events to some user's event manager.
 *
 */
public interface AddStrategy {
	/** Returns a new event that can be fit into the handler without any overlapping issues.
	 * @param event task-type event that's supposed to be fit into the manager.
	 * @param handler method should return an event that doesn't overlap any of manager's events.
	 * @param Tim timestamp which will be used to determine new event's position.
	 * @return A new event that can be fit into the manager without any overlapping issues.
	 */
	Event getAddableEvent(Task event, EventHandler handler, Calendar Tim);
}