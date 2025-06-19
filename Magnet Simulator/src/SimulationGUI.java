import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimulationGUI extends JFrame implements KeyListener, ActionListener, MouseListener {
    private double[] data;
    private RoundMagnet magnet;
    private Diagram magnetDiagram;
    private JTextField[] inputs;
    private JLabel[] inputLabels;
    private String[] labelNames;
    private JButton[] buttons;
    private String[] buttonNames;
    private JLabel buttonLabel;
    private JFrame previous;
    private JLabel magneticFieldLabel;
    private JLabel homogeneityLabel;
    private JLabel magneticFieldUnits;
    private JLabel orderOfMagnitude;
    private JLabel homogeneity;
    private JLabel magneticField;
    private boolean stop;
    private double diameter;
    private double resolution;

    private double highest;
    private double lowest;
    private double[][][] magneticFieldData;
    private double x;
    private double y;
    private double z;
    private JProgressBar progress;

    private boolean stopSimulation;//two variables are used to stop the loop so that it can be paused
    private JLabel progressLabel;
    private JLabel progressLabel2;

    public SimulationGUI(double[] d, double density, Diagram m, JFrame p, RoundMagnet r){
        data = new double[d.length+1];
        for (int i = 0; i < d.length; i++) {
            data[i] = d[i];
        }
        data[d.length] = density;
        magnetDiagram = m;
        previous = p;
        inputs = new JTextField[2];
        inputLabels = new JLabel[2];
        buttons = new JButton[5];
        labelNames = new String[]{"cm               Diameter of Simulation Volume", "Points/cm   Resolution"};
        buttonNames = new String[]{"Run", "Stop", "Back", "Save", "Draw"};
        setBounds(previous.getBounds());
        add(magnetDiagram);
        stop = true;
        stopSimulation = true;
        magnet = r;

        progress = new JProgressBar(0, 100);
        progress.setBounds(10, 125, 300, 100);
        add(progress);

        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new JTextField();
            inputs[i].setBounds(10, 10+50*i, 150, 50);
            inputs[i].setOpaque(false);
            inputs[i].addKeyListener(this);
            add(inputs[i]);
            inputLabels[i] = new JLabel(labelNames[i]);
            inputLabels[i].setBounds(100, 10+50*i, 400, 50);
            add(inputLabels[i]);
        }
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            buttons[i].setBounds(360, 10+60*i, 100, 50);
            buttons[i].setBackground(Color.lightGray);
            buttons[i].setOpaque(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
        buttons[1].addMouseListener(this);

        buttonLabel = new JLabel("Graph");
        buttonLabel.setBounds(393, 280, 100, 15);
        add(buttonLabel);

        homogeneityLabel = new JLabel("Homogeneity");
        homogeneityLabel.setBounds(360, 430, 100, 15);
        add(homogeneityLabel);

        magneticFieldLabel = new JLabel("Central Magnetic Field");
        magneticFieldLabel.setBounds(350, 330, 150, 15);
        add(magneticFieldLabel);

        magneticFieldUnits = new JLabel("X10   T/A");
        magneticFieldUnits.setBounds(400, 380, 50, 15);
        add(magneticFieldUnits);

        orderOfMagnitude = new JLabel("0");
        orderOfMagnitude.setBounds(420, 378, 10, 10);
        orderOfMagnitude.setFont(orderOfMagnitude.getFont().deriveFont(8F));
        add(orderOfMagnitude);

        homogeneity = new JLabel("    %");
        homogeneity.setBounds(400, 460, 50, 15);
        add(homogeneity);

        magneticField = new JLabel("");
        magneticField.setBounds(370, 380, 50, 15);
        add(magneticField);

        progressLabel = new JLabel("press 'Run' to simulate the magnet");
        progressLabel.setBounds(10, 110, 400, 15);
        add(progressLabel);
        progressLabel2 = new JLabel("cursor over 'Stop' to pause, press 'Stop' to stop");
        progressLabel2.setBounds(10, 225, 400, 15);
        add(progressLabel2);
    }
    public void simulateMagnet(){
        while (!stop){
            double s = VectorHandler.toVector(magnet.getStrength(x, y, z));
            if (s > highest){
                highest = s;
            } else if (s < lowest){
                lowest = s;
            }
            magneticFieldData[(int) (x * resolution)][(int) (y * resolution)][(int) (z * resolution)] = s;
            progress.setValue((int)(100*((z+y/(resolution*diameter/2))/(diameter/2))));
            x += 1/resolution;
            if (x > diameter/2){
                x = 0;
                y += 1/resolution;
                if (y > diameter/2){
                    y = 0;
                    z += 1/resolution;
                    if (z > diameter/2){
                        stop = true;
                        stopSimulation = true;
                        progress.setValue(0);
                        homogeneity.setText(((float)(int)(10000 * (highest - lowest) / (highest)))/100 +"  %");
                    }
                }
            }
            progress.update(progress.getGraphics());
            if(getMousePosition()!=null) {
                if (getMousePosition().x > 360 && getMousePosition().x < 460 && getMousePosition().y > 100 && getMousePosition().y < 150) {
                    stop = true;
                }
            }
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
        if(e.getSource() == inputs[0]) {
            diameter = GUI.strToInt(inputs[0].getText());
            magnetDiagram.setDiameter((int)diameter);
        } else if (e.getSource() == inputs[1]){
            resolution = GUI.strToInt(inputs[1].getText());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttons[0]){
            if (resolution/2 != (int)resolution/2){
                resolution+=1;
            }
            stop = false;
            stopSimulation = false;
            highest = VectorHandler.toVector(magnet.getStrength(0, 0, 0));
            double outputValue = highest;
            int orderOfMagnitudeInt = 2;
            while (outputValue>1000){
                outputValue/=10;
                orderOfMagnitudeInt+=1;
            }
            while (outputValue<100){
                outputValue*=10;
                orderOfMagnitudeInt-=1;
            }
            outputValue = Math.round(outputValue);
            outputValue/=100;
            magneticField.setText(String.valueOf(outputValue));
            orderOfMagnitude.setText(String.valueOf(orderOfMagnitudeInt));
            lowest = highest;
            x = 0;
            y = 0;
            z = 0;
            if (diameter != 0 && resolution != 0) {
                magneticFieldData = new double[(int)(diameter*resolution)/2+1][(int)(diameter*resolution)/2+1][(int)(diameter*resolution)/2+1];
                simulateMagnet();
            }
            //LoadingScreen loading = new LoadingScreen(getX(), getY(), (int)Math.pow((resolution*diameter)+1, 3)/2);
            //while (!stop){//the value for homogeneity here will be a slight underestimate, since the area is a cube, not a sphere

            //}
            //loading.dispose();
            //homogeneity.setText(2*(highest-lowest)/(highest+lowest));
        } else if (e.getSource() == buttons[1]){
            stopSimulation = true;
            progress.setValue(0);
            magneticFieldData = null;
        } else if (e.getSource() == buttons[2]){
            previous.setVisible(true);
            magnetDiagram.setDiameter(0);
            previous.add(magnetDiagram);
            previous.setBounds(getBounds());
            dispose();
        } else if (e.getSource() == buttons[3]){
            if(magneticFieldData != null) {
                OutputDataWindow outputWindow = new OutputDataWindow(getX(), getY(), magneticFieldData);
            } else {
                JFrame outputWindow = new JFrame("Magnet Simulator");
                outputWindow.setBounds(getX(), getY(), 300, 100);
                outputWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                outputWindow.setLayout(null);
                outputWindow.setVisible(true);
                JLabel outputText = new JLabel("please press 'Run' to generate magnetic field data");
                outputText.setBounds(0, 0, 300, 50);
                outputWindow.add(outputText);
            }
        } else if (e.getSource() == buttons[4]){

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!stopSimulation){
            stop = false;
            simulateMagnet();
        }
    }
}
