package TwoDo;

import javax.swing.*;
import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
import java.awt.*;

class TaskEditor extends JPanel {
    boolean newTask;
    int index;

    JLabel titleLabel = new JLabel("Enter title");
    JLabel descLabel = new JLabel("Enter desc");
    JLabel dateLabel = new JLabel("Enter due date");
    JTextField title = new JTextField(45);
    JTextField desc = new JTextField(45);
    JTextField date = new JTextField(45);
    JPanel buttonPane = new JPanel();
    JButton saveBtn = new JButton("Save");
    JButton cancelBtn = new JButton("Cancel");

    public TaskEditor() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(150, 150));

        titleLabel.setAlignmentX(0);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 8));
        descLabel.setAlignmentX(0);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 8));
        dateLabel.setAlignmentX(0);
        dateLabel.setFont(new Font("SansSerif", Font.PLAIN, 8));
        title.setAlignmentX(0);
        desc.setAlignmentX(0);
        date.setAlignmentX(0);

        buttonPane.add(saveBtn);
        buttonPane.add(cancelBtn);
        buttonPane.setAlignmentX(0);

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
        title.setText("");
        desc.setText("");
        date.setText("");
        newTask = true;
        index = -1;
        setActionCommands();
    }

    public void setData(int i, String _title, String _desc, String _date) {
        newTask = false;
        title.setText(_title);
        desc.setText(_desc);
        date.setText(_date);
        index = i;
        setActionCommands();
    }

    public void setIndex(int i) {
        index = i;
    }

    public void setActionCommands() {
        saveBtn.setActionCommand("Save:" + index);
        cancelBtn.setActionCommand("Cancel:" + index);
    }
}