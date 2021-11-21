package TwoDo;

import javax.swing.*;
import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
import java.awt.*;

class Task extends JPanel {
    // JPanel task = new JPanel();
    JLabel title = new JLabel("Task #1");
    JLabel date = new JLabel("07/02/2002");
    JPanel buttonPane = new JPanel();
    JButton delBtn = new JButton("Delete");
    JButton editBtn = new JButton("Edit");

    public Task(String titleText, String dateText) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // setPreferredSize(new Dimension(200, 200));

        title.setText(titleText);
        title.setAlignmentX(0);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        date.setText(dateText);
        date.setAlignmentX(0);
        date.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        buttonPane.add(Box.createHorizontalGlue());
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
}