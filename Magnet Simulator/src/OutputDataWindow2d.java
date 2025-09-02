import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutputDataWindow2d extends JFrame implements ActionListener{
    private JLabel label1;
    private JLabel label2;
    private JButton saveData;
    private JTextField fileName;
    private String[][] data;
    private JFileChooser test;
    public OutputDataWindow2d(int x, int y, String[][] d){
        setBounds(x, y, 400, 150);
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        test = new JFileChooser();
        label1 = new JLabel("Save data about the magnetic field to a file?");
        label1.setBounds(0, 0, 400, 15);
        add(label1);
        label2 = new JLabel("File Name (the magnet data will be saved to your default directory)");
        label2.setBounds(0, 25, 400, 15);
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
        FileHandler.write2dArray(data, test.getCurrentDirectory().getPath() + "\\" + fileName.getText());
        dispose();
    }
}
