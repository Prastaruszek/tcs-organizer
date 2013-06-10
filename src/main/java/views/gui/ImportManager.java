package views.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Event;
import models.EventSet;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.ImportManagerController;

import views.gui.components.JLabelledBtn;

public class ImportManager extends JFrame {
	
	List<JLabelledBtn> events = new ArrayList<JLabelledBtn>();
	private static final long serialVersionUID = -328398734659873L;
	private static ImportManager instance = null;
	private EventManager manager;
	
	

	public static ImportManager getInstance(EventSet leftover, models.EventManager manager) {
		if(instance == null)
			instance = new ImportManager(leftover, manager);
		else {
			instance.dispose();
			instance = new ImportManager(leftover, manager);
		}
		return instance;
	}
	
	private ImportManager(final EventSet leftover, models.EventManager manager) {
		setTitle("Some events couldn't be added due to overlapping!");
		setBounds(100, 100, 400, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	//	setResizable(false);
		
		JPanel searchField = new JPanel();
		getContentPane().add(searchField, BorderLayout.NORTH);
		
		JPanel botBtns = new JPanel();
		getContentPane().add(botBtns, BorderLayout.SOUTH);
		
		JPanel eventView = new JPanel();
		JScrollPane eventPane = new JScrollPane(eventView);
		getContentPane().add(eventPane, BorderLayout.WEST);
		eventView.setLayout(new BoxLayout(eventView, BoxLayout.Y_AXIS));
		
		if(leftover.size() == 0){
			eventView.add(new JLabel("No events to organise, this shouldn't happen"));
		}
		
		for(Event ev : leftover){
			JLabelledBtn labelledbtn = new JLabelledBtn(ev);
			eventView.add(labelledbtn);
			events.add(labelledbtn);
		}
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ImportManagerController(events, manager));
		botBtns.add(btnOK);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		botBtns.add(Cancel);
		
		setVisible(true);
	}

}
