import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MagnetButton extends JButton implements ActionListener {
    private JLabel name;
    private JButton deleteButton;
    private JButton editButton;
    private JButton duplicateButton;
    private FileHandlerGUI previous;
    private String filename;
    public MagnetButton(String n, FileHandlerGUI p, String f) {
        super();
        previous = p;
        filename = f;
        addActionListener(this);
        setSize(450, 50);
        name = new JLabel(n);
        name.setBounds(10, 20, 100, 20);
        add(name);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(120, 15, 100, 20);
        deleteButton.addActionListener(this);
        add(deleteButton);

        editButton = new JButton("Edit");
        editButton.setBounds(220, 15, 100, 20);
        editButton.addActionListener(this);
        add(editButton);

        duplicateButton = new JButton("Duplicate");
        duplicateButton.setBounds(320, 15, 100, 20);
        duplicateButton.addActionListener(this);
        add(duplicateButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this){
            MagnetData data = FileHandler.readMagnetData(filename, name.getText());
            if (data != null){
                if (data.toString().charAt(0) == '0'){
                    double[] outputData = new double[4];
                    for (int i = 0; i < 4; i++) {
                        outputData[i] = data.getData()[i];
                    }
                    for (double d: outputData) {
                        System.out.println(d);
                    }
                    RoundMagnet magnet = new RoundMagnet(outputData[0], outputData[1], outputData[2], outputData[3], data.getData()[4], 100);
                    Diagram magnetDiagram = new Diagram(outputData, true);
                    SimulationGUI magnetDetails = new SimulationGUI(outputData, data.getData()[4], magnetDiagram, previous, magnet);
                    magnetDiagram.setMagnetData(outputData);
                    previous.setVisible(false);

                } else {
                    double[] outputData = new double[4];
                    for (int i = 0; i < 4; i++) {
                        outputData[i] = data.getData()[i];
                    }
                    for (double d: outputData) {
                        System.out.println(d);
                    }
                    RoundMagnet magnet = new RoundMagnet(outputData[0], outputData[1], outputData[2], outputData[3], data.getData()[5], 100, data.getData()[4]);
                    Diagram magnetDiagram = new Diagram(outputData, false);
                    SimulationGUI magnetDetails = new SimulationGUI(outputData, data.getData()[4], magnetDiagram, previous, magnet);
                    magnetDiagram.setMagnetData(outputData);
                    previous.setVisible(false);
                }
            }
        } else if (e.getSource() == deleteButton) {
            FileHandler.writeSpecificLine(filename, name.getText(), null);
            previous.reset(false);
            previous.loadData();
        } else if (e.getSource() == editButton) {
            MagnetData data = FileHandler.readMagnetData(filename, name.getText());
            if (data != null){
                editButton = null;
                if (data.toString().charAt(0) == '0'){
                    double[] outputData = new double[4];
                    for (int i = 0; i < 4; i++) {
                        outputData[i] = data.getData()[i];
                    }
                    for (double d: outputData) {
                        System.out.println(d);
                    }
                    GUI magnetDetails = new GUI(previous, outputData, data.getData()[4]);
                    previous.setVisible(false);
                } else {
                    double[] outputData = new double[5];
                    for (int i = 0; i < 5; i++) {
                        outputData[i] = data.getData()[i];
                    }
                    for (double d: outputData) {
                        System.out.println(d);
                    }
                    SquareMagnetGUI magnetDetails = new SquareMagnetGUI(previous, outputData, data.getData()[5]);
                    previous.setVisible(false);
                }
            }
        } else if (e.getSource() == duplicateButton) {
            MagnetData data = FileHandler.readMagnetData(filename, name.getText());
            if (data != null){
                double[] outputData = new double[4];
                for (int i = 0; i < 4; i++) {
                    outputData[i] = data.getData()[i];
                }
                for (double d: outputData) {
                    System.out.println(d);
                }
                MagnetDataWindow magnetDetails = new MagnetDataWindow(previous.getX(), previous.getY(), data.getData());
                previous.reset(false);
            }
        }
    }
}
