package TwoDo;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App app = new App();
                app.runApp();
            }
        });
        // App app = new App();
        // app.runApp();
    }
}