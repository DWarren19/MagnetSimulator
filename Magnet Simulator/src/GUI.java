import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements KeyListener, ActionListener {
    private JLabel[] inputLabels;
    private JLabel[] unitLabels;
    private JTextField[] inputs;
    private JButton densityUnits;
    private JLabel densityLabel;
    private JLabel densityButtonLabel;
    private JTextField densityInput;
    private Diagram crossSectionDiagram;
    private JButton nextButton;
    private boolean perCm;
    private String density;
    private boolean round;
    protected double[] inputData;
    private RoundMagnet magnet;
    private JButton save;
    private JFrame previous;
    private JButton back;

    public GUI(JFrame p){
        String[] inputNames =  {"Length (L)", "Split Length (S)", "Inner Radius (IR)", "Outer Radius (OR)"};
        round = true;
        previous = p;
        setUpGui(inputNames);
    }

    public GUI(String[] inputNames, boolean r, LoginGUI p) {
        previous = p;
        round = r;
        setUpGui(inputNames);
    }
    public void setUpGui(String[] inputNames){
        density = "";
        inputLabels = new JLabel[inputNames.length];
        inputs = new JTextField[inputNames.length];
        unitLabels = new JLabel[inputNames.length];

        densityUnits = new JButton("turns per cm^2");
        densityUnits.setBounds(300, 60, 150, 50);
        densityUnits.setOpaque(false);
        densityUnits.setBackground(Color.lightGray);
        add(densityUnits);
        densityUnits.addActionListener(this);

        densityLabel = new JLabel("turns per side");
        densityLabel.setBounds(350, 20, 100, 30);
        add(densityLabel);
        densityButtonLabel = new JLabel("switch to");
        densityButtonLabel.setBounds(350, 55, 100, 30);
        add(densityButtonLabel);

        densityInput = new JTextField();
        densityInput.setBounds(300, 10, 50, 50);
        densityInput.addKeyListener(this);
        add(densityInput);

        double[] data = {0, 0, 0, 0};
        crossSectionDiagram = new Diagram(data, round);
        add(crossSectionDiagram);

        nextButton = new JButton("Next");
        nextButton.setBounds(350, 430, 100, 30);
        add(nextButton);
        nextButton.addActionListener(this);

        save = new JButton("Save");
        save.setBounds(350, 390, 100, 30);
        add(save);
        save.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(350, 470, 100, 30);
        add(back);
        back.addActionListener(this);

        perCm = false;
        inputData = new double[inputs.length];

        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(previous.getBounds());
        setLayout(null);
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new JTextField();
            inputs[i].setBounds(10, 50*i, 55, 50);
            inputs[i].setOpaque(false);
            inputs[i].addKeyListener(this);
            add(inputs[i]);
            unitLabels[i] = new JLabel("cm");
            unitLabels[i].setBounds(40, 50*i, 50, 50);
            add(unitLabels[i]);
            inputLabels[i] = new JLabel(inputNames[i]);
            inputLabels[i].setBounds(80, 50*i, 150,50);
            add(inputLabels[i]);
        }
        setVisible(true);
        crossSectionDiagram.setMagnetData(data);
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
        if (e.getSource() == nextButton && strToInt(density)!=0 && inputData[0]!=0 && inputData[3]!=0){
            if (strToInt(density) > (inputData[0]-inputData[1])*(inputData[3]-inputData[2])) {
                double densityCm;
                if (perCm) {
                    densityCm = Math.sqrt(strToInt(density));
                } else {
                    densityCm = Math.sqrt(strToInt(density) / ((inputData[0] - inputData[1]) * (inputData[3] - inputData[2]) / 2));
                }
                if (round) {
                    magnet = new RoundMagnet(inputData[0], inputData[1], inputData[2], inputData[3], densityCm, 100);
                } else {
                    magnet = new RoundMagnet(inputData[0], inputData[1], inputData[2], inputData[3], densityCm, 100, inputData[4]);
                }
                SimulationGUI nextGUI = new SimulationGUI(inputData, densityCm, crossSectionDiagram, this, magnet);
                setVisible(false);
            }
        } else if (e.getSource() == save && strToInt(density)!=0 && inputData[0]!=0 && inputData[3]!=0){
            if (strToInt(density) > (inputData[0]-inputData[1])*(inputData[3]-inputData[2])) {
                double[] outputData = new double[inputData.length + 1];
                for (int i = 0; i < inputData.length; i++) {
                    outputData[i] = inputData[i];
                }
                double densityCm;
                if (perCm) {
                    densityCm = Math.sqrt(strToInt(density));
                } else {
                    densityCm = Math.sqrt(strToInt(density) / ((inputData[0] - inputData[1]) * (inputData[3] - inputData[2]) / 2));
                }
                outputData[inputData.length] = densityCm;
                MagnetDataWindow saveData = new MagnetDataWindow(getX(), getY(), outputData);
            }
        } else if (e.getSource() == back){
            previous.setVisible(true);
            previous.setBounds(getBounds());
            dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == densityInput) {
            density += e.getKeyChar();
        } else {
            for (int n = 0; n < inputs.length; n++) {
                if(e.getSource() == inputs[n]) {
                    for (int i = 0; i < inputData.length; i++) {
                        inputData[i] = strToInt(inputs[i].getText());
                    }
                    crossSectionDiagram.setMagnetData(inputData);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == densityInput) {
            density = densityInput.getText();
        }
        for (int n = 0; n < inputs.length; n++) {
            if(e.getSource() == inputs[n]) {
                inputData[n] = strToInt(inputs[n].getText());
                crossSectionDiagram.setMagnetData(inputData);
            }
        }
    }
    public static double strToInt(String s){
        double total = 0;
        int value = 1;
        char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
        for (int i = s.length()-1; i >= 0; i-=1) {
            for (int n = 0; n < numbers.length; n++) {
                if(s.charAt(i) == numbers[n]){
                    total+=n*value;
                    value*=10;
                } else if(s.charAt(i) == '.'){
                    total/=value;
                    value = 1;
                }
            }
        }
        return total;
    }
}
