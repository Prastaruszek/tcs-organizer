package models;

import java.util.Calendar;

public interface IAddStrategy {
	Event getAddableEvent(UnbindedEvent event, EventSet eventSet, Calendar startTime);
}
