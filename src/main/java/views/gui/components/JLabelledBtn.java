package views.gui.components;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.ImportEditButtonController;

import models.Event;
import models.User;

/** Small panel which combines JButton and JLabel with an event. If the button is pressed, then an EventManager window appears to edit 
 * contents of this event.
 *
 */
public class JLabelledBtn extends JPanel{
	
	private static final long serialVersionUID = -4035129086969430119L;
	private JButton button;
	private JLabel label;
	private Event event;
	
	/** Requires an event upon which this panel is created, also user for whom is all of this nonsense made.
	 * @param event
	 * @param currentUser
	 */
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
