package controllers;

import java.awt.event.ActionEvent;
import java.util.Calendar;

import models.AddStrategy;
import models.Event;
import models.Task;
import models.User;

/** On-click adds event specified in the constructor, applying custom strategy.
 *
 */
public class AddStrategyController extends Controller {
	
	private AddStrategy addStrategy;
	private Event event;
	private User currentUser;
	private Calendar time;
	
	public AddStrategyController(AddStrategy addStrategy, Event event, User currentUser, Calendar time) {
		this.addStrategy = addStrategy;
		this.event = event;
		this.currentUser = currentUser;
		this.time = time;
	}
	
	@Override
	public void actionPerformed(ActionEvent X) {
		Event newEvent = addStrategy
				.getAddableEvent(new Task(event), currentUser.getUserProfile().getEvents(), time);
		newEvent.setProfile(currentUser.getUserProfile());
		
		currentUser.getUserProfile().getEvents().add(newEvent);
	}

}
