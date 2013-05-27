package views.gui;

import controllers.DatePickerController;

import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

public final class JDateFactory {
	/**
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
		textPane.setText(DatePickerController.dateDisplay(calendar));
		textPane.insertIcon(new ImageIcon(Calendar.SRC_MAIN_IMAGES_DATE_PICKER_ICON_GIF));
		return textPane;
	}
}