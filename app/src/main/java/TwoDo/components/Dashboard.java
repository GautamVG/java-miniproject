package TwoDo.components;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
import java.awt.*;

import TwoDo.db.TaskData;

public class Dashboard extends JPanel {
    JLabel title = new JLabel("Hello User!");
    JButton addBtn = new JButton("Add");
    Border padding = BorderFactory.createEmptyBorder(10, 20, 10, 20);
    Border margin = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    Border bottomSpacing = BorderFactory.createEmptyBorder(0, 0, 10, 0);
    Border topSpacing = BorderFactory.createEmptyBorder(20, 0, 0, 0);

    JLabel noTasksLabel = new JLabel("No more tasks! Yay!!");
    TaskEditor taskEditor = new TaskEditor();

    public Dashboard() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(200, 600));
        setBorder(BorderFactory.createCompoundBorder(margin, padding));

            title.setAlignmentX(0);
            title.setBorder(bottomSpacing);
            title.setFont(new Font("SansSerif", Font.BOLD, 24));

            noTasksLabel.setAlignmentX(0);
            noTasksLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
            noTasksLabel.setBorder(bottomSpacing);
            noTasksLabel.setVisible(false);

            addBtn.setAlignmentX(0);
            addBtn.setFont(new Font("SansSerif", Font.BOLD, 16));

            taskEditor.setBorder(topSpacing);
            closeEditor();

        add(title);
        add(noTasksLabel);
        add(addBtn);
        add(taskEditor);
    }

    public void setParentActionListener(ActionListener handler) {
        addBtn.addActionListener(handler);
        taskEditor.setParentActionHandler(handler);
    }

    public void openEditor() {
        taskEditor.setEmpty();
        taskEditor.setVisible(true);
    }

    public void openEditor(TaskData data) {
        taskEditor.setData(data);
        taskEditor.setVisible(true);
    }

    public TaskData getEditorData() {
        return taskEditor.getData();
    }

    public void closeEditor() {
        taskEditor.setVisible(false);
    }

    public boolean isEditorDataValid() {
        return taskEditor.isDataValid();
    }
}
