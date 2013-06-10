package views.gui.components;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Event;

public class JLabelledBtn extends JPanel{
	private JButton button;
	private JLabel label;
	private Event event;
	
	public JLabelledBtn(Event event){
		this.event = event;
		this.button = new JButton("edit");
		this.label = new JLabel(event.toString());
		add(button);
		add(label);
	}
	
	public Event getEvent(){
		return event;
	}
	
}
