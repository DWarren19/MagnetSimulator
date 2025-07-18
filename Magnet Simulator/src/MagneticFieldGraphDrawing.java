import javax.swing.*;
import java.awt.*;

public class MagneticFieldGraphDrawing extends JPanel {
    private double[] data;
    private double increment;
    private double maximum;
    double minimum;
    public MagneticFieldGraphDrawing(double[] data, double increment, double maximum, double minimum) {
        super();
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.data = data;
        this.increment = increment;
        this.maximum = maximum;
        this.minimum = minimum;
    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 1; i < 20; i++) {
            g2.setColor(Color.lightGray);
            g2.drawLine(0, 25*i, 500, 25*i);
            g2.setColor(Color.black);
            String scale = String.valueOf((21-i)*(maximum-minimum)/21 + minimum);
            if (scale.contains("E")) {
                g2.drawString((scale + "    ").substring(0, 4) + scale.substring(scale.indexOf('E')), 5, 25 * i+5);
            } else {
                g2.drawString((scale + "    ").substring(0, 7), 5, 25 * i+5);
            }
        }
        for (int i = 0; i < data.length-1; i++) {
            g2.setColor(Color.lightGray);
            g2.drawLine((450*(i)/(data.length-1))+50, 0, (450*(i)/(data.length-1))+50, 500);
            g2.setColor(Color.black);
            g2.drawLine((450*i/(data.length-1))+50,500-(int)(500*(data[i]-minimum)/(maximum-minimum)), (450*(i+1)/(data.length-1))+50, 500-(int)(500*(data[i+1]-minimum)/(maximum-minimum)));
            g2.drawString((i*increment+"000").substring(0, 5), 450*i/(data.length-1)+30, 470);
        }
    }
}
