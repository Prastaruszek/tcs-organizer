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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
		
		JPanel SearchField = new JPanel();
		getContentPane().add(SearchField, BorderLayout.NORTH);
		
		JPanel BotBtns = new JPanel();
		getContentPane().add(BotBtns, BorderLayout.SOUTH);
		
		JPanel EventView = new JPanel();
		getContentPane().add(EventView, BorderLayout.CENTER);
		EventView.setLayout(new BoxLayout(EventView, BoxLayout.Y_AXIS));
		 
		
		JButton btnOK = new JButton("OK");
		BotBtns.add(btnOK);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		BotBtns.add(Cancel);
		
		
	}

}
