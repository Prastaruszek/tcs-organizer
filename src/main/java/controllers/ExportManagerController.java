package controllers;

import models.Event;
import models.EventSet;
import models.Organizer;
import views.gui.ExportManager;
import views.gui.components.JEventBox;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

public class ExportManagerController extends Controller {

	ExportManager exportManager;
	
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
			if(box.isSelected())
				eS.add(box.getEvent());
		if(!eS.isEmpty()){
			eS.exportEventSet(dest, Organizer.getInstance().getCurrentUser().getUsername()+"_expo");
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
