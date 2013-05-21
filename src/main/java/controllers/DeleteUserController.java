package controllers;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import models.Organizer;
import views.gui.Login;

public class DeleteUserController extends Controller {
	private Login login;
	private JComboBox<String> username;
	private JPasswordField passwd;
	
	public DeleteUserController(Login login, JComboBox<String> username, 
			JPasswordField passwd) {
		this.login = login;
		this.username = username;
		this.passwd = passwd;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Organizer organizer = Organizer.getInstance();
		boolean removed = organizer.getUsers().remove(
				(String) username.getSelectedItem(), new String(passwd.getPassword()));
		if(removed) {
			JOptionPane alert = new JOptionPane("User '" + username.getSelectedItem() + "' has been removed!",
					JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
			login.updateUserList();
			passwd.setText("");
			alert.createDialog(login, "Confirmation").setVisible(true);
		} else {
			JOptionPane alert = new JOptionPane("The password is invalid!",
					JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
			alert.createDialog(login, "ERROR").setVisible(true);
		}
	}
}
