import models.*;
import views.gui.Login;

import java.awt.*;

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
            	new Login().setVisible(true);
            }
        });
    }
}
