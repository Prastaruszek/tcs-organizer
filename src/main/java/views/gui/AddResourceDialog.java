package views.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddResourceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	final private JList resourcesList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddResourceDialog dialog = new AddResourceDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param jList 
	 */
	public AddResourceDialog(JList _resourcesList) {
		this.resourcesList = _resourcesList;
		setBounds(100, 100, 450, 157);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JButton btnAddFile = new JButton("Add File");
		btnAddFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(AddResourceDialog.this);
				if(result == JFileChooser.APPROVE_OPTION){
					((DefaultListModel)resourcesList.getModel()).addElement(fileChooser.getSelectedFile().toString());
					dispose();
				}
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnAddLink = new JButton("Add Link");
		btnAddLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((DefaultListModel)resourcesList.getModel()).addElement(getLinkField().getText());
				dispose();
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddLink, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
					.addGap(8))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(btnAddFile)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField)
						.addComponent(btnAddLink, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(btnAddFile)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}
	protected JTextField getLinkField() {
		return textField;
	}
}
