package TwoDo.components;

import javax.swing.*;
import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
import java.awt.*;

import TwoDo.db.TaskData;

public class TaskEditor extends JPanel {
    TaskData taskData = new TaskData();

    JLabel titleLabel = new JLabel("Enter title");
    JLabel descLabel = new JLabel("Enter desc");
    JLabel dateLabel = new JLabel("Enter due date");
    JTextField title = new JTextField();
    JTextField desc = new JTextField();
    JTextField date = new JTextField();
    JPanel buttonPane = new JPanel();
    JButton saveBtn = new JButton("Save");
    JButton cancelBtn = new JButton("Cancel");

    public TaskEditor() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));

            titleLabel.setAlignmentX(0);
            titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            title.setAlignmentX(0);
            title.setFont(new Font("SansSerif", Font.PLAIN, 14));

            descLabel.setAlignmentX(0);
            descLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            desc.setAlignmentX(0);
            desc.setFont(new Font("SansSerif", Font.PLAIN, 14));

            dateLabel.setAlignmentX(0);
            dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            date.setAlignmentX(0);
            date.setFont(new Font("SansSerif", Font.PLAIN, 14));

            buttonPane.setAlignmentX(0);
            buttonPane.add(saveBtn);
            buttonPane.add(cancelBtn);

        add(titleLabel);
        add(title);
        add(descLabel);
        add(desc);
        add(dateLabel);
        add(date);
        add(buttonPane);
    }

    public void setParentActionHandler(ActionListener handler) {
        saveBtn.addActionListener(handler);
        cancelBtn.addActionListener(handler);
    }

    public void setEmpty() {
        taskData.id = -1;
        title.setText("");
        desc.setText("");
        date.setText("");
    }

    public void setData(TaskData _data) {
        taskData = _data;
        title.setText(taskData.title);
        desc.setText(taskData.desc);
        date.setText(taskData.getDateShort());
    }

    public TaskData getData() {
        taskData.title = title.getText();
        taskData.desc = desc.getText();
        taskData.setDate(date.getText());
        return taskData;
    }

    public boolean isDataValid() {
        if (title.getText().isBlank()) {
            return false;
        }
        return true;
    }
}