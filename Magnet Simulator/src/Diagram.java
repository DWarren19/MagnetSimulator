import javax.swing.*;
import java.awt.*;

public class Diagram extends JPanel {
    public double[] data;
    private JLabel outerRadius = new JLabel();
    public Diagram(double[] d){
        super();
        setBorder(BorderFactory.createLineBorder(Color.black));
        data = d;
    }
    public void setMagnetData(double[] d){
        Graphics g = super.getGraphics();
        data = d;
        if (data[0]==0){
            data[0] = 1;
        }
        d[1] /= data[0];
        d[1] *= 140;
        d[2] /= data[0];
        d[2] *= 140;
        if(d[2]>95){
            d[2]=95;
        }
        int magnetWidth = 280;
        d[3] /= data[0];
        if(d[3]>0.66){
            magnetWidth *= (0.66/d[3]);
            for (int i = 0; i < d.length; i++) {
                d[i] *= (0.66/d[3]);
            }
        }
        d[3] *= 140;
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(1, 1, super.getWidth()-2, super.getHeight()-2);
        g2.setColor(Color.black);
        g2.fillRect(160-magnetWidth/2, 100-(int)d[3], (int)(magnetWidth/2-d[1]), (int)d[3]*2);
        g2.fillRect(160-magnetWidth/2-14, 120-(int)d[3], 8, (int)d[3]-20);
        g2.fillPolygon(new int[]{160-magnetWidth/2-19, 160-magnetWidth/2-1, 160-magnetWidth/2-10}, new int[]{120-(int)d[3], 120-(int)d[3], 100-(int)d[3]}, 3);
        g2.setColor(Color.lightGray);
        g2.fillRect(160-magnetWidth/2, 100-(int)d[2], (int)(magnetWidth/2-d[1]), (int)d[2]*2);
        g2.setColor(Color.black);
        g2.fillRect(160+(int)(d[1]), 100-(int)d[3], (int)(magnetWidth/2-d[1]), (int)d[3]*2);
        g2.setColor(Color.lightGray);
        g2.fillRect(160+(int)(d[1]), 100-(int)d[2], (int)(magnetWidth/2-d[1]), (int)d[2]*2);
    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(10, 100-(int)data[3], 30, (int)data[3]*2);
    }
}
