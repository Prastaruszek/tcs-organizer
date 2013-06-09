package views.gui;

import utils.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

public final class JDateFactory {
	/**
     * Factory for simple DD.MM.YY date picking component. You also have to provide action listener
     * like for example in EventManager to make it truly pick date. Factory itself only provides basic
     * layout for text pane.
	 * @wbp.factory
	 */
	public static JTextPane JDate(java.util.Calendar calendar) {
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textPane.setContentType("text/html");
		if(calendar==null)
			calendar = GregorianCalendar.getInstance();
		textPane.setText(DateUtils.dateDisplay(calendar));
		textPane.insertIcon(new ImageIcon(Calendar.SRC_MAIN_IMAGES_DATE_PICKER_ICON_GIF));
		return textPane;
	}
}