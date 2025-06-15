import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OutputDataWindow extends JFrame implements ActionListener{
    private JLabel label1;
    private JLabel label2;
    private JButton saveData;
    private JTextField fileName;
    private double[][][] data;
    public OutputDataWindow(int x, int y, double[][][] d){
        setBounds(x, y, 400, 150);
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        label1 = new JLabel("Save data about the magnetic field to a file?");
        label1.setBounds(0, 0, 400, 15);
        add(label1);
        label2 = new JLabel("File Name");
        label2.setBounds(75, 25, 100, 15);
        add(label2);
        saveData = new JButton("save");
        saveData.setBounds(300, 40, 75, 50);
        saveData.addActionListener(this);
        add(saveData);
        fileName = new JTextField();
        fileName.setBounds(10, 40, 200, 50);
        add(fileName);
        data = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileHandler.write3dArray(data, fileName.getText());
        dispose();
    }
}
