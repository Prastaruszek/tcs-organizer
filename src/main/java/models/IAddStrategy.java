package models;

import java.util.Calendar;

public interface IAddStrategy {
	boolean addEvent(UnbindedEvent event, User user, Calendar startTime);
}
