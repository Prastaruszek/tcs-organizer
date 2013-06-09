package views.gui;

import controllers.EventFilterController;
import controllers.ExportManagerController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import views.gui.components.JEventBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ExportManager extends JFrame {
	
	private static final long serialVersionUID = -7741300109933336770L;
	private final JPanel panel = new JPanel();
	private List<JEventBox> checkboxes;
	private JTextField destination;
	private JTextField searchField;
	private static ExportManager instance = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExportManager frame = new ExportManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static ExportManager getInstance() {
		if(instance == null)
			instance = new ExportManager();
		else {
			instance.dispose();
			instance = new ExportManager();
		}
		return instance;
	}
	
	private ExportManager() {
		
		setTitle("Export your events to a file so that others can see how miserable is your life");
		setBounds(100, 100, 400, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		destination = new JTextField();
		destination.setText("Pick a destination");
		
		destination.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser file = new JFileChooser();
				file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(file.showDialog(ExportManager.this, "Choose") == JFileChooser.APPROVE_OPTION) {
					destination.setText(file.getSelectedFile().getAbsolutePath());
				}
			}
		});
		destination.setEditable(false);
		
		panel_1.add(destination, BorderLayout.SOUTH);
		destination.setColumns(10);
		
		JPanel eventView = new JPanel();
		JScrollPane eventPanel = new JScrollPane(eventView);
		eventView.setLayout(new BoxLayout(eventView, BoxLayout.Y_AXIS));
		panel_1.add(eventPanel, BorderLayout.CENTER);
		
		JPanel searchPanel = new JPanel();
		panel_1.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		
		JLabel lblSearch = new JLabel("Search:");
		searchPanel.add(lblSearch);
		
		searchField = new JTextField();
		searchPanel.add(searchField);
		searchField.setColumns(10);
		searchField.getDocument().addDocumentListener(new EventFilterController(this));
		
		checkboxes = ExportManagerController.getBoxes();
		
		for(JEventBox box : checkboxes)
			eventView.add(box);
		
		if(checkboxes.isEmpty())
			eventView.add(new JLabel("You have no events to export!"));
		
		JButton btnSelectAll = new JButton("Select all");
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(JEventBox box : checkboxes)
					if(box.isVisible())
						box.setSelected(true);
				searchField.requestFocusInWindow();
			}
		});
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ExportManagerController(this));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JButton btnDeselectAll = new JButton("Deselect all");
		btnDeselectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(JEventBox box : checkboxes)
					if(box.isVisible())
						box.setSelected(false);
				searchField.requestFocusInWindow();
			}
		});
		
		panel.add(btnSelectAll);
		panel.add(btnDeselectAll);
		panel.add(btnOK);
		panel.add(btnCancel);
		setVisible(true);
		updateWidth();
	}
	
	private void updateWidth() {
		int maxWidth = 400;
		for(JEventBox box : checkboxes)
			maxWidth = Math.max(maxWidth, box.getWidth());
		setSize(maxWidth+20, 400);
	}
	
	public JTextField getDestination() {
		return destination;
	}
	
	public JTextField getSearchField() {
		return searchField;
	}
	
	public List<JEventBox> getEventBoxes() {
		return checkboxes;
	}
}
