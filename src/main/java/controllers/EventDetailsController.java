package controllers;

import models.Organizer;
import views.gui.EventDetails;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventDetailsController extends Controller {
	private EventDetails frame;
    private RemoveListener removeListener = null;
    private RemoveGroupListener removeGroupListener = null;
	public EventDetailsController(EventDetails frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
        throw new RuntimeException("This should never happen. Please report bug on github.");
	}

    /**
     * Returns ActionListener for remove button.
     * @return RemoveListener
     */
    public RemoveListener getRemoveListener() {
        if(removeListener == null)
            removeListener = new RemoveListener();
        return removeListener;
    }

    /**
     * Returns ActionListener for removing group button (it displays only if event is in group).
     * @return RemoveGroupListener
     */
    public RemoveGroupListener getRemoveGroupListener() {
        if(removeGroupListener == null)
            removeGroupListener = new RemoveGroupListener();
        return removeGroupListener;
    }

    /**
     * ActionListener for remove button.
     */
    private class RemoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JOptionPane alert = new JOptionPane("Are you sure you want to remove this event?",
                    JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
            alert.createDialog(frame, "").setVisible(true);
            if(alert.getValue().equals(1))
                return;
            frame.getEvent().delete();
            Organizer.getInstance().update();
            Organizer.getInstance().notifyObservers(Organizer.getInstance().getCurrentUser().getUserProfile().getState());
            frame.dispose();
        }
    }

    /**
     * ActionListener for removing group button (it displays only if event is in group).
     */
    private class RemoveGroupListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JOptionPane alert = new JOptionPane("Are you sure you want to remove this event group?",
                    JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
            alert.createDialog(frame, "").setVisible(true);
            if(alert.getValue().equals(1))
                return;
            frame.getEvent().getParent().delete();
            Organizer.getInstance().update();
            Organizer.getInstance().notifyObservers(Organizer.getInstance().getCurrentUser().getUserProfile().getState());
            frame.dispose();
        }
    }
}
