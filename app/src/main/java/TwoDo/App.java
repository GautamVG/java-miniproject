package TwoDo;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import TwoDo.components.*;
import TwoDo.db.TaskData;

class App extends JFrame implements ActionListener {

    private JPanel contentPane = new JPanel();
    private Dashboard dashboard = new Dashboard();
    private TaskManager taskManager = new TaskManager();

    public void runApp() {
        setTitle("TwoDo");
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

            case "Refresh":
                taskManager.syncUI();
                break;

            default:
                break;
        }
    }

    private void saveBtnClicked(ActionEvent e) {
        if (dashboard.validateEditor()) {
            TaskData data = dashboard.getEditorData();
            taskManager.update(data);
            dashboard.closeEditor();
        } else {
            JOptionPane.showMessageDialog(this, "There is a problem with your form");
        }
    }

    private void editBtnClicked(ActionEvent e) {
        JButton sourceBtn = (JButton) e.getSource();
        int id = (Integer) sourceBtn.getClientProperty("id");
        TaskData sourceTaskData = taskManager.getTaskDataWithID(id);
        dashboard.openEditor(sourceTaskData);
    }

    private void delBtnClicked(ActionEvent e) {
        JButton sourceBtn = (JButton) e.getSource();
        int id = (Integer) sourceBtn.getClientProperty("id");
        taskManager.deleteTaskWithID(id);
    }

}