package TwoDo.components;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
import java.awt.*;

import TwoDo.db.TaskData;

public class Dashboard extends JPanel {
    JLabel title = new JLabel("Stuff To Do");
    JPanel buttonPane = new JPanel();
    JButton addBtn = new JButton("Add");
    JButton refreshBtn = new JButton("Refresh");
    Border padding = BorderFactory.createEmptyBorder(10, 20, 10, 20);
    Border margin = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    Border bottomSpacing = BorderFactory.createEmptyBorder(0, 0, 10, 0);
    Border topSpacing = BorderFactory.createEmptyBorder(20, 0, 0, 0);

    TaskEditor taskEditor = new TaskEditor();

    public Dashboard() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(250, 600));
        setBorder(BorderFactory.createCompoundBorder(margin, padding));

            title.setAlignmentX(0);
            title.setBorder(bottomSpacing);
            title.setFont(new Font("SansSerif", Font.BOLD, 24));

            buttonPane.setAlignmentX(0);
            buttonPane.setLayout(new FlowLayout(FlowLayout.LEADING));
                addBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
                refreshBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
            buttonPane.add(addBtn);
            buttonPane.add(refreshBtn);

            closeEditor();

        add(title);
        add(buttonPane);
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

    public boolean validateEditor() {
        return taskEditor.validateData();
    }
}
