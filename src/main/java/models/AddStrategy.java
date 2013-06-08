package models;

import java.util.Calendar;

public interface AddStrategy {
	Event getAddableEvent(UnbindedEvent event, EventSet eventSet, Calendar startTime);
}