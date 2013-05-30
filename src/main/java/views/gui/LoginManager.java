package views.gui;

import controllers.CreateUserController;
import controllers.DeleteUserController;
import controllers.LoginController;
import models.Organizer;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginManager extends JFrame {

	private static final long serialVersionUID = -370406313299117919L;
	private JTabbedPane contentPane;
	private JTextField username;
	private JPasswordField pwdPasswd, pwdPasswd1, pwdConfPasswd1, pwdPasswd2;
	private JComboBox<String> userList, userList2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginManager frame = new LoginManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginManager() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 250);
		setResizable(false);
		setTitle("User manager");
		contentPane = new JTabbedPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel loginCard = new JPanel();
		contentPane.add("Login", loginCard);
		
		JPanel btnPanel = new JPanel();
		
		JLabel lblUser = new JLabel("User");
		
		userList = new JComboBox<>(Organizer.getInstance().getUsers().getUsernames());
		
		JLabel lblPassword = new JLabel("Password");
		
		userList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pwdPasswd.requestFocusInWindow();
			}
		});
		
		pwdPasswd = new JPasswordField();
		pwdPasswd.addActionListener(new LoginController(this, userList, pwdPasswd));
		
		GroupLayout gl_loginCard = new GroupLayout(loginCard);
		gl_loginCard.setHorizontalGroup(
			gl_loginCard.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginCard.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_loginCard.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUser)
						.addComponent(lblPassword))
					.addGap(21)
					.addGroup(gl_loginCard.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pwdPasswd)
						.addComponent(userList, 0, 166, Short.MAX_VALUE))
					.addContainerGap(116, Short.MAX_VALUE))
				.addComponent(btnPanel, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
		);
		gl_loginCard.setVerticalGroup(
			gl_loginCard.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginCard.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_loginCard.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUser)
						.addComponent(userList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_loginCard.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(pwdPasswd, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
					.addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new LoginController(this, userList, pwdPasswd));
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnPanel.add(btnLogIn);
		btnPanel.add(btnClose);
		
		loginCard.setLayout(gl_loginCard);
		
		//loginCard end---------------------------------------------------------------------
		
		JPanel createUserCard = new JPanel();
		contentPane.add("New user", createUserCard);
		
		JPanel btnPanel1 = new JPanel();
		
		JLabel lblUser1 = new JLabel("User");
		
		username = new JTextField();
		
		JLabel lblPassword1 = new JLabel("Password");
		
		pwdPasswd1 = new JPasswordField();
		pwdConfPasswd1 = new JPasswordField();
		pwdConfPasswd1.addActionListener(new CreateUserController(this, username, pwdPasswd1, pwdConfPasswd1));
		
		JLabel lblConfirm1 = new JLabel("Confirm");
		
		GroupLayout gl_createUserCard = new GroupLayout(createUserCard);
		gl_createUserCard.setHorizontalGroup(
			gl_createUserCard.createParallelGroup(Alignment.LEADING)
				.addComponent(btnPanel1, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
				.addGroup(gl_createUserCard.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_createUserCard.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUser1)
						.addComponent(lblPassword1)
						.addComponent(lblConfirm1))
					.addGap(21)
					.addGroup(gl_createUserCard.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pwdConfPasswd1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_createUserCard.createParallelGroup(Alignment.LEADING, false)
							.addComponent(pwdPasswd1, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
							.addComponent(username, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(121, Short.MAX_VALUE))
		);
		gl_createUserCard.setVerticalGroup(
			gl_createUserCard.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_createUserCard.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_createUserCard.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUser1)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_createUserCard.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword1)
						.addComponent(pwdPasswd1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_createUserCard.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirm1)
						.addComponent(pwdConfPasswd1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(btnPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new CreateUserController(this, username, pwdPasswd1, pwdConfPasswd1));
		
		JButton btnClose1 = new JButton("Close");
		btnClose1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnPanel1.add(btnCreate);
		btnPanel1.add(btnClose1);
		
		createUserCard.setLayout(gl_createUserCard);
		
		//createUserCard end---------------------------------------------------------------------
		
		JPanel deleteUserCard = new JPanel();
		contentPane.add("Delete user", deleteUserCard);
		
		JPanel btnPanel2 = new JPanel();
		
		JLabel lblUser2 = new JLabel("User");
		
		userList2 = new JComboBox<>(Organizer.getInstance().getUsers().getUsernames());
		
		JLabel lblPassword2 = new JLabel("Password");
		
		userList2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pwdPasswd2.requestFocusInWindow();
			}
		});
		
		pwdPasswd2 = new JPasswordField();
		pwdPasswd2.addActionListener(new DeleteUserController(this, userList2, pwdPasswd2));
		
		GroupLayout gl_deleteUserCard = new GroupLayout(deleteUserCard);
		gl_deleteUserCard.setHorizontalGroup(
			gl_deleteUserCard.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_deleteUserCard.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_deleteUserCard.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUser2)
						.addComponent(lblPassword2))
					.addGap(21)
					.addGroup(gl_deleteUserCard.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pwdPasswd2)
						.addComponent(userList2, 0, 166, Short.MAX_VALUE))
					.addContainerGap(116, Short.MAX_VALUE))
				.addComponent(btnPanel2, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
		);
		gl_deleteUserCard.setVerticalGroup(
			gl_deleteUserCard.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_deleteUserCard.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_deleteUserCard.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUser2)
						.addComponent(userList2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_deleteUserCard.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword2)
						.addComponent(pwdPasswd2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addComponent(btnPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new DeleteUserController(this, userList2, pwdPasswd2));
		
		JButton btnClose2 = new JButton("Close");
		btnClose2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnPanel2.add(btnDelete);
		btnPanel2.add(btnClose2);
		
		deleteUserCard.setLayout(gl_deleteUserCard);
		
		//deleteUserCard end---------------------------------------------------------------------
		
		contentPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				pwdPasswd.requestFocusInWindow();
				username.requestFocusInWindow();
				pwdPasswd2.requestFocusInWindow();
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
	        public void windowClosing(WindowEvent e) {
	        	Organizer.getInstance().saveToFile();
	        }
	        
			@Override
	        public void windowClosed(WindowEvent e) {
				Organizer.getInstance().saveToFile();
			}
	    });
		
		setVisible(true);
		pwdPasswd.requestFocusInWindow();
	}
	
	public void updateUserList() {
		userList.setModel(new DefaultComboBoxModel<>(
				Organizer.getInstance().getUsers().getUsernames()));
		userList2.setModel(new DefaultComboBoxModel<>(
				Organizer.getInstance().getUsers().getUsernames()));
	}
}
