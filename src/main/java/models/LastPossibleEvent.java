package models;

import java.util.Calendar;

public class LastPossibleEvent implements AddStrategy {

	public LastPossibleEvent() { }
	
	@Override
	public Event getAddableEvent(Task event, EventManager manager,
			Calendar Tim) {
		Calendar startTime = ((Calendar) Tim.clone()),
				endTime = ((Calendar) Tim.clone());
		startTime.add(Calendar.MINUTE, -event.getDuration());
		
		Event[] currEvents = manager.all().getSortedArray();
		
		boolean found = false;
		int j = currEvents.length-1;
		
		while(!found){
			while(j > 0 && 
					currEvents[j].getStartTime().getTimeInMillis() > endTime.getTimeInMillis())
				j--;
			
			if(currEvents[j].overlaps(startTime, endTime)){
				int offset = (int) ((endTime.getTimeInMillis() - currEvents[j].getStartTime().getTimeInMillis()) / 60000L) + 15;
				startTime.add(Calendar.MINUTE, -offset);
				endTime.add(Calendar.MINUTE, -offset);
			}
			else if(startTime.get(Calendar.HOUR_OF_DAY) >= 22){
				int offset = (int) (endTime.get(Calendar.MINUTE) + 60*(endTime.get(Calendar.HOUR_OF_DAY) + ((endTime.get(Calendar.HOUR_OF_DAY)<8)?24:0) - 23));
				startTime.add(Calendar.MINUTE, -offset);
				endTime.add(Calendar.MINUTE, -offset);
			}
			else if(startTime.get(Calendar.HOUR_OF_DAY) < 8){
				int offset = (int) (endTime.get(Calendar.MINUTE) + 60*(endTime.get(Calendar.HOUR_OF_DAY) + 2) );
				startTime.add(Calendar.MINUTE, -offset);
				endTime.add(Calendar.MINUTE, -offset);
			}
			else if(endTime.get(Calendar.HOUR_OF_DAY) > 22){
				int offset = (int) (endTime.get(Calendar.MINUTE) + 60*(endTime.get(Calendar.HOUR_OF_DAY) - 23) );
				startTime.add(Calendar.MINUTE, -offset);
				endTime.add(Calendar.MINUTE, -offset);
			}
			else if(endTime.get(Calendar.HOUR_OF_DAY) < 8){
				int offset = (int) (endTime.get(Calendar.MINUTE) + 60*(endTime.get(Calendar.HOUR_OF_DAY) + 2) );
				startTime.add(Calendar.MINUTE, -offset);
				endTime.add(Calendar.MINUTE, -offset);
			}
			else
				found = true;
		}
		return new Event(event, startTime, endTime);
	}

}
