package controllers;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

import models.*;
import views.gui.ImportManager;

import models.Event;
import models.EventSet;
import models.Organizer;
import models.User;


/** This class is used when User presses the "import" button in main calendar view.
 * It tries to add as many events as it can without any overlapping issues, then, if there are any events still to add,
 * creates an instance of ImportManager.
 * @see ImportManager
 */
public class ImportController extends Controller {
	
	private User currentUser;
	
	public ImportController(User currentUser){
		this.currentUser = currentUser;
	}
	@Override
	public void actionPerformed(ActionEvent X) {
		JFileChooser file = new JFileChooser();
		if(file.showDialog(null, "Choose") == JFileChooser.APPROVE_OPTION) {
			
			String tmpPath = file.getSelectedFile().getAbsolutePath();
			
			EventSet newEvents = EventHandler.importEventSet(tmpPath);
			for(Event ev : newEvents)
				ev.setProfile(currentUser.getUserProfile());
			
			EventHandler manager = currentUser.getUserProfile().getEvents();
			
			EventSet leftover = manager.addSet(newEvents);
			
		
			Organizer.getInstance().update();
			Organizer.getInstance().notifyObservers();
			
			if(leftover!=null && leftover.size() > 0){
				System.out.println("leftover");
				ImportManager.getInstance(leftover, currentUser).setVisible(true);
			}
		}

	}
	

}
