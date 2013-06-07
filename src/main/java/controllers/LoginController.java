package controllers;

import models.Organizer;
import models.User;
import views.gui.Calendar;
import views.gui.LoginManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginController extends Controller {
	private LoginManager login;
	private JComboBox<String> username;
	private JPasswordField passwd;
	
	public LoginController(LoginManager login, JComboBox<String> username, 
			JPasswordField passwd) {
		this.login = login;
		this.username = username;
		this.passwd = passwd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Organizer organizer = Organizer.getInstance();
		User u = organizer.getUsers().validateUser(
				(String) username.getSelectedItem(), passwd.getPassword());
		if(u != null) {
			organizer.getUsers().setCurrentUser(u);
			Calendar window = new Calendar(u);
			organizer.addObserver(window);
			window.setVisibility(true);
			login.dispose();
		} else {
			JOptionPane alert = new JOptionPane("The password is invalid!",
					JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
			alert.createDialog(login, "Error").setVisible(true);
		}
	}
}
