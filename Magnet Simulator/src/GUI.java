import javax.swing.*;

public class GUI extends JFrame {
    private JLabel[] inputLabels;
    private JTextField[] inputs;

    public GUI(){
        inputLabels = new JLabel[4];
        inputs = new JTextField[4];
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        String[] inputNames = {"Length", "Split Length", "Inner Radius", "Outer Radius"};
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new JTextField();
            inputs[i].setBounds(10, 50*i, 50, 50);
            add(inputs[i]);
            inputLabels[i] = new JLabel(inputNames[i]);
            inputLabels[i].setBounds(80, 50*i, 150,50);
            add(inputLabels[i]);
        }
        setVisible(true);
    }
}
