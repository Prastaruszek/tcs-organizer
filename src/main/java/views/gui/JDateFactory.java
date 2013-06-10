package views.gui;


import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;



/**
 * Factory for simple DD.MM.YY date picking component. You also have to provide action listener
 * like for example in EventManager to make it truly pick date. Factory itself only provides basic
 * layout for text pane.
 * @wbp.factory
 * @author laiqu
 */
public final class JDateFactory {

	public static JTextPane JDate(java.util.Calendar calendar) {
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textPane.setContentType("text/html");
		if(calendar==null)
			calendar = GregorianCalendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		textPane.setText(df.format(calendar.getTime()));
		textPane.insertIcon(new ImageIcon(Calendar.SRC_MAIN_IMAGES_DATE_PICKER_ICON_GIF));
		return textPane;
	}
}