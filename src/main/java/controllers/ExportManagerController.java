package controllers;

import models.Event;
import models.EventSet;
import models.Organizer;
import views.gui.ExportManager;
import views.gui.components.JEventBox;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
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

	public static List<JEventBox> getBoxes() {
		List<JEventBox> list = new ArrayList<>();
		EventSet events = Organizer.getInstance().getCurrentUser().getUserProfile().getEvents().all();
		Event[] evlist = new Event[events.size()];
		
		int it = 0;
		for(Event e : events){
			evlist[it] = e;
			it++;
		}
		
		Comparator<Event> c = new Comparator<Event>(){
			@Override
			public int compare(Event o1, Event o2) {
				if(o1.getStartTime().getTimeInMillis() < o2.getStartTime().getTimeInMillis())
					return -1;
				else if(o1.getStartTime().getTimeInMillis() == o2.getStartTime().getTimeInMillis())
					return 0;
				else return 1;
			}
		};
		Arrays.sort(evlist, c);
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
					
			
			list.add(new JEventBox(e.getTitle() + " " + startDate +  "  till  " + endDate, e));
		}
		return list;
	}

}
