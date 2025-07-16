import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MagnetDataWindow extends JFrame implements ActionListener, KeyListener {
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton saveData;
    private JTextField magnetName;
    private JTextField fileName;
    private MagnetData data;
    private JFileChooser fileChooser;
    private Component[] fileChooserComponents;
    private Component[] fileChooserComponents2;
    private Component[] fileChooserComponents3;
    private JButton save;
    private JButton close;
    JFrame previous;
    public MagnetDataWindow(int x, int y, double[] d, JFrame p, String name){
        setBounds(x, y, 500, 550);
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        previous = p;
        /*label1 = new JLabel("Save a copy of the magnet to the same file?");
        label1.setBounds(0, 0, 400, 15);
        add(label1);
        label2 = new JLabel("Magnet Name");
        label2.setBounds(75, 25, 100, 15);
        add(label2);
        saveData = new JButton("save");
        saveData.setBounds(300, 40, 75, 50);
        saveData.addActionListener(this);
        add(saveData);
        magnetName = new JTextField();
        magnetName.setBounds(10, 40, 200, 25);
        magnetName.addKeyListener(this);
        add(magnetName);
        fileName = new JTextField(name);
        */
        data = new MagnetData(d, d.length==6, "");
        fileChooser = new JFileChooser();
        fileChooser.setBounds(0, 0, 450, 450);
        fileChooserComponents = fileChooser.getComponents();
        if (fileChooserComponents[fileChooserComponents.length-1].getClass() == JPanel.class) {
            fileChooserComponents2 = ((JPanel) fileChooserComponents[fileChooserComponents.length - 1]).getComponents();
            if (fileChooserComponents2[fileChooserComponents2.length-1].getClass() == JPanel.class) {
                fileChooserComponents3 = ((JPanel) fileChooserComponents2[fileChooserComponents2.length - 1]).getComponents();
                save = (JButton) fileChooserComponents3[fileChooserComponents3.length - 2];
                save.addActionListener(this);
                close = (JButton) fileChooserComponents3[fileChooserComponents3.length - 1];
                close.addActionListener(this);
            }
        }
    }
    public MagnetDataWindow(int x, int y, double[] d){
        setBounds(x, y, 400, 150);
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        /*label1 = new JLabel("Save data about the magnet (length, radius etc.) to a file?");
        label1.setBounds(0, 0, 400, 15);
        add(label1);
        label2 = new JLabel("Magnet Name");
        label2.setBounds(75, 25, 100, 15);
        add(label2);
        label3 = new JLabel("File Name");
        label3.setBounds(75, 65, 100, 15);
        add(label3);
        saveData = new JButton("save");
        saveData.setBounds(300, 40, 75, 50);
        saveData.addActionListener(this);
        add(saveData);
        magnetName = new JTextField();
        magnetName.setBounds(10, 40, 200, 25);
        magnetName.addKeyListener(this);
        add(magnetName);
        fileName = new JTextField();
        fileName.setBounds(10, 80, 200, 25);
        fileName.addKeyListener(this);
        add(fileName);
        */
        data = new MagnetData(d, d.length==6, "");
        fileChooser = new JFileChooser();
        fileChooser.setBounds(0, 0, 450, 450);
        fileChooserComponents = fileChooser.getComponents();
        if (fileChooserComponents[fileChooserComponents.length-1].getClass() == JPanel.class) {
            fileChooserComponents2 = ((JPanel) fileChooserComponents[fileChooserComponents.length - 1]).getComponents();
            if (fileChooserComponents2[fileChooserComponents2.length-1].getClass() == JPanel.class) {
                fileChooserComponents3 = ((JPanel) fileChooserComponents2[fileChooserComponents2.length - 1]).getComponents();
                save = (JButton) fileChooserComponents3[fileChooserComponents3.length - 2];
                save.addActionListener(this);
                close = (JButton) fileChooserComponents3[fileChooserComponents3.length - 1];
                close.addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!fileChooser.getSelectedFile().getName().equals("")){
            FileHandler.writeSpecificLine(fileName.getText(), data.getName(), data);
            if(previous!=null){
                if (previous.getClass() == FileHandlerGUI.class){
                    FileHandlerGUI previous2 = (FileHandlerGUI)previous;
                    previous2.reset(false);
                    previous2.loadData();
                }
            }
            dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        data.setName(magnetName.getText());
    }
}
