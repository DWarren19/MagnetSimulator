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
    private boolean stopSimulation2d;
    private double diameter;
    private double resolution;

    private JButton axial;
    private JButton radial;

    private double highest;
    private double lowest;
    private double[][][] magneticFieldData;
    private double[][] magneticFieldData2d;
    private double x;
    private double y;
    private double z;
    private JProgressBar progress;

    private boolean stopSimulation;//two variables are used to stop the loop so that it can be paused
    private JLabel progressLabel;
    private JLabel progressLabel2;

    private boolean axialData;

    public SimulationGUI(double[] d, double density, Diagram m, JFrame p, RoundMagnet r) {
        data = new double[d.length + 1];
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
        stopSimulation2d = true;
        stopSimulation = true;
        magnet = r;

        progress = new JProgressBar(0, 100);
        progress.setBounds(10, 125, 300, 25);
        add(progress);

        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new JTextField();
            inputs[i].setBounds(10, 10 + 50 * i, 150, 50);
            inputs[i].setOpaque(false);
            inputs[i].addKeyListener(this);
            add(inputs[i]);
            inputLabels[i] = new JLabel(labelNames[i]);
            inputLabels[i].setBounds(100, 10 + 50 * i, 400, 50);
            add(inputLabels[i]);
        }
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            buttons[i].setBounds(360, 10 + 60 * i, 100, 50);
            buttons[i].setBackground(Color.lightGray);
            buttons[i].setOpaque(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
        buttons[1].addMouseListener(this);

        axial = new JButton("Run Axial");
        axial.setBounds(10, 175, 100, 50);
        axial.setBackground(Color.lightGray);
        axial.setOpaque(false);
        axial.addActionListener(this);
        add(axial);
        radial = new JButton("Run Radial");
        radial.setBounds(120, 175, 100, 50);
        radial.setBackground(Color.lightGray);
        radial.setOpaque(false);
        radial.addActionListener(this);
        add(radial);

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
        progressLabel2.setBounds(10, 150, 400, 15);
        add(progressLabel2);
    }

    public void simulateMagnet() {
        while (!stop) {
            double s = VectorHandler.toVector(magnet.getStrength(x, y, z));
            if (s > highest) {
                highest = s;
            } else if (s < lowest) {
                lowest = s;
            }
            magneticFieldData[(int) (x * resolution * 1.01)][(int) (y * resolution * 1.01)][(int) (z * resolution * 1.01)] = s;
            progress.setValue((int) (100 * ((z + y / (resolution * diameter / 2)) / (diameter / 2))));
            x += 1 / resolution;
            x *= resolution;
            x = Math.round(x);
            x /= resolution;
            if (x > diameter / 2) {
                x = 0;
                y += 1 / resolution;
                y *= resolution;
                y = Math.round(y);
                y /= resolution;
                if (y > diameter / 2) {
                    y = 0;
                    z += 1 / resolution;
                    z *= resolution;
                    z = Math.round(z);
                    z /= resolution;
                    if (z > diameter / 2) {
                        stop = true;
                        stopSimulation = true;
                        progress.setValue(0);
                        homogeneity.setText(((float) (int) (10000 * (highest - lowest) / (highest))) / 100 + "  %");
                        magneticFieldData2d = null;
                    }
                }
            }
            progress.update(progress.getGraphics());
            if (getMousePosition() != null) {
                if (getMousePosition().x > 360 && getMousePosition().x < 460 && getMousePosition().y > 100 && getMousePosition().y < 150) {
                    stop = true;
                }
            }
        }
    }

    public void simulateMagnet2d(boolean axial){
        while (!stop) {
            double s;
            if(axial) {
                s = VectorHandler.toVector(magnet.getStrength(x, 0, y));
            } else {
                s = VectorHandler.toVector(magnet.getStrength(x, y, 0));
            }
            if (s > highest) {
                highest = s;
            } else if (s < lowest) {
                lowest = s;
            }
            magneticFieldData2d[(int) (x * resolution * 1.01)][(int) (y * resolution * 1.01)] = s;//values are multiplied by 1.01 to avoid rounding errors
            progress.setValue((int) (100 * ((y + x / (resolution * diameter / 2)) / (diameter / 2))));
            x += 1 / resolution;
            x *= resolution;
            x = Math.round(x);
            x /= resolution;
            if (x > diameter / 2) {
                x = 0;
                y += 1 / resolution;
                y *= resolution;
                y = Math.round(y);
                y /= resolution;
                if (y > diameter / 2) {
                    stop = true;
                    stopSimulation2d = true;
                    progress.setValue(0);
                    homogeneity.setText(((float) (int) (10000 * (highest - lowest) / (highest))) / 100 + "  %");
                    magneticFieldData = null;
                }
            }
            progress.update(progress.getGraphics());
            if (getMousePosition() != null) {
                if (getMousePosition().x > 360 && getMousePosition().x < 460 && getMousePosition().y > 100 && getMousePosition().y < 150) {
                    stop = true;
                }
            }
        }
    }
    public void prepareMagnet() {
        if (resolution / (diameter / 2) != (int) (resolution / (diameter / 2))) {
            resolution /= diameter / 2;
            resolution = (int) resolution;
            resolution *= diameter / 2;
        }
        highest = VectorHandler.toVector(magnet.getStrength(0, 0, 0));
        double outputValue = highest;
        int orderOfMagnitudeInt = 2;
        while (outputValue > 1000) {
            outputValue /= 10;
            orderOfMagnitudeInt += 1;
        }
        while (outputValue < 100 && outputValue != 0) {
            outputValue *= 10;
            orderOfMagnitudeInt -= 1;
        }
        outputValue = Math.round(outputValue);
        outputValue /= 100;
        magneticField.setText(String.valueOf(outputValue));
        orderOfMagnitude.setText(String.valueOf(orderOfMagnitudeInt));
        lowest = highest;
        x = 0;
        y = 0;
        z = 0;
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
            magnetDiagram.setDiameter(diameter);
        } else if (e.getSource() == inputs[1]){
            resolution = GUI.strToInt(inputs[1].getText());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttons[0]) {
            prepareMagnet();
            if (diameter != 0 && resolution != 0) {
                stop = false;
                stopSimulation = false;
                int size = (int) Math.ceil(diameter * resolution / 2);
                if (size == diameter * resolution / 2) {
                    size += 1;
                }
                magneticFieldData = new double[size][size][size];
                simulateMagnet();
            }
            //LoadingScreen loading = new LoadingScreen(getX(), getY(), (int)Math.pow((resolution*diameter)+1, 3)/2);
            //while (!stop){//the value for homogeneity here will be a slight underestimate, since the area is a cube, not a sphere

            //}
            //loading.dispose();
            //homogeneity.setText(2*(highest-lowest)/(highest+lowest));
        } else if (e.getSource() == axial){
            prepareMagnet();
            if (diameter != 0 && resolution != 0) {
                stop = false;
                stopSimulation2d = false;
                int size = (int) Math.ceil(diameter * resolution / 2);
                if (size == diameter * resolution / 2) {
                    size += 1;
                }
                axialData = true;
                magneticFieldData2d = new double[size][size];
                simulateMagnet2d(true);
            }
        } else if (e.getSource() == radial){
            prepareMagnet();
            if (diameter != 0 && resolution != 0) {
                stop = false;
                stopSimulation2d = false;
                int size = (int) Math.ceil(diameter * resolution / 2);
                if (size == diameter * resolution / 2) {
                    size += 1;
                }
                axialData = false;
                magneticFieldData2d = new double[size][size];
                simulateMagnet2d(false);
            }
        } else if (e.getSource() == buttons[1]){
            stopSimulation = true;
            progress.setValue(0);
            magneticFieldData = null;
        } else if (e.getSource() == buttons[2]){
            previous.setVisible(true);
            magnetDiagram.setDiameter(0);
            if(previous.getClass() == GUI.class) {
                previous.add(magnetDiagram);
            } else if (previous.getClass() == SquareMagnetGUI.class){
                previous.add(magnetDiagram);
            } else if (previous.getClass() == FileHandlerGUI.class){
                FileHandlerGUI previousGUI = (FileHandlerGUI) previous;
                previousGUI.reset(true);
            }
            previous.setBounds(getBounds());
            dispose();
        } else if (e.getSource() == buttons[3]){
            if(magneticFieldData != null) {
                OutputDataWindow outputWindow = new OutputDataWindow(getX(), getY(), magneticFieldData);
            } else if (magneticFieldData2d != null){
                OutputDataWindow2d outputWindow = new OutputDataWindow2d(getX(), getY(), magneticFieldData2d);
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
            if(magneticFieldData != null) {
                MagneticFieldGraph graph1 = new MagneticFieldGraph(this, magneticFieldData, 1 / resolution * Math.sqrt(3), highest, lowest);
                setVisible(false);
            } else if (magneticFieldData2d != null) {
                MagneticFieldGraph2d graph1 = new MagneticFieldGraph2d(this, magneticFieldData2d, 1/resolution, highest, lowest, axialData);
            }
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
        } else if (!stopSimulation2d){
            stop = false;
            simulateMagnet2d(axialData);
        }
    }
}
