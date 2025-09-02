import javax.swing.*;
import java.awt.event.KeyEvent;

public class SquareMagnetGUI extends GUI {
    private SquareMagnetDiagram diagram;
    public SquareMagnetGUI(JFrame p){
        super(new String[]{"Length (L)", "Split Length (S)", "Inner Length (IL)", "Outer Length (OL)", "Inner Radius (IR)"}, false, p);
        diagram = new SquareMagnetDiagram(super.inputData[2], super.inputData[3], super.inputData[4]);
        super.add(diagram);
        diagram.setData(super.inputData[2], super.inputData[3], super.inputData[4]);
        JLabel diagramLabel = new JLabel("Side View");
        diagramLabel.setBounds(350, 350, 100, 20);
        super.add(diagramLabel);
    }
    public SquareMagnetGUI(JFrame p, double[] data, double density){
        super(new String[]{"Length (L)", "Split Length (S)", "Inner Length (IL)", "Outer Length (OL)", "Inner Radius (IR)"}, false, p, data, density);
        diagram = new SquareMagnetDiagram(super.inputData[2], super.inputData[3], super.inputData[4]);
        super.add(diagram);
        diagram.setData(super.inputData[2], super.inputData[3], super.inputData[4]);
        JLabel diagramLabel = new JLabel("Side View");
        diagramLabel.setBounds(350, 350, 100, 20);
        super.add(diagramLabel);
    }
    public void keyReleased(KeyEvent e) {//this has to be different because measurements are in length, not radius
        super.keyReleased(e);
        diagram.setData(super.inputData[2], super.inputData[3], super.inputData[4]);
    }
}
