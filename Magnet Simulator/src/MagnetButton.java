import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MagnetButton extends JButton implements ActionListener {
    private JLabel name;
    private JButton deleteButton;
    private JButton editButton;
    private JButton duplicateButton;
    public MagnetButton(String n) {
        super();
        addActionListener(this);
        setSize(300, 50);
        name = new JLabel(n);
        name.setBounds(10, 20, 100, 10);
        add(name);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(120, 15, 50, 20);
        deleteButton.addActionListener(this);
        add(deleteButton);

        editButton = new JButton("Edit");
        editButton.setBounds(180, 15, 50, 20);
        editButton.addActionListener(this);
        add(editButton);

        duplicateButton = new JButton("Duplicate");
        duplicateButton.setBounds(220, 15, 50, 20);
        editButton.addActionListener(this);
        add(duplicateButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
