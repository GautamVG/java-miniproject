package TwoDo.components;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
import java.awt.*;

import TwoDo.db.TaskData;

public class Task extends JPanel {
    TaskData taskData;

    Border defaultBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    Border alertBorder = BorderFactory.createLineBorder(Color.red);
    Border highlightBorder = BorderFactory.createLineBorder(Color.cyan);
    JPanel dataPane = new JPanel();
        JLabel title = new JLabel("Task #1");
        JLabel desc = new JLabel("Task #1");
        JLabel date = new JLabel("07/02/2002");
    JPanel buttonPane = new JPanel();
        JButton delBtn = new JButton("Delete");
        JButton editBtn = new JButton("Edit");
    Border spacing = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    public Task(TaskData data) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 150));

            dataPane.setLayout(new BorderLayout());
            dataPane.setBorder(spacing);
                title.setFont(new Font("SansSerif", Font.PLAIN, 18));
                desc.setFont(new Font("SansSerif", Font.PLAIN, 14));
                desc.setVerticalAlignment(SwingConstants.TOP);
                date.setFont(new Font("SansSerif", Font.PLAIN, 14));
            dataPane.add(title, BorderLayout.PAGE_START);
            dataPane.add(desc, BorderLayout.CENTER);
            dataPane.add(date, BorderLayout.PAGE_END);

            buttonPane.setLayout(new FlowLayout(FlowLayout.TRAILING));
            buttonPane.setBorder(spacing);
            buttonPane.add(editBtn);
            buttonPane.add(delBtn);

        add(dataPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

        syncUIWith(data);
    }

    public void setParentActionListener(ActionListener listener) {
        editBtn.addActionListener(listener);
        delBtn.addActionListener(listener);
    }

    public void syncUIWith(TaskData data) {
        taskData = data;
        title.setText(data.title);
        desc.setText(data.desc);
        date.setText(taskData.getDateLong());
        editBtn.putClientProperty("id", data.id);
        delBtn.putClientProperty("id", data.id);
        if (data.isPastDueDate()) {
            setBorder(BorderFactory.createCompoundBorder(alertBorder, defaultBorder));
        } else {
            setBorder(BorderFactory.createCompoundBorder(highlightBorder, defaultBorder));
        }
    }

    public int getKey() {
        return taskData.id;
    }

    public TaskData getData() {
        return taskData;
    }
}