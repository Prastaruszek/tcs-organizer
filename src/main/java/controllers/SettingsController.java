package controllers;

import models.Organizer;
import views.gui.Settings;

import java.awt.event.ActionEvent;

public class SettingsController extends Controller {
	
	public SettingsController(Settings ref) {
		this.ref = ref;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Organizer.getInstance().getCurrentUser().getUserProfile().setPath(ref.getPath());
		Organizer.getInstance().getCurrentUser().getUserProfile().setVelocity(ref.getVelocity());
		Organizer.getInstance().getCurrentUser().getUserProfile().setIconPath(ref.getIconPath());
		Organizer.getInstance().update();
		Organizer.getInstance().notifyObservers();
		ref.dispose();
	}
	
	private Settings ref;
}
