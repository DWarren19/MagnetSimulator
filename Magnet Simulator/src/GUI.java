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
    private JButton crossSectionDiagram;
    private JButton outerRadius;
    private JButton innerRadius;
    private JButton nextButton;
    private boolean perCm;
    private String[] inputData;
    private String density;
    private JLabel diagramLabel;

    public GUI(){
        density = "";
        inputData = new String[4];
        inputLabels = new JLabel[4];
        inputs = new JTextField[4];
        unitLabels = new JLabel[4];

        densityUnits = new JButton();
        densityUnits.setBounds(300, 60, 150, 50);
        densityUnits.setOpaque(false);
        densityUnits.setBackground(Color.lightGray);
        add(densityUnits);
        densityUnits.addActionListener(this);

        densityLabel = new JLabel();
        densityLabel.setBounds(350, 20, 100, 30);
        add(densityLabel);
        densityButtonLabel = new JLabel("switch to");
        densityButtonLabel.setBounds(350, 55, 100, 30);
        add(densityButtonLabel);

        densityInput = new JTextField();
        densityInput.setBounds(300, 10, 50, 50);
        densityInput.addKeyListener(this);
        add(densityInput);

        crossSectionDiagram = new JButton();
        crossSectionDiagram.setBounds(10, 250, 300, 200);
        crossSectionDiagram.setBackground(Color.lightGray);
        crossSectionDiagram.setOpaque(false);
        add(crossSectionDiagram);

        diagramLabel = new JLabel("Cross Section");
        diagramLabel.setBounds(100, 175, 300, 100);
        add(diagramLabel);

        innerRadius = new JButton();
        innerRadius.setBounds(30, 325, 30, 50);
        innerRadius.setBackground(Color.white);
        add(innerRadius);

        outerRadius = new JButton();
        outerRadius.setBounds(30, 300, 30, 100);
        outerRadius.setBackground(Color.lightGray);
        add(outerRadius);

        nextButton = new JButton("Next");
        nextButton.setBounds(350, 400, 100, 30);
        add(nextButton);
        nextButton.addActionListener(this);

        crossSectionDiagram = new JButton();
        crossSectionDiagram.setBounds(10, 250, 300, 200);
        crossSectionDiagram.setBackground(Color.white);
        add(crossSectionDiagram);

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
        if (e.getSource() == nextButton){

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == densityInput) {
            density += e.getKeyChar();
            System.out.println(density);
        } else if(e.getSource() == inputs[0]) {
            innerRadius.setBounds(30,350-inputs[3].getText(), 500/inputs[0].getText(), );
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
