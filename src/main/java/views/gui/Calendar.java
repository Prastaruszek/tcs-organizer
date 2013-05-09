package views.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.BoxLayout;

import views.gui.components.JEventDisplay;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class Calendar {

	private JFrame frame;
	private JEventDisplay eventDisplay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calendar window = new Calendar();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calendar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		eventDisplay = new JEventDisplay();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(eventDisplay, GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
							.addGap(11))
						.addComponent(eventDisplay, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)))
		);
		
		JButton btnAddEvent = new JButton("Add Event");
		
		JButton btnSettings = new JButton("Settings");
		
		JButton btnImport = new JButton("Import");
		
		JButton btnExport = new JButton("Export");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(btnAddEvent, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
				.addComponent(btnSettings, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
				.addComponent(btnExport, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
				.addComponent(btnImport, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnAddEvent)
					.addGap(18)
					.addComponent(btnSettings)
					.addGap(18)
					.addComponent(btnImport)
					.addGap(18)
					.addComponent(btnExport)
					.addGap(158))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblVelocity = new JLabel("Velocity: 9000");
		lblVelocity.setFont(new Font("Dialog", Font.BOLD, 20));
		panel_2.add(lblVelocity);
		
		JButton btnPrevious = new JButton("Previous");
		panel.add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		panel.add(btnNext);
		
		JTextPane txtpnThereWillBe = new JTextPane();
		txtpnThereWillBe.setText("There will be date");
		panel.add(txtpnThereWillBe);
		frame.getContentPane().setLayout(groupLayout);
	}
}
