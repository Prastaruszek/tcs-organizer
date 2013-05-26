package controllers;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.Organizer;

import views.gui.Calendar;
import views.gui.LoginManager;

public class CreateUserController extends Controller {
	private LoginManager login;
	private JTextField username;
	private JPasswordField passwd, confPasswd;
	
	public CreateUserController(LoginManager login, JTextField username, 
			JPasswordField passwd, JPasswordField confPasswd) {
		this.login = login;
		this.username = username;
		this.passwd = passwd;
		this.confPasswd = confPasswd;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Organizer organizer = Organizer.getInstance();
		if(organizer.getUsers().getByUsername(username.getText()) != null) {
			JOptionPane alert = new JOptionPane("This username has already been taken. Please choose another username.",
					JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
			alert.createDialog(login, "Error").setVisible(true);
			return;
		}
		if(!Arrays.equals(passwd.getPassword(), confPasswd.getPassword())) {
			JOptionPane alert = new JOptionPane("Password and confirmation password are not the same!",
					JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
			alert.createDialog(login, "Error").setVisible(true);
			return;
		}
		
		organizer.getUsers().initializeUser(username.getText(), new String(passwd.getPassword()));
		organizer.getUsers().setCurrentUser(organizer.getUsers().getByUsername(username.getText()));
		JOptionPane alert = new JOptionPane("User '" + username.getText() + "' has been created!",
				JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
		alert.createDialog(login, "Confirmation").setVisible(true);
		
		Calendar window = new Calendar(organizer.getUsers().getByUsername(username.getText()));
		organizer.addObserver(window);
		window.setVisibility(true);
		login.dispose();
	}
}
