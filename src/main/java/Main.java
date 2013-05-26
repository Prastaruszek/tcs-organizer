import models.Organizer;
import views.gui.LoginManager;

import java.awt.*;

public class Main {
    /**
     * Launch the application.
     */
    private static Organizer organizer = Organizer.getInstance();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	new LoginManager().setVisible(true);
            }
        });
    }
}
