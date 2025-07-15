import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FileHandlerGUI extends JFrame implements ActionListener, KeyListener {
    private MagnetButton[] buttons;
    private JTextField fileName;
    private JLabel fileLabel;
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
        setResizable(false);

        fileLabel = new JLabel("File Name:");
        fileLabel.setBounds(10, 10, 100, 20);
        fileLabel.setVisible(false);
        add(fileLabel);

        fileName = new JTextField();
        fileName.setBounds(10, 40, 100, 20);
        fileName.addKeyListener(this);
        fileName.setVisible(false);
        add(fileName);

        fileChooser = new JFileChooser();
        fileChooser.setBounds(0, 0, 450, 450);
        fileChooserComponents = fileChooser.getComponents();
        if (fileChooserComponents[fileChooserComponents.length-1].getClass() == JPanel.class) {
            fileChooserComponents2 = ((JPanel) fileChooserComponents[fileChooserComponents.length - 1]).getComponents();
            if (fileChooserComponents2[fileChooserComponents2.length-1].getClass() == JPanel.class) {
                fileChooserComponents3 = ((JPanel) fileChooserComponents2[fileChooserComponents2.length - 1]).getComponents();
                open = (JButton) fileChooserComponents3[fileChooserComponents3.length - 2];
                open.addActionListener(this);
                cancel = (JButton) fileChooserComponents3[fileChooserComponents3.length - 1];
                cancel.addActionListener(this);
            }
        }
        /*for (Component c: fileChooserComponents){
            System.out.println(c.getClass()+"\n");
            if (c.getClass() == JPanel.class){
                for (Component d: ((JPanel) c).getComponents()){
                    System.out.println(d.getClass());
                    if (d.getClass() == JPanel.class){
                        for (Component e: ((JPanel) d).getComponents()){
                            System.out.print("   "+e.getClass());
                        }
                    }
                }
            }
        }*/
        fileChooser.getComponents();
        //File documentsFolder = fileChooser.getCurrentDirectory();
        //fileChooser.setCurrentDirectory(new File());
        add(fileChooser);

        back = new JButton("Back");
        back.setBounds(150, 10, 100, 50);
        back.addActionListener(this);
        add(back);
        back.setVisible(false);

        previous = p;

    }
    public void reset(boolean resetName){
        for (int i = 0; i < buttons.length; i++) {
            remove(buttons[i]);
        }
        if(resetName) {
            fileName.setText("");
        }
        update(getGraphics());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back || e.getSource() == cancel) {
            previous.setVisible(true);
            previous.setBounds(getBounds());
            dispose();
        } else {
            if (fileChooser.getSelectedFile() != null) {
                fileChooser.setVisible(false);
                fileLabel.setVisible(true);
                fileName.setVisible(true);
                back.setVisible(true);
                String[] magnetNames = FileHandler.readMagnetArray(fileChooser.getSelectedFile().getName());
                if (magnetNames != null) {
                    buttons = new MagnetButton[magnetNames.length];
                    for (int i = 0; i < buttons.length; i++) {
                        buttons[i] = new MagnetButton(magnetNames[i], this, fileName.getText());
                        buttons[i].setBounds(10, 60 * i + 70, buttons[i].getWidth(), buttons[i].getHeight());
                        add(buttons[i]);
                        update(getGraphics());
                        System.out.println("button added");
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            loadData();
        }
    }
    public void loadData() {
        String[] magnetNames = FileHandler.readMagnetArray(fileName.getText());
        if (magnetNames != null) {
            buttons = new MagnetButton[magnetNames.length];
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new MagnetButton(magnetNames[i], this, fileName.getText());
                buttons[i].setBounds(10, 60 * i + 70, buttons[i].getWidth(), buttons[i].getHeight());
                add(buttons[i]);
            }
            update(getGraphics());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
