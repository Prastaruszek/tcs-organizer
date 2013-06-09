package controllers;

import models.Organizer;
import models.Task;
import views.gui.TaskDetails;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskDetailsController extends Controller {
	private TaskDetails frame;
    private RemoveListener removeListener = null;
	public TaskDetailsController(TaskDetails frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
        throw new RuntimeException("This should never happen. Please report bug on github.");
	}

    public RemoveListener getRemoveListener() {
        if(removeListener == null)
            removeListener = new RemoveListener();
        return removeListener;
    }


    private class RemoveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JOptionPane alert = new JOptionPane("Are you sure you want to remove this task?",
                    JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
            alert.createDialog(frame, "").setVisible(true);
            if(alert.getValue().equals(1))
                return;
            frame.getEvent().getTasks().remove(frame.getTask());
            ((DefaultListModel<Task>)frame.getTaskJList().getModel()).removeElement(frame.getTask());
            Organizer.getInstance().update();
            Organizer.getInstance().notifyObservers(Organizer.getInstance().getCurrentUser().getUserProfile().getState());
            frame.dispose();
        }
    }
}
