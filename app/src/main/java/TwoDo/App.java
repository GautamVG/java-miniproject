package TwoDo;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import TwoDo.components.*;
import TwoDo.db.TaskData;

class App extends JFrame implements ActionListener {

    JPanel contentPane = new JPanel();
    Dashboard dashboard = new Dashboard();
    TaskManager taskManager = new TaskManager();

    public void runApp() {
        setTitle("TwoDo");
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setSize(700, 600);
        setResizable(false);

        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        dashboard.setParentActionListener(this);
        taskManager.setParentActionListener(this);

        contentPane.add(dashboard, BorderLayout.LINE_START);
        contentPane.add(taskManager, BorderLayout.CENTER);

        // Display Window
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Add":
                dashboard.openEditor();
                break;

            case "Edit":
                editBtnClicked(e);
                break;

            case "Save":
                saveBtnClicked(e);
                break;

            case "Cancel":
                dashboard.closeEditor();
                break;

            case "Delete":
                delBtnClicked(e);    
                break;

            default:
                break;
        }
    }

    public void saveBtnClicked(ActionEvent e) {
        TaskData data = dashboard.getEditorData();
        if (dashboard.isEditorDataValid()) {
            taskManager.updateTasks(data);
            dashboard.closeEditor();
        } else {
            System.out.println("Invalid Data Entered");
        }
    }

    public void editBtnClicked(ActionEvent e) {
        JButton sourceBtn = (JButton) e.getSource();
        int id = (Integer) sourceBtn.getClientProperty("id");
        TaskData sourceTaskData = taskManager.getTaskDataWithID(id);
        dashboard.openEditor(sourceTaskData);
    }

    public void delBtnClicked(ActionEvent e) {
        JButton sourceBtn = (JButton) e.getSource();
        int id = (Integer) sourceBtn.getClientProperty("id");
        taskManager.deleteTaskWithID(id);
    }

}
