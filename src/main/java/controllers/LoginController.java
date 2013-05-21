package controllers;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPasswordField;

import models.Organizer;
import models.User;
import views.gui.Login;

public class LoginController extends Controller {
	private Login login;
	private JComboBox<String> username;
	private JPasswordField passwd;
	private JDialog dialog;
	private Callback cb;
	
	public LoginController(Login login, JComboBox<String> username, 
			JPasswordField passwd, JDialog dialog, Callback cb) {
		this.login = login;
		this.username = username;
		this.passwd = passwd;
		this.dialog = dialog;
		this.cb = cb;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Organizer organizer = Organizer.getInstance();
		User u = organizer.getUsers().validateUser(
				(String) username.getSelectedItem(), new String(passwd.getPassword()));
		if(u != null) {
			if(cb != null)
				cb.call();
			organizer.update();
			organizer.notifyObservers(u.getUserProfile().getState());
			login.dispose();
		} else {
			dialog.setVisible(true);
		}
	}
}
