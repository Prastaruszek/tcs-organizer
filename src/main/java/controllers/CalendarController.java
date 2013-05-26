package controllers;

import models.Organizer;
import views.gui.components.JEventDisplay;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class CalendarController extends Controller implements Observer {
	JEventDisplay display;
	public CalendarController(JEventDisplay eventDisplay) {
		display=eventDisplay;
		update(null, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		display.setEvents(Organizer.getInstance().getCurrentUser().getUserProfile().getState().getCurrentWeekEvents());
	}

}
