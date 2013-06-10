package models;

import java.util.Calendar;

/**	This class provides a method which returns the earlier - the better non-overlapping position of a event in a event handler.
 *
 */
public class FirstPossibleEvent implements AddStrategy {
	
	public FirstPossibleEvent() { }
	
	/** Produces the earliest possible event that doesn't collide with anything or is set in uncomfortable hours.
	 * @param event task, upon which the event will be constructed.
	 * @param handler provided so that the event won't produce any collisions in the calendar.
	 * @param Tim minimal starting moment for the event.
	 * @return returns the earliest possible event that doesn't collide with anything or is set in uncomfortable hours.
	 */
	@Override
	public Event getAddableEvent(Task event, EventHandler handler,
			 Calendar Tim) {
		Calendar startTime = ((Calendar) Tim.clone()),
					endTime = ((Calendar) Tim.clone());
		endTime.add(Calendar.MINUTE, event.getDuration());
		
		Event[] currEvents = handler.all().getSortedArray();
		
		boolean found = false;
		int j = 0;
		
		while(!found){
			while(j < currEvents.length - 1 && 
					currEvents[j].getEndTime().getTimeInMillis() < startTime.getTimeInMillis())
				j++;
			if(currEvents[j].overlaps(startTime, endTime)){
				int offset = (int)((currEvents[j].getEndTime().getTimeInMillis() - startTime.getTimeInMillis()) / 60000L) + 15;
				startTime.add(Calendar.MINUTE, offset);
				endTime.add(Calendar.MINUTE, offset);
			}
			else if(startTime.get(Calendar.HOUR_OF_DAY) >= 22){
				int offset = (60 - startTime.get(Calendar.MINUTE)) + 60* ( 8 + 23 - startTime.get(Calendar.HOUR_OF_DAY));
				startTime.add(Calendar.MINUTE, offset);
				endTime.add(Calendar.MINUTE, offset);
			}
			else if(startTime.get(Calendar.HOUR_OF_DAY) < 8){
				int offset = (60 - startTime.get(Calendar.MINUTE)) + 60* ( 7 - startTime.get(Calendar.HOUR_OF_DAY));
				startTime.add(Calendar.MINUTE, offset);
				endTime.add(Calendar.MINUTE, offset);
			}
			else if(endTime.get(Calendar.HOUR_OF_DAY) < 8){
				int offset = (60 - startTime.get(Calendar.MINUTE)) + 
						60*( 7 - startTime.get(Calendar.HOUR_OF_DAY) + ((startTime.get(Calendar.HOUR_OF_DAY)>10)?24:0) );
				startTime.add(Calendar.MINUTE, offset);
				endTime.add(Calendar.MINUTE, offset);
			}
			else if(endTime.get(Calendar.HOUR_OF_DAY) > 22){
				int offset = (60 - startTime.get(Calendar.MINUTE)) + 60* ( 8 + 23 - startTime.get(Calendar.HOUR_OF_DAY));
				startTime.add(Calendar.MINUTE, offset);	
				endTime.add(Calendar.MINUTE, offset);
			}
			else
				found = true;
		}
		return new Event(event, startTime, endTime);
	}

}
