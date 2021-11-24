package TwoDo.components;

import javax.swing.*;
import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
import java.awt.*;

import java.util.ArrayList;

import TwoDo.db.*;

public class TaskManager extends JScrollPane {
    
    // Event Handler
    ActionListener parentActionListener;

    // Database Instance
    DB db = new DB();

    // UI objects
    JPanel contentPane = new JPanel();
    ArrayList<Task> tasks = new ArrayList<Task>();
    Dimension minimumSize = new Dimension(600, 600);

    public TaskManager() {
        setViewportView(contentPane);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setPreferredSize(minimumSize);
        setAlignmentX(0);
        setBorder(BorderFactory.createEmptyBorder());

        contentPane.setLayout(new GridLayout(0, 2, 20, 20));
        contentPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        syncUI();
    }

    public void setParentActionListener(ActionListener listener) {
        parentActionListener = listener;
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setParentActionListener(listener);
        }
    }

    public void syncUI() {
        syncUIWith(db.getAllTasks());
    }

    void syncUIWith(ArrayList<TaskData> taskData) {
        int i = 0;
        for (; i < Math.min(tasks.size(), taskData.size()); i++) {
            // Sync existing tasks
            tasks.get(i).syncUIWith(taskData.get(i));
        }
        if (tasks.size() < taskData.size()) {
            // more tasks need to be added
            for (int j = i; j < taskData.size(); j++) {
                Task task = new Task(taskData.get(j));
                task.setParentActionListener(parentActionListener);
                contentPane.add(task);
                tasks.add(task);
            }
        } else {
            // all tasks above i need to be deleted
            for (int j = i; j < tasks.size(); j++) {
                contentPane.remove(tasks.get(j));
                tasks.remove(j);
            }
        }
        revalidate();
        repaint();
    }

    public TaskData getTaskDataWithID(int id) {
        TaskData result = null;
        for (int i = 0; i < tasks.size(); i++)  {
            if (tasks.get(i).getKey() == id) {
                result = tasks.get(i).getData();
                break;
            }
        }
        return result;
    }

    public void updateTasks(TaskData data) {
        // Database Operation
        if (data.id == -1) {
            db.insertNewTask(data);
        } else {
            db.updateTask(data);
        }
        syncUI();
    }

    public void deleteTaskWithID(int id) {
        // Database Operation
        db.deleteTask(id);
        syncUI();
    }
}
