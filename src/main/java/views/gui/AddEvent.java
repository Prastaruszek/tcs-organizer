package views.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;

import controllers.DatePickerController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

public class AddEvent extends JFrame {

	private JPanel contentPane;
	private JTextField txtfldTitle;
	private JTextArea txtrComment;
	private GregorianCalendar endCalendar;
	public GregorianCalendar getStartCalendar() {
		return startCalendar;
	}
	private GregorianCalendar startCalendar;
	public GregorianCalendar getEndCalendar() {
		return endCalendar;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEvent frame = new AddEvent();
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
	public AddEvent() {
		setResizable(false);
		setBounds(100, 100, 397, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Title: ");
		
		txtfldTitle = new JTextField();
		txtfldTitle.setColumns(10);
		
		JLabel lblComment = new JLabel("Comment:");
		
		JPanel panel = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JLabel lblImportance = new JLabel("Importance:");
		
		JComboBox comboBox = new JComboBox();
		
		JPanel panel_4 = new JPanel();
		
		JPanel panel_5 = new JPanel();
		
		txtrComment = new JTextArea();
		txtrComment.setLineWrap(true);
		txtrComment.setFont(new Font("Monospaced", Font.PLAIN, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtfldTitle, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblComment, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblImportance)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
				.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
				.addComponent(txtrComment, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(txtfldTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblComment)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrComment, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImportance)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JButton btnAddEvent = new JButton("Add Event");
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
					.addComponent(btnAddEvent)
					.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
					.addComponent(btnClose))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClose)
						.addComponent(btnAddEvent))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_5.setLayout(gl_panel_5);
		
		JButton btnAddResource = new JButton("Add resource");
		
		JButton btnRemoveresource = new JButton("Remove resource");
		
		JList list = new JList();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(btnAddResource)
					.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
					.addComponent(btnRemoveresource))
				.addComponent(list, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddResource)
						.addComponent(btnRemoveresource))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblRepeatUntil = new JLabel("Repeat until:");
		
		JLabel lblDaymthyer = new JLabel("daymthyer");
		
		JCheckBox chckbxMonday = new JCheckBox("Mon");
		
		JCheckBox chckbxTuesday = new JCheckBox("Tue");
		
		JCheckBox chckbxWed = new JCheckBox("Wed");
		
		JCheckBox chckbxTh = new JCheckBox("Thu");
		
		JCheckBox chckbxFri = new JCheckBox("Fri");
		
		JCheckBox chckbxSat = new JCheckBox("Sat");
		
		JCheckBox chckbxSun = new JCheckBox("Sun");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblRepeatUntil)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDaymthyer))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(chckbxMonday)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxTuesday)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxWed)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxTh)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxFri)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxSat)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxSun)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepeatUntil)
						.addComponent(lblDaymthyer))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxMonday)
						.addComponent(chckbxTuesday)
						.addComponent(chckbxWed)
						.addComponent(chckbxTh)
						.addComponent(chckbxFri)
						.addComponent(chckbxSat)
						.addComponent(chckbxSun))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
		);
		
		JLabel lblEnd = new JLabel("End:");
		
		final JTextPane endDatePicker = JDateFactory.JDate();
		endDatePicker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new DatePicker(new DatePickerController() {

					@Override
					public void actionPerformed(ActionEvent e) {
						endCalendar = getGregorianCalendar();
						endDatePicker.setText(dateDisplay(endCalendar));
						endDatePicker.insertIcon(new ImageIcon(Calendar.SRC_MAIN_IMAGES_DATE_PICKER_ICON_GIF));
						super.actionPerformed(e);
					}
				}).setVisible(true);
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnd)
						.addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(123, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblEnd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblStart = new JLabel("Start:");
		
		final JTextPane startDatePicker = JDateFactory.JDate();
		startDatePicker.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new DatePicker(new DatePickerController() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						startCalendar = getGregorianCalendar();
						startDatePicker.setText(dateDisplay(startCalendar));
						startDatePicker.insertIcon(new ImageIcon(Calendar.SRC_MAIN_IMAGES_DATE_PICKER_ICON_GIF));
						super.actionPerformed(e);
					}
				}).setVisible(true);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStart)
						.addComponent(startDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblStart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(startDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	public String getEventTitle() {
		return txtfldTitle.getText();
	}
	public String getEventComment() {
		return txtrComment.getText();
	}
}
