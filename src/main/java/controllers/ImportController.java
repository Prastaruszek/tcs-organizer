package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import models.Event;
import models.EventSet;
import models.Organizer;
import models.User;


public class ImportController implements ActionListener {
	
	private User currentUser;
	
	public ImportController(User currentUser){
		this.currentUser = currentUser;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser file = new JFileChooser();
		if(file.showDialog(null, "Choose") == JFileChooser.APPROVE_OPTION) {
			String tmpPath = file.getSelectedFile().getAbsolutePath();
			
			EventSet eS = models.EventManager.importEventSet(tmpPath);
			
			models.EventManager manager = currentUser.getUserProfile().getEvents();
			
			for(Event ev : eS)
				manager.add(ev);
			
			
			for(Event ev : currentUser.getUserProfile().getEvents().all())
				System.out.println(ev);
		
			Organizer.getInstance().update();
			Organizer.getInstance().notifyObservers();
		}

	}
	

}
