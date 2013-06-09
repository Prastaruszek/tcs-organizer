package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import models.AddStrategy;
import models.Event;
import models.EventSet;
import models.FirstPossibleEvent;
import models.Organizer;
import models.UnboundEvent;
import models.User;


public class ImportController implements ActionListener {
	
	private User currentUser;
	
	public ImportController(User currentUser){
		this.currentUser = currentUser;
	}
	@Override
	public void actionPerformed(ActionEvent X) {
		JFileChooser file = new JFileChooser();
		if(file.showDialog(null, "Choose") == JFileChooser.APPROVE_OPTION) {
			
			String tmpPath = file.getSelectedFile().getAbsolutePath();
			
			EventSet newEvents = models.EventManager.importEventSet(tmpPath);
			for(Event ev : newEvents)
				ev.setProfile(currentUser.getUserProfile());
			
			models.EventManager manager = currentUser.getUserProfile().getEvents();
			
			EventSet leftover = manager.addSet(newEvents);
			
			if(leftover!=null && leftover.size() > 0)
				System.out.println("leftover");
			
			//przyklad dzialania tej strategii : wynik w konsoli
			AddStrategy AS = new FirstPossibleEvent();
			for(Event ev : leftover){
				System.out.println(ev + " AS prop> " + AS.getAddableEvent(new UnboundEvent(ev), manager, ev.getStartTime()));
			}
		
			Organizer.getInstance().update();
			Organizer.getInstance().notifyObservers();
		}

	}
	

}
