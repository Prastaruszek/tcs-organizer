package views.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import controllers.AddEventController;
import controllers.DatePickerController;
import models.Organizer;

public class AddEvent extends JFrame {

	private static final long serialVersionUID = -1113743261857828270L;
	
	private JPanel contentPane;
	private JTextField txtfldTitle;
	private JTextArea txtrComment;
	private java.util.Calendar endCalendar;
	private java.util.Calendar startCalendar;
    private java.util.Calendar dateUntilCalendar;
	private JComboBox startTimeBox;
	private JComboBox endTimeBox;
	
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
        endCalendar = Organizer.getInstance().getCurrentUser().getUserProfile().getState().getFirstDay();
        startCalendar = Organizer.getInstance().getCurrentUser().getUserProfile().getState().getFirstDay();
        dateUntilCalendar = Organizer.getInstance().getCurrentUser().getUserProfile().getState().getFirstDay();
        setResizable(false);
		setBounds(100, 100, 397, 529);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("Title: ");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtfldTitle = new JTextField();
		txtfldTitle.setColumns(10);
		
		JLabel lblComment = new JLabel("Comment:");
		lblComment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanel panel = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JLabel lblImportance = new JLabel("Importance:");
		lblImportance.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
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
					.addComponent(lblImportance)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
				.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
				.addComponent(txtrComment, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblComment, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(295, Short.MAX_VALUE))
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImportance)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new AddEventController(this));
        btnAddEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
		
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
		lblRepeatUntil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JCheckBox chckbxMonday = new JCheckBox("Mon");
		
		JCheckBox chckbxTuesday = new JCheckBox("Tue");
		
		JCheckBox chckbxWed = new JCheckBox("Wed");
		
		JCheckBox chckbxTh = new JCheckBox("Thu");
		
		JCheckBox chckbxFri = new JCheckBox("Fri");
		
		JCheckBox chckbxSat = new JCheckBox("Sat");
		
		JCheckBox chckbxSun = new JCheckBox("Sun");
		
		final JTextPane repeatUntilDate = JDateFactory.JDate(dateUntilCalendar);
		repeatUntilDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new DatePicker(new DatePickerController() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dateUntilCalendar = getGregorianCalendar();
						repeatUntilDate.setText(dateDisplay(endCalendar));
						repeatUntilDate.insertIcon(new ImageIcon(Calendar.SRC_MAIN_IMAGES_DATE_PICKER_ICON_GIF));
						super.actionPerformed(e);
					}
				}).setVisible(true);
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblRepeatUntil)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(repeatUntilDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblRepeatUntil)
						.addComponent(repeatUntilDate, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxMonday)
						.addComponent(chckbxTuesday)
						.addComponent(chckbxWed)
						.addComponent(chckbxTh)
						.addComponent(chckbxFri)
						.addComponent(chckbxSat)
						.addComponent(chckbxSun))
					.addContainerGap(22, Short.MAX_VALUE))
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
		lblEnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		final JTextPane endDatePicker = JDateFactory.JDate(endCalendar);
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
		
		endTimeBox = new JComboBox();
		endTimeBox.setModel(new DefaultComboBoxModel(new String[] {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"}));
		endTimeBox.setSelectedIndex(17);
		endTimeBox.setEditable(true);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnd)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(endTimeBox, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblEnd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(endTimeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(endDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblStart = new JLabel("Start:");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		final JTextPane startDatePicker = JDateFactory.JDate(startCalendar);
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
		
		startTimeBox = new JComboBox();
		startTimeBox.setEditable(true);
		startTimeBox.setModel(new DefaultComboBoxModel(new String[] {"00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"}));
		startTimeBox.setSelectedIndex(16);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblStart)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(startDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(startTimeBox, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblStart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(startTimeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(startDatePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	public String getEventTitle() {
		return txtfldTitle.getText();
	}
	public String getEventComment() {
		return txtrComment.getText();
	}
	private boolean checkIsTimeIsValid(String time){
		if(time.length()==5&&time.charAt(2)==':'){
			String hour = time.substring(0,2);
			String minutes = time.substring(3,5);
			int h,m;
			try{
				h = Integer.parseInt(hour);
				m = Integer.parseInt(minutes);	
			} catch(NumberFormatException exception){
				return false;
			}
			if(h>=0&&h<=24&&m>=0&&m<=59)
				return true;
		}
		return false;
	}
	public java.util.Calendar getStartCalendar() {
		String time = (String) getStartTimeBox().getSelectedItem();
		if(checkIsTimeIsValid(time)){
			int h = Integer.parseInt(time.substring(0,2));
			int m = Integer.parseInt(time.substring(3,5));
			startCalendar.set(java.util.Calendar.HOUR_OF_DAY, h);
			startCalendar.set(java.util.Calendar.MINUTE, m);
		}
		return (java.util.Calendar) startCalendar.clone();
	}
	public java.util.Calendar getEndCalendar() {
		String time = (String) getEndTimeBox().getSelectedItem();
		if(checkIsTimeIsValid(time)){
			int h = Integer.parseInt(time.substring(0,2));
			int m = Integer.parseInt(time.substring(3,5));
			endCalendar.set(java.util.Calendar.HOUR_OF_DAY, h);
			endCalendar.set(java.util.Calendar.MINUTE, m);
		}
		return (java.util.Calendar) endCalendar.clone();
	}
	protected JComboBox getStartTimeBox() {
		return startTimeBox;
	}
	protected JComboBox getEndTimeBox() {
		return endTimeBox;
	}
}
