package controllers;

import models.Event;
import models.EventSet;
import models.Organizer;
import models.Resource;
import models.ResourceFile;
import models.Task;
import views.gui.ExportManager;
import views.gui.components.JEventBox;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JOptionPane;

/** Handles export process.
 */
public class ExportManagerController extends Controller {

	ExportManager exportManager;
	
	/**
	 * Constructs a new controller associated with a given export manager window.
	 * @param exportManager the associated export manager window.
	 */
	public ExportManagerController(ExportManager exportManager) {
		super();
		this.exportManager = exportManager;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String dest = exportManager.getDestination().getText();
		if(dest.equals("Pick a destination")){
			new JOptionPane("Please select a destination folder", JOptionPane.ERROR_MESSAGE,
				JOptionPane.DEFAULT_OPTION).createDialog(exportManager, "Error").setVisible(true);
			return;
		}
		EventSet eS = new EventSet();
		for(JEventBox box : exportManager.getEventBoxes())
			if(box.isSelected()) {
				Event tmp = box.getEvent();
				eS.add(tmp);
			}
		if(!eS.isEmpty()){
			eS.exportEventSet(Organizer.getInstance().getCurrentUser().getUserProfile().getPath(), "Events.ser");
			try {
				FileOutputStream fos = new FileOutputStream(dest + "/events.zip");
				ZipOutputStream zos = new ZipOutputStream(fos);
				ZipEntry ze = new ZipEntry("Events.ser");
				FileInputStream in = new FileInputStream(Organizer.getInstance().getCurrentUser().getUserProfile().getPath()+ "/Events.ser");
				
				zos.putNextEntry(ze);
				
				byte[] buffer = new byte[1024];
				
				int len;
				while((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				
				in.close();
				zos.closeEntry();
				
				for(Event e : eS) {
					for(File f : new File(e.getProfile().getPath() + "/" + e.getRandom()).listFiles()) {
						if(f.isDirectory())
							continue;
						ze = new ZipEntry(e.getRandom() + "/" + f.getName());
						
						zos.putNextEntry(ze);
						
						in = new FileInputStream(f);
						
						while((len = in.read(buffer)) > 0) {
							zos.write(buffer, 0, len);
						}
						
						in.close();
						zos.closeEntry();
					}
					ze = new ZipEntry(e.getRandom());
				}				
				
				zos.close();
				
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			new File(Organizer.getInstance().getCurrentUser().getUserProfile().getPath(), "Events.ser").delete();
		}
		else{
			new JOptionPane("No events have been selected", JOptionPane.ERROR_MESSAGE,
					JOptionPane.DEFAULT_OPTION).createDialog(exportManager, "Error").setVisible(true);
			return;
		}
		exportManager.dispose();
	}

	/** Creates readable and sorted by date list of JEventBoxes containing Events of the User.
	 * Only used in ExportManager.
	 * @return Sorted List of JEventBoxes.
	 */
	public static List<JEventBox> getBoxes() {
		List<JEventBox> list = new ArrayList<>();
		Event[] evlist = Organizer.getInstance().getCurrentUser().getUserProfile().getEvents().all().getSortedArray();
		
		for(Event e : evlist){
			String startDate = new String(), endDate = new String();
			Calendar tmp = e.getStartTime();
			startDate += tmp.get(Calendar.HOUR_OF_DAY) + 
					":" + ( (tmp.get(Calendar.MINUTE)<10 )?"0":"") + 
					tmp.get(Calendar.MINUTE) +
					" " +tmp.get(Calendar.DAY_OF_MONTH) + 
					"." + (tmp.get(Calendar.MONTH)+1) + 
					"." + tmp.get(Calendar.YEAR);
			
			tmp = e.getEndTime();
			
			endDate += tmp.get(Calendar.HOUR_OF_DAY) + 
					":" + ( (tmp.get(Calendar.MINUTE)<10 )?"0":"") + 
					tmp.get(Calendar.MINUTE) +
					" " +tmp.get(Calendar.DAY_OF_MONTH) + 
					"." + (tmp.get(Calendar.MONTH)+1) + 
					"." + tmp.get(Calendar.YEAR);
					
			
			list.add(new JEventBox(e.getTitle() + "  Starts: " + startDate +  "  Ends: " + endDate, e));
		}
		return list;
	}

}
