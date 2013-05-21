import models.*;
import views.gui.Calendar;
import views.gui.Login;

import java.awt.*;

import controllers.Callback;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 14.05.13
 * Time: 14:35
 */
public class Main {
    /**
     * Launch the application.
     */
    private static Organizer organizer = Organizer.getInstance();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new Login(new Callback() {
					@Override
					public void call(Object... args) {
						Calendar window = new Calendar();
		                organizer.addObserver(window);
		                window.setVisibility(true);
					}
            	}).setVisible(true);
            }
        });
    }
}
