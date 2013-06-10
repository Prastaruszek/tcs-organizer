package controllers;

import java.util.List;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import views.gui.ExportManager;
import views.gui.components.JEventBox;

/** Filters events in export manager.
 * Shows events checkboxes which match a given string, hides the others.
 */
public class EventFilterController implements DocumentListener {
	
	private ExportManager exportManager;
	
	/**
	 * Constructs a new controller associated with a given export manager window.
	 * @param exportManager the associated export manager window.
	 */
	public EventFilterController(ExportManager exportManager) {
		this.exportManager = exportManager;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {}

	@Override
	public void insertUpdate(DocumentEvent e) {
		handleInput();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		handleInput();
	}

	private void handleInput() {
		String text = exportManager.getSearchField().getText();
		List<JEventBox> checkboxes = exportManager.getEventBoxes();
		for(JEventBox box : checkboxes)
			if(box.getText().toLowerCase().contains(text.toLowerCase()))
				box.setVisible(true);
			else
				box.setVisible(false);
	}
	
}
