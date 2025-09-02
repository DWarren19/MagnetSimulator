import javax.swing.*;
import java.awt.*;
public class SquareMagnetDiagram extends JPanel {//a side view of the magnet to check the inner radius
    private double innerLength;
    private double outerLength;
    private double innerRadius;
    private int outputDiameter;
    private int offset;
    private int innerDiameter;
    public SquareMagnetDiagram(double i, double o, double r){
        super();
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBounds(345, 250, 100, 100);
        innerLength = i;
        outerLength = o;
        innerRadius = r;
        outputDiameter = (int)((innerRadius*2+outerLength-innerLength)*80/outerLength);
        offset = (int)((10+(outerLength-innerLength)/2)*80/outerLength);
        innerDiameter = (int)((innerRadius*2)*80/outerLength);
        repaint();
    }
    public void setData(double i, double o, double r){
        innerLength = i;
        outerLength = o;
        innerRadius = r;
        outputDiameter = (int)((innerRadius*2+outerLength-innerLength)*80/outerLength);
        offset = (int)(10+((outerLength-innerLength)/2)*80/outerLength);
        innerDiameter = (int)((innerRadius*2)*80/outerLength);
        repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(1, 1, 98, 98);
        g2.setColor(Color.black);
        g2.fillOval(10, 10, outputDiameter, outputDiameter);
        g2.fillOval(90-outputDiameter, 90-outputDiameter, outputDiameter, outputDiameter);
        g2.fillOval(90-outputDiameter, 10, outputDiameter, outputDiameter);
        g2.fillOval(10, 90-outputDiameter, outputDiameter, outputDiameter);
        g2.fillRect(outputDiameter/2+10, 10, 80-outputDiameter, 80);
        g2.fillRect(10, outputDiameter/2+10, 80, 80-outputDiameter);
        g2.setColor(Color.white);
        g2.fillOval(offset, offset, innerDiameter, innerDiameter);
        g2.fillOval(100-offset-innerDiameter, 100-offset-innerDiameter, innerDiameter, innerDiameter);
        g2.fillOval(100-offset-innerDiameter, offset, innerDiameter, innerDiameter);
        g2.fillOval(offset, 100-offset-innerDiameter, innerDiameter, innerDiameter);
        g2.fillRect(innerDiameter/2+offset, offset, 100-(offset+innerDiameter/2)*2, 100-offset*2);
        g2.fillRect(offset, innerDiameter/2+offset, 100-offset*2, 100-(offset+innerDiameter/2)*2);
    }
}
