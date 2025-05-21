import javax.swing.*;
import java.awt.*;

public class Diagram extends JPanel {
    public int[] data;
    public Diagram(int[] d){
        super();
        setBorder(BorderFactory.createLineBorder(Color.black));
        setMagnetData(d);
    }
    public void setMagnetData(int[] d){
        data = d;
    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fillRect(10, 100-data[3], 30, data[3]*2);
    }
}
