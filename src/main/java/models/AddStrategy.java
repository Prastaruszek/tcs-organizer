package models;

import java.util.Calendar;

public interface AddStrategy {
	Event getAddableEvent(Task event, EventManager manager, Calendar startTime);
}