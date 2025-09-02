import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MagnetDataWindow extends JFrame implements ActionListener, KeyListener {//used to save ata about a magnet
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton saveData;
    private JTextField magnetName;
    private JTextField fileName;
    private MagnetData data;
    private JFrame previous;
    private JFileChooser test;
    public MagnetDataWindow(int x, int y, double[] d, JFrame p, String name){//allows a user to duplicate a magnet
        setBounds(x, y, 400, 150);
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        previous = p;
        label1 = new JLabel("Save a copy of the magnet to the same file?");
        label1.setBounds(0, 0, 400, 15);
        add(label1);
        label2 = new JLabel("Magnet Name");
        label2.setBounds(75, 25, 100, 15);
        add(label2);
        saveData = new JButton("save");
        saveData.setBounds(300, 40, 75, 50);
        saveData.addActionListener(this);
        add(saveData);
        test = new JFileChooser();
        magnetName = new JTextField();
        magnetName.setBounds(10, 40, 200, 25);
        magnetName.addKeyListener(this);
        add(magnetName);
        fileName = new JTextField(name);
        data = new MagnetData(d, d.length==6, "");
    }
    public MagnetDataWindow(int x, int y, double[] d){//allows a user to save data about a magnet
        setBounds(x, y, 400, 150);
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        label1 = new JLabel("Save data about the magnet (length, radius etc.) to a file?");
        label1.setBounds(0, 0, 400, 15);
        add(label1);
        label2 = new JLabel("Magnet Name");
        label2.setBounds(75, 25, 100, 15);
        add(label2);
        label3 = new JLabel("File Name (the magnet data will be saved to your default directory)");
        label3.setBounds(0, 65, 400, 15);
        add(label3);
        saveData = new JButton("save");
        saveData.setBounds(300, 17, 75, 50);
        saveData.addActionListener(this);
        add(saveData);
        test = new JFileChooser();
        magnetName = new JTextField();
        magnetName.setBounds(10, 40, 200, 25);
        magnetName.addKeyListener(this);
        add(magnetName);
        fileName = new JTextField();
        fileName.setBounds(10, 80, 200, 25);
        fileName.addKeyListener(this);
        add(fileName);
        data = new MagnetData(d, d.length==6, "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!fileName.getText().isEmpty()) {
            FileHandler.writeSpecificLine(test.getCurrentDirectory().getPath() + "\\" + fileName.getText() + ".magnet", data.getName(), data);
            if (previous != null) {
                if (previous.getClass() == FileHandlerGUI.class) {
                    FileHandlerGUI previous2 = (FileHandlerGUI) previous;
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
