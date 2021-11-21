package TwoDo;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import java.util.ArrayList;

// import Records.*;

public class TaskManager extends JPanel implements ActionListener {
    
    // JScrollPane scrollPane = new JScrollPane(this);

    // TaskRecord taskRecords[];
    // Task tasks[];
    Dimension minimumSize = new Dimension(500, 500);
    ArrayList<Task> tasks = new ArrayList<Task>();

    TaskManager() {
        setPreferredSize(minimumSize);
        setAlignmentX(0);
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        // setBorder(BorderFactory.createTitledBorder("Tasks"));

        // add(scrollPane);
    }

    public void setParentActionHandler(ActionListener handler) {

    }

    public void addTask(String title, String desc) {
        Task task = new Task(title, desc);
        task.setParentActionHandler(this);
        add(task);
        tasks.add(task);
        revalidate();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked inside a task");
        System.out.println(e.getActionCommand());
    }
}
