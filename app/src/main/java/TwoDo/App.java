package TwoDo;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
// import java.awt.*;

class App extends JFrame implements ActionListener {

    JPanel contentPane = new JPanel();
    Header header = new Header();
    TaskManager taskManager = new TaskManager();
    // JScrollPane scrollPane = new JScrollPane(taskManager, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public void runApp() {
        setTitle("TwoDo");
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(768, 640);
        setResizable(false);

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // scrollPane.setPreferredSize(new Dimension(500, 500));

        header.setParentActionHandler(this);

        contentPane.add(header);
        contentPane.add(taskManager);
        // contentPane.add(new JScrollPane(taskManager, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        // contentPane.add(scrollPane);

        // Display Window
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "Add") {
            taskManager.openEditor();
            // scrollPane.revalidate();
        }
    }

}
