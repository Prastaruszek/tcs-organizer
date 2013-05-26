package models;

import javax.swing.*;

public class EventBox extends JCheckBox {
	
	private Event event;
	
	public Event getEvent(){
		return event;
	}
	
	public EventBox(String s, Event event){
		super(s);
		this.event = event;
	}
}
