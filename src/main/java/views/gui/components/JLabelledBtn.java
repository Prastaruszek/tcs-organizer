package views.gui.components;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.ImportEditButtonController;

import models.Event;
import models.User;

public class JLabelledBtn extends JPanel{
	
	private static final long serialVersionUID = -4035129086969430119L;
	private JButton button;
	private JLabel label;
	private Event event;
	
	public JLabelledBtn(Event event, User currentUser){
		this.event = event;
		this.button = new JButton("Edit");
		
		this.label = new JLabel(this.event.toString());
		
		button.addActionListener(new ImportEditButtonController(this, currentUser));
		
		add(button);
		add(label);
	}
	
	public Event getEvent(){
		return event;
	}
	
	public JButton getButton(){
		return button;
	}
	
	public JLabel getLabel(){
		return label;
	}
	
}
