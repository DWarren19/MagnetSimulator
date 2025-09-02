import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FileHandlerGUI extends JFrame implements ActionListener {
    private MagnetButton[] buttons;
    private String fileName;
    private JButton back;
    private LoginGUI previous;
    private JFileChooser fileChooser;
    private Component[] fileChooserComponents;
    private Component[] fileChooserComponents2;
    private Component[] fileChooserComponents3;
    private JButton open;
    private JButton cancel;
    public FileHandlerGUI(LoginGUI p){
        setBounds(p.getBounds());
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        fileName = "";

        fileChooser = new JFileChooser();
        fileChooser.setBounds(0, 0, 450, 450);
        fileChooserComponents = fileChooser.getComponents();
        if (fileChooserComponents[fileChooserComponents.length-1].getClass() == JPanel.class) {//this loop adds actionListeners to the open and cancel buttons built into the file chooser
            fileChooserComponents2 = ((JPanel) fileChooserComponents[fileChooserComponents.length - 1]).getComponents();
            if (fileChooserComponents2[fileChooserComponents2.length-1].getClass() == JPanel.class) {
                fileChooserComponents3 = ((JPanel) fileChooserComponents2[fileChooserComponents2.length - 1]).getComponents();
                open = (JButton) fileChooserComponents3[fileChooserComponents3.length - 2];
                open.addActionListener(this);
                cancel = (JButton) fileChooserComponents3[fileChooserComponents3.length - 1];
                cancel.addActionListener(this);
            }
        }
        fileChooser.setFileFilter(new FileNameExtensionFilter("Magnet Data File", "magnet"));//sets the file type of the file chooser to only open files created with this software
        add(fileChooser);

        back = new JButton("Back");
        back.setBounds(10, 10, 100, 50);
        back.addActionListener(this);
        add(back);
        back.setVisible(false);

        previous = p;

    }
    public void reset(boolean resetName){//resets the GUI, either removing all buttons or returning to the file chooser
        for (int i = 0; i < buttons.length; i++) {
            remove(buttons[i]);
        }
        if(resetName) {
            fileName = "";
            fileChooser.setVisible(true);
            back.setVisible(false);
        }
        update(getGraphics());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back || e.getSource() == cancel) {//returns to the previous screen
            previous.setVisible(true);
            previous.setBounds(getBounds());
            dispose();
        } else {//the only button that this is true for is the 'open' button
            if (fileChooser.getSelectedFile() != null) {
                fileName = fileChooser.getSelectedFile().getPath();
                fileChooser.setVisible(false);
                back.setVisible(true);
                String[] magnetNames = FileHandler.readMagnetArray(fileName);
                if (magnetNames != null) {//creates an array of buttons
                    buttons = new MagnetButton[magnetNames.length];
                    for (int i = 0; i < buttons.length; i++) {
                        buttons[i] = new MagnetButton(magnetNames[i], this, fileName);
                        buttons[i].setBounds(10, 60 * i + 70, buttons[i].getWidth(), buttons[i].getHeight());
                        add(buttons[i]);
                        update(getGraphics());
                        buttons[i].update(buttons[i].getGraphics());
                    }
                }
            }
        }
    }

    public void loadData() {
        String[] magnetNames = FileHandler.readMagnetArray(fileName);
        if (magnetNames != null) {
            buttons = new MagnetButton[magnetNames.length];
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new MagnetButton(magnetNames[i], this, fileName);
                buttons[i].setBounds(10, 60 * i + 70, buttons[i].getWidth(), buttons[i].getHeight());
                add(buttons[i]);
            }
            update(getGraphics());
        }
    }
}
