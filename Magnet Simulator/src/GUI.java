import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JLabel[] inputLabels;
    private JLabel[] unitLabels;
    private JTextField[] inputs;
    private JButton densityUnits;
    private JLabel densityLabel;
    private JLabel densityButtonLabel;
    private JTextField densityInput;
    private JTextArea crossSectionDiagram;
    private boolean perCm;

    public GUI(){
        inputLabels = new JLabel[4];
        inputs = new JTextField[4];
        unitLabels = new JLabel[4];

        densityUnits = new JButton();
        densityUnits.setBounds(300, 60, 150, 150);
        add(densityUnits);
        densityUnits.addActionListener(this);

        densityLabel = new JLabel();
        densityLabel.setBounds(350, 10, 100, 30);
        add(densityLabel);
        densityButtonLabel = new JLabel("switch to");
        densityButtonLabel.setBounds(350, 40, 100, 30);
        add(densityButtonLabel);

        densityInput = new JTextField();
        densityInput.setBounds(300, 10, 50, 50);
        add(densityInput);

        crossSectionDiagram = new JTextArea();
        crossSectionDiagram.setBounds(10, 400, );

        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        String[] inputNames = {"Length (L)", "Split Length (S)", "Inner Radius (IR)", "Outer Radius (OR)"};
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new JTextField();
            inputs[i].setBounds(10, 50*i, 55, 50);
            inputs[i].setOpaque(false);
            add(inputs[i]);
            unitLabels[i] = new JLabel("cm");
            unitLabels[i].setBounds(40, 50*i, 50, 50);
            add(unitLabels[i]);
            inputLabels[i] = new JLabel(inputNames[i]);
            inputLabels[i].setBounds(80, 50*i, 150,50);
            add(inputLabels[i]);
        }
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == densityUnits){
            if(perCm){
                perCm = false;
                densityUnits.setText("turns per cm^2");
                densityLabel.setText("turns per side");
            } else {
                perCm = true;
                densityUnits.setText("turns per side");
                densityLabel.setText("turns per cm^2");
            }
        }
    }
}
