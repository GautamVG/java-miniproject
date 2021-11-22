package TwoDo;

import javax.swing.*;
import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
import java.awt.*;

import TwoDo.db.TaskData;

class Task extends JPanel {
    int index;

    JLabel title = new JLabel("Task #1");
    JLabel desc = new JLabel("Task #1");
    JLabel date = new JLabel("07/02/2002");
    JPanel buttonPane = new JPanel();
    JButton delBtn = new JButton("Delete");
    JButton editBtn = new JButton("Edit");

    public Task(TaskData data) {
        String titleText = data.title;
        String dateText = data.date;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(150, 150));

        title.setText(titleText);
        title.setAlignmentX(0);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        date.setText(dateText);
        date.setAlignmentX(0);
        date.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        buttonPane.add(editBtn);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(delBtn);
        buttonPane.setAlignmentX(0);

        add(title);
        add(date);
        add(buttonPane);
    }

    public void setParentActionHandler(ActionListener actionHandler) {
        delBtn.addActionListener(actionHandler);
        editBtn.addActionListener(actionHandler);
    }

    public void syncUIWith(TaskData data) {
        title.setText(data.title);
        desc.setText(data.desc);
        date.setText(data.date);
    }

    public void setIndex(int i) {
        index = i;
        setActionCommands();
    }

    public void setActionCommands() {
        editBtn.setActionCommand("Edit:" + index);
        delBtn.setActionCommand("Delete:" + index);
    }
}