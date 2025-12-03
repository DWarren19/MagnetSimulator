import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MagneticFieldGraph2d extends JFrame implements ActionListener {//a graph of magnetic field strength against distance
    private JFrame previous;
    private JButton back;
    private MagneticFieldGraphDrawing drawing;
    private JLabel direction;
    public MagneticFieldGraph2d(JFrame p, double[][] data, double increment, double maximum, double minimum, boolean axial) {
        previous = p;
        setBounds(previous.getBounds());
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        double[] outputData = data[1];

        drawing = new MagneticFieldGraphDrawing(outputData, increment, maximum, minimum, true);
        drawing.setBounds(0, 0, 500, 480);
        add(drawing);

        back = new JButton("Back");
        back.setBounds(0, 480, 100, 30);
        back.addActionListener(this);
        add(back);

        if (axial) {
            direction = new JLabel("axial");
        } else {
            direction = new JLabel("radial");
        }
        direction.setBounds(150, 490, 100, 20);
        add(direction);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            previous.setVisible(true);
            previous.setBounds(getBounds());
            dispose();
        }
    }
}
