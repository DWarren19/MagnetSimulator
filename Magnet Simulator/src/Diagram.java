import javax.swing.*;
import java.awt.*;

public class Diagram extends JPanel {
    public double[] data;
    public int magnetWidth;
    public double[] outputData;
    public JLabel[] outputLabels;
    public Diagram(double[] d){
        super();
        setBorder(BorderFactory.createLineBorder(Color.black));
        data = d;
        this.outputData = d;
        setBounds(10, 250, 330, 250);
        outputLabels = new JLabel[4];
        String[] labelData = {"IR", "OR", "L", "S"};
        for (int i = 0; i < 4; i++) {
            outputLabels[i] = new JLabel(labelData[i]);
            add(outputLabels[i]);
        }
    }
    public void setMagnetData(double[] d) {
        Graphics g = super.getGraphics();
        d = d.clone();
        data = d;
        this.outputData = d;
        if (data[0] == 0) {
            data[0] = 1;
        }
        d[1] /= data[0];
        d[1] *= 140;
        d[2] /= data[0];
        d[2] *= 140;
        if (d[2] > 95) {
            d[2] = 95;
        }
        magnetWidth = 280;
        d[3] /= data[0];
        if (d[3] > 0.66) {
            magnetWidth *= (0.66 / d[3]);
            for (int i = 0; i < d.length; i++) {
                d[i] *= (0.66 / d[3]);
            }
        }
        d[3] *= 140;
        repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(1, 1, super.getWidth()-2, super.getHeight()-2);
        g2.setColor(Color.black);
        g2.fillRect(160-magnetWidth/2, 100-(int) outputData[3], (int)(magnetWidth/2- outputData[1]), (int) outputData[3]*2);
        g2.setColor(Color.lightGray);
        g2.fillRect(160-magnetWidth/2, 100-(int) outputData[2], (int)(magnetWidth/2- outputData[1]), (int) outputData[2]*2);
        g2.setColor(Color.black);
        g2.fillRect(160+(int)(outputData[1]), 100-(int) outputData[3], (int)(magnetWidth/2- outputData[1]), (int) outputData[3]*2);
        g2.setColor(Color.lightGray);
        g2.fillRect(160+(int)(outputData[1]), 100-(int) outputData[2], (int)(magnetWidth/2- outputData[1]), (int) outputData[2]*2);

        g2.fillRect(160+(magnetWidth/2)+6, 120-(int) outputData[2], 8, (int) outputData[2]-20);
        g2.fillPolygon(new int[]{160+(magnetWidth/2)+1, 160+(magnetWidth/2)+19, 160+(magnetWidth/2)+10}, new int[]{120-(int) outputData[2], 120-(int) outputData[2], 100-(int) outputData[2]}, 3);
        g2.fillRect(160-magnetWidth/2-14, 120-(int) outputData[3], 8, (int) outputData[3]-20);
        g2.fillPolygon(new int[]{160-magnetWidth/2-19, 160-magnetWidth/2-1, 160-magnetWidth/2-10}, new int[]{120-(int) outputData[3], 120-(int) outputData[3], 100-(int) outputData[3]}, 3);
        outputLabels[0].setBounds(160+(magnetWidth/2)+5, 100-(int) outputData[2], 20, 20);
        outputLabels[1].setBounds(160-(magnetWidth/2)-19, 100-(int) outputData[3], 20, 20);

        g2.fillRect(180-magnetWidth/2, 225, magnetWidth-40, 10);
        g2.fillPolygon(new int[]{160-magnetWidth/2, 180-magnetWidth/2, 180-magnetWidth/2}, new int[]{230, 220, 240}, 3);
        g2.fillPolygon(new int[]{160+magnetWidth/2, 140+magnetWidth/2, 140+magnetWidth/2}, new int[]{230, 220, 240}, 3);
        outputLabels[2].setBounds(160, 220, 20, 20);
        g2.fillRect(180-(int)outputData[1], 200, (int)outputData[1]*2-40, 10);
        g2.fillPolygon(new int[]{160-(int)outputData[1], 180-(int)outputData[1], 180-(int)outputData[1]}, new int[]{205, 195, 215}, 3);
        g2.fillPolygon(new int[]{160+(int)outputData[1], 140+(int)outputData[1], 140+(int)outputData[1]}, new int[]{205, 195, 215}, 3);
        outputLabels[3].setBounds(160, 195, 20, 20);
    }
}
