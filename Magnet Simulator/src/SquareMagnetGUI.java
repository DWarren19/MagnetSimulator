import javax.swing.*;
import java.awt.event.KeyEvent;

public class SquareMagnetGUI extends GUI {
    private SquareMagnetDiagram diagram;
    public SquareMagnetGUI(){
        super(new String[]{"Length (L)", "Split Length (S)", "Inner Length (IL)", "Outer Length (OL)", "Inner Radius (IR)"}, false);
        diagram = new SquareMagnetDiagram(1, 1, 0.5);
        super.add(diagram);
        JLabel diagramLabel = new JLabel("Side View");
        diagramLabel.setBounds(350, 350, 100, 20);
        super.add(diagramLabel);
    }
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        diagram.setData(super.inputData[2], super.inputData[3], super.inputData[4]);
    }
}
