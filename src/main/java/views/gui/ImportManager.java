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

/**
 * Creates a window for import functionality.
 */
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
	
	private ImportManager(final EventSet leftover, final User currentUser) {
		setTitle("These events couldn't be added due to overlapping!");
		setBounds(100, 100, 400, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		topPanel.add(new JLabel("Imported events, that are overlapping existing ones in your schedule:"));
		topPanel.add(new JLabel("(editions made to events don't visualise, unless you refresh the window)"));
		
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
			Event[] evList;
			evList = leftover.getSortedArray();
			
			for(Event ev : evList){
				JLabelledBtn labelledbtn = new JLabelledBtn(ev, currentUser);
				eventView.add(labelledbtn);
				events.add(labelledbtn);
			}
		}
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ImportManagerController(events, currentUser));
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventSet eS = new EventSet();
				for(JLabelledBtn btn : events){
					eS.add(btn.getEvent());
				}
				getInstance(eS, currentUser);
			}
		});
		
		JButton btnCancel = new JButton("Abort these events");
		btnCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		botBtns.add(btnRefresh);
		botBtns.add(btnOK);
		botBtns.add(btnCancel);
		
		setVisible(true);
		updateWidth();
	}

	private void updateWidth() {
		int maxWidth = 400;
		for(JLabelledBtn lbtn : events)
			maxWidth = Math.max(maxWidth, lbtn.getWidth());
		setSize(maxWidth+25, 400);
	}
}
