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
	
	public LoginController(Login login, JComboBox<String> username, 
			JPasswordField passwd, JDialog dialog) {
		this.login = login;
		this.username = username;
		this.passwd = passwd;
		this.dialog = dialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		User u = Organizer.getInstance().getUsers().validateUser(
				(String) username.getSelectedItem(), new String(passwd.getPassword()));
		if(u != null) {
			Organizer.getInstance().notifyObservers(u);
			login.dispose();
		} else {
			dialog.setVisible(true);
		}
	}
}
