import javax.swing.*;
import java.awt.*;

public class MagneticFieldGraphDrawing extends JPanel {
    private double[] data;
    private double increment;
    private double maximum;
    public MagneticFieldGraphDrawing(double[] data, double increment, double maximum) {
        super();
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.data = data;
        this.increment = increment;
        this.maximum = maximum;
    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        for (int i = 0; i < data.length-1; i++) {
            g2.drawLine((int)(500*i/(data.length-1)),(int)(500*data[i]/maximum), (int)(500*(i+1)/(data.length-1)), (int)(500*data[i+1]/maximum));
        }
    }
}
