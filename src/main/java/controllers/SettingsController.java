package controllers;

import java.awt.event.ActionEvent;

import models.Organizer;

import views.gui.Settings;

public class SettingsController extends Controller {
	
	public SettingsController(Settings ref) {
		this.ref = ref;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Organizer.getInstance().getCurrentUser().getUserProfile().setPath(ref.getPath());
		ref.dispose();
	}
	
	private Settings ref;
}
