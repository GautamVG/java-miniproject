package TwoDo.components;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.ActionListener;
import java.awt.*;

import TwoDo.db.TaskData;

public class Task extends JPanel {
    private TaskData taskData;

    private Border defaultBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    private Border alertBorder = BorderFactory.createLineBorder(Color.red);
    private Border highlightBorder = BorderFactory.createLineBorder(Color.cyan);
    private JPanel dataPane = new JPanel();
        private JLabel title = new JLabel("Task #1");
        private JLabel desc = new JLabel("Task #1");
        private JLabel date = new JLabel("07/02/2002");
    private JPanel buttonPane = new JPanel();
        private JButton delBtn = new JButton("Delete");
        private JButton editBtn = new JButton("Edit");
    private Border spacing = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    public Task(TaskData data) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 180));

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