package controllers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
			
			String ser = null;
			
			String tmpPath = file.getSelectedFile().getAbsolutePath();
			
			try {
			
				ZipInputStream zis = new ZipInputStream(new FileInputStream(tmpPath));
				ZipEntry ze;
				zis.mark(0);
				while((ze = zis.getNextEntry()) != null) {
					if(ze.getName().equals("Events.ser")) {
						FileOutputStream fos = new FileOutputStream("Events.ser");
						int len;
						byte[] buffer = new byte[1024];
						while((len = zis.read(buffer)) > 0)
							fos.write(buffer, 0, len);
	
						fos.close();
						ser = "Events.ser";
					} else {
						new File(new File(Organizer.getInstance().getCurrentUser().getUserProfile().getPath() + "/" + ze.getName()).getParent()).mkdirs();
						FileOutputStream fos = new FileOutputStream(Organizer.getInstance().getCurrentUser().getUserProfile().getPath() + "/" + ze.getName());
						int len;
						byte[] buffer = new byte[1024];
						while((len = zis.read(buffer)) > 0)
							fos.write(buffer, 0, len);
						
						fos.close();
					}
				}
			
				zis.close();
			
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(ser == null)
				return;
			
			tmpPath = ser;
			
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
			
			new File(ser).delete();
		}

	}
	

}
