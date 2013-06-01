package views.gui;

import controllers.ExportManagerController;

import javax.swing.*;

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
	public List<JEventBox> checkboxes;
	public JTextField destination;
	
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
	
	public ExportManager() {
		
		setName("Export Manager");
		setBounds(100, 100, 300, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
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
		
		checkboxes = ExportManagerController.getBoxes();
		
		for(JEventBox box : checkboxes)
			eventView.add(box);
		
		if(checkboxes.isEmpty())
			eventView.add(new JLabel("Nothing to do here"));
		
		JButton btnSelectAll = new JButton("Select all");
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(JEventBox box : checkboxes)
					box.setSelected(true);
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
		panel.add(btnSelectAll);
		panel.add(btnOK);
		panel.add(btnCancel);
		setVisible(true);
		updateWidth();
	}

	private void updateWidth() {
		int maxWidth = 300;
		for(JEventBox box : checkboxes)
			maxWidth = Math.max(maxWidth, box.getWidth());
		setSize(maxWidth+20, 300);
	}
}
