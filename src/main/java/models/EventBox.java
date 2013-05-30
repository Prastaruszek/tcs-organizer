package models;

import javax.swing.*;

public class EventBox extends JCheckBox {

	private static final long serialVersionUID = 5944145824013281841L;
	private Event event;
	
	public Event getEvent(){
		return event;
	}
	
	public EventBox(String s, Event event){
		super(s);
		this.event = event;
	}
}
