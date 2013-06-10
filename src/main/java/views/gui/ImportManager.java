package views.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Event;
import models.EventSet;
import models.User;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controllers.ImportManagerController;

import views.gui.components.JLabelledBtn;

public class ImportManager extends JFrame {
	
	List<JLabelledBtn> events = new ArrayList<JLabelledBtn>();
	private static final long serialVersionUID = -328398734659873L;
	private static ImportManager instance = null;

	public static ImportManager getInstance(EventSet leftover, User currentUser) {
		if(instance == null)
			instance = new ImportManager(leftover, currentUser);
		else {
			instance.dispose();
			instance = new ImportManager(leftover, currentUser);
		}
		return instance;
	}
	
	private ImportManager(final EventSet leftover, User currentUser) {
		setTitle("Some events couldn't be added due to overlapping!");
		setBounds(100, 100, 400, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		JPanel eventPanel = new JPanel();
		getContentPane().add(eventPanel, BorderLayout.CENTER);
		eventPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel botBtns = new JPanel();
		getContentPane().add(botBtns, BorderLayout.SOUTH);
		eventPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel eventView = new JPanel();
		JScrollPane eventPane = new JScrollPane(eventView);
		eventPanel.add(eventPane);

		eventView.setLayout(new BoxLayout(eventView, BoxLayout.Y_AXIS));
		
		JPanel searchPanel = new JPanel();
		eventPanel.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		
		if(leftover == null || leftover.size() == 0){
			eventView.add(new JLabel("No events to organise, this shouldn't happen"));
		}
		else{
			Event[] evlist = leftover.getSortedArray();
		
			for(Event ev : evlist){
				JLabelledBtn labelledbtn = new JLabelledBtn(ev, currentUser);
				eventView.add(labelledbtn);
				events.add(labelledbtn);
			}
		}
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ImportManagerController(events, currentUser));
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
		updateWidth();
	}

	private void updateWidth() {
		int maxWidth = 400;
		for(JLabelledBtn lbtn : events)
			maxWidth = Math.max(maxWidth, lbtn.getWidth());
		setSize(maxWidth+20, 400);
	}
}
