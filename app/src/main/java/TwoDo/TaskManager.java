package TwoDo;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import java.util.ArrayList;

import TwoDo.db.*;

public class TaskManager extends JPanel implements ActionListener {
    
    // Database objects
    DB db = new DB();
    ArrayList<TaskData> data = new ArrayList<TaskData>();

    // UI objects
    ArrayList<Task> tasks = new ArrayList<Task>();
    TaskEditor taskEditor = new TaskEditor();
    JLabel noTasksLabel = new JLabel("No more tasks! Yay!!");
    Dimension minimumSize = new Dimension(500, 500);

    TaskManager() {
        // Self Styles
        setPreferredSize(minimumSize);
        setAlignmentX(0);
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Children Styles
        taskEditor.setParentActionHandler(this);
        taskEditor.setVisible(false);
        add(noTasksLabel);
        add(taskEditor);

        // Initializing with Data
        data = db.getAllTasks();
        syncUIWith(data);
    }

    void syncUIWith(ArrayList<TaskData> taskData) {
        int i;
        for (i = 0; i < Math.min(tasks.size(), taskData.size()); i++) {
            // Sync existing tasks
            tasks.get(i).syncUIWith(taskData.get(i));
            tasks.get(i).setIndex(i);
        }
        if (tasks.size() < taskData.size()) {
            // more tasks need to be added
            for (int j = i; j < taskData.size(); j++) {
                Task task = new Task(taskData.get(j));
                task.setIndex(j);
                task.setParentActionHandler(this);
                add(task);
                tasks.add(task);
            }
        } else {
            // all tasks above i need to be deleted
            for (int j = i; j < taskData.size(); j++) {
                remove(tasks.get(i));
                tasks.remove(j);
            }
        }
        if (tasks.size() == 0) {
            noTasksLabel.setVisible(true);
        } else {
            noTasksLabel.setVisible(false);
        }
        revalidate();
    }

    public void openEditor() {
        taskEditor.setEmpty();
        taskEditor.setVisible(true);
    }

    public void openEditor(int targetIndex, TaskData data) {
        taskEditor.setData(targetIndex, data.title, data.desc, data.date);
        taskEditor.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String[] parts = e.getActionCommand().split(":");
        String command = parts[0];
        int targetIndex = Integer.parseInt(parts[1]);
        System.out.println(command);
        System.out.println(targetIndex);

        switch (command) {
            case "Edit":
                openEditor(targetIndex, data.get(targetIndex));
                break;

            case "Save":
                taskEditor.setVisible(false);
                break;

            case "Cancel":
                taskEditor.setVisible(false);

            case "Delete":
                break;

            default:
                break;
        }
    }

    public void setParentActionHandler(ActionListener handler) {}
}
