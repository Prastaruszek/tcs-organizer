package views.gui;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import controllers.DatePickerController;
import java.util.GregorianCalendar;

public final class JDateFactory {
	/**
	 * @wbp.factory
	 */
	public static JTextPane JDate() {
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 9));
		textPane.setContentType("text/html");
		textPane.setText(DatePickerController.dateDisplay(GregorianCalendar.getInstance()));
		textPane.insertIcon(new ImageIcon(Calendar.SRC_MAIN_IMAGES_DATE_PICKER_ICON_GIF));
		return textPane;
	}
}