package views.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.EventSet;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import views.gui.components.JLabelledBtn;

public class ImportManager extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportManager frame = new ImportManager(new EventSet());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static final long serialVersionUID = -328398734659873L;
	private JTextField txtTip;

	public ImportManager(EventSet leftover) {
		
		JPanel searchField = new JPanel();
		getContentPane().add(searchField, BorderLayout.NORTH);
		
		JPanel botBtns = new JPanel();
		getContentPane().add(botBtns, BorderLayout.SOUTH);
		
		JPanel eventView = new JPanel();
		getContentPane().add(eventView, BorderLayout.CENTER);
		eventView.setLayout(new BoxLayout(eventView, BoxLayout.Y_AXIS));
		
		
		//test
		for(int i = 0; i < 5; ++i){
			eventView.add(new JLabelledBtn(new JButton("" + i), new JLabel("" + i)));
		}
		
		JButton btnOK = new JButton("OK");
		botBtns.add(btnOK);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		botBtns.add(Cancel);
		
		
	}

}
