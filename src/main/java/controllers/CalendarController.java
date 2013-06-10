package controllers;

import models.Organizer;
import views.gui.components.JEventDisplay;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Responsible for drawing and updating calendar view.
 */
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

    /**
     * Observer method. Updates events in display and sets display range.
     * @param arg0 unused
     * @param arg1 unused
     */
	@Override
	public void update(Observable arg0, Object arg1) {
		display.setEvents(Organizer.getInstance().getCurrentUser().getUserProfile().getState().getCurrentWeekEvents(),
                Organizer.getInstance().getCurrentUser().getUserProfile().getState().getFirstDay());
        display.setStartingHour(Organizer.getInstance().getCurrentUser().getUserProfile().getDisplayFirstHour());
        display.setHourNumber(
                Organizer.getInstance().getCurrentUser().getUserProfile().getDisplayLastHour()-
                        display.getStartingHour()+1);
	}

}
