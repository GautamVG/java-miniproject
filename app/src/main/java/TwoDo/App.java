package TwoDo;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
// import java.awt.*;

class App extends JFrame implements ActionListener {

    JPanel contentPane = new JPanel();
    Header header = new Header();
    TaskManager taskManager = new TaskManager();

    public void runApp() {
        setTitle("TwoDo");
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setSize(768, 640);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        header.setParentActionHandler(this);
        taskManager.setParentActionHandler(this);

        contentPane.add(header);
        contentPane.add(taskManager);
        // contentPane.add(new JScrollPane(taskManager, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        // Display Window
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked");
        System.out.println(e.getActionCommand());
        String command = e.getActionCommand();
        if (command == "Add") {
            taskManager.addTask("Task #1", "07/02/2002");
        }
    }

}
