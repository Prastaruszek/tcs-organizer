package models;

import java.util.Calendar;

public interface AddStrategy {
	Event getAddableEvent(UnboundEvent event, EventManager manager, Calendar startTime);
}