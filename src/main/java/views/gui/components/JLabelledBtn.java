package views.gui.components;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JLabelledBtn extends JPanel{
	private JButton button;
	private JLabel label;
	
	public JLabelledBtn(JButton button, JLabel label){
		this.button = button;
		this.label = label;
		add(button);
		add(label);
	}
	
}
