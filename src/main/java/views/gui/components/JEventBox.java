package views.gui.components;

import javax.swing.*;

import models.Event;

public class JEventBox extends JCheckBox {

	private static final long serialVersionUID = 5944145824013281841L;
	private Event event;
	
	public Event getEvent(){
		return event;
	}
	
	public JEventBox(String s, Event event){
		super(s);
		this.event = event;
	}
}
