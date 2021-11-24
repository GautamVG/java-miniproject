package TwoDo.components;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.*;

import TwoDo.db.TaskData;

public class TaskEditor extends JPanel {
    private TaskData taskData = new TaskData();

    private JLabel titleLabel = new JLabel("Enter title");
    private JTextField title = new JTextField();
    private JLabel descLabel = new JLabel("Enter desc");
    private JTextField desc = new JTextField();
    private JLabel dateLabel = new JLabel("Enter due date");
    private JTextField date = new JTextField();
    private JLabel timeLabel = new JLabel("Enter due time");
    private JTextField time = new JTextField();
    private JPanel buttonPane = new JPanel();
    private JButton saveBtn = new JButton("Save");
    private JButton cancelBtn = new JButton("Cancel");
    private Border topSpacing = BorderFactory.createEmptyBorder(5, 0, 0, 0);

    public TaskEditor() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

            titleLabel.setAlignmentX(0);
            titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            titleLabel.setBorder(topSpacing);
            title.setAlignmentX(0);
            title.setFont(new Font("SansSerif", Font.PLAIN, 14));

            descLabel.setAlignmentX(0);
            descLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            descLabel.setBorder(topSpacing);
            desc.setAlignmentX(0);
            desc.setFont(new Font("SansSerif", Font.PLAIN, 14));

            dateLabel.setAlignmentX(0);
            dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            descLabel.setBorder(topSpacing);
            date.setAlignmentX(0);
            date.setFont(new Font("SansSerif", Font.PLAIN, 14));

            timeLabel.setAlignmentX(0);
            timeLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
            timeLabel.setBorder(topSpacing);
            time.setAlignmentX(0);
            time.setFont(new Font("SansSerif", Font.PLAIN, 14));

            buttonPane.setAlignmentX(0);
            buttonPane.setBorder(topSpacing);
            buttonPane.add(saveBtn);
            buttonPane.add(cancelBtn);

        add(titleLabel);
        add(title);
        add(descLabel);
        add(desc);
        add(dateLabel);
        add(date);
        add(timeLabel);
        add(time);
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
        time.setText("");
    }

    public void setData(TaskData _data) {
        taskData = _data;
        title.setText(taskData.title);
        desc.setText(taskData.desc);
        date.setText(taskData.getDateShort());
        time.setText(taskData.getTimeShort());
    }

    public TaskData getData() {
        return taskData;
    }

    public boolean validateData() {
        String datetime = "";

        if (title.getText().isBlank()) {
            // Title is empty
            return false;
        }
        if (!date.getText().isBlank()) {
            if (time.getText().isBlank()) {
                datetime = date.getText() + ", 12:00 am";
            } else {
                datetime = date.getText() + ", " + time.getText();
            }
        } else if (!time.getText().isBlank()) {
            return false;
        }

        if (!taskData.setDate(datetime)) {
            return false;
        }

        taskData.title = title.getText();
        taskData.desc = desc.getText();
        return true;
    }
}