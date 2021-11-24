package TwoDo;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
// import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import com.formdev.flatlaf.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    // UIManager.setLookAndFeel(new FlatLightLaf());
                    // UIManager.setLookAndFeel(new NimbusLookAndFeel());
                } catch (Exception err) {
                    System.out.println(err);
                }
                App app = new App();
                app.runApp();
            }
        });
        // App app = new App();
        // app.runApp();
    }
}
