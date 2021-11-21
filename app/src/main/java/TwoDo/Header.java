package TwoDo;

import javax.swing.*;
import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
import java.awt.*;

class Header extends JPanel {
    JPanel container = new JPanel();
    JLabel title = new JLabel("Hello User!");
    JLabel subtitle = new JLabel("You have no more tasks. Add some");
    JButton addBtn = new JButton("Add");
    
    public Header() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setAlignmentX(0);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setAlignmentX(0);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        subtitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        subtitle.setAlignmentX(0);
        subtitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        addBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        addBtn.setAlignmentX(0);

        add(title);
        add(subtitle);
        add(addBtn);
    }

    public void setParentActionHandler(ActionListener handler) {
        addBtn.addActionListener(handler);
    }
}
