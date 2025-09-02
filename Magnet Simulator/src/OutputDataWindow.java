import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OutputDataWindow extends JFrame implements ActionListener{//allows the user to save data about the magnetic field as a .txt file, which can then be imported into a spreadsheet
    private JLabel label1;
    private JLabel label2;
    private JButton saveData;
    private JTextField fileName;
    private String[][][][] data;
    private JFileChooser test;
    public OutputDataWindow(int x, int y, String[][][][] d){
        setBounds(x, y, 400, 150);
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        test = new JFileChooser();//this is used to find the user's default directory
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
        FileHandler.write3dArray(data[0], test.getCurrentDirectory().getPath() + "\\" + fileName.getText());
        FileHandler.write3dArray(data[1], test.getCurrentDirectory().getPath() + "\\" + "(Vector values X[radial])" + fileName.getText());
        FileHandler.write3dArray(data[2], test.getCurrentDirectory().getPath() + "\\" + "(Vector values Y[radial])" + fileName.getText());
        FileHandler.write3dArray(data[3], test.getCurrentDirectory().getPath() + "\\" + "(Vector values Z[axial])" + fileName.getText());
        dispose();
    }
}
