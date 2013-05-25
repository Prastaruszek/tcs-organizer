package views.gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ListCellRenderer;

import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import models.Event;
import models.EventBox;
import models.EventSet;
import models.Organizer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ExportManager extends JFrame {
	
	private final JPanel panel = new JPanel();
	public List<EventBox> checkboxes;
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
		// TODO: przerobic ten chujowy layout
		
		setName("Export Manager");
		setBounds(100,100, 300, 300);
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
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		
		checkboxes = ExportManagerController.getBoxes();
		for(EventBox box : checkboxes)
			panel_2.add(box);
		
		if(checkboxes.isEmpty())
			panel_2.add(new JLabel("Nothing to do here"));
		
		JButton btnSelectAll = new JButton("Select all");
		btnSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(EventBox box : checkboxes)
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
	}

}
