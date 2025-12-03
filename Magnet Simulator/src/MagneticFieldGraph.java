import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MagneticFieldGraph extends JFrame implements ActionListener {//a graph of magnetic field strength against distance
    private JFrame previous;
    private JButton back;
    private JButton axial;
    private JButton radial;
    private JButton diagonal;
    private MagneticFieldGraphDrawing drawing;
    private double[][][] data;
    private double increment;
    private double maximum;
    private double minimum;
    public MagneticFieldGraph(JFrame p, double[][][] data, double increment, double maximum, double minimum) {
        previous = p;
        setBounds(previous.getBounds());
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        this.data = data;
        this.increment = increment;
        this.maximum = maximum;
        this.minimum = minimum;

        double[] outputData = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            outputData[i] = data[i][i][i];
        }

        drawing = new MagneticFieldGraphDrawing(outputData, increment, maximum, minimum, true);
        drawing.setBounds(0, 0, 500, 480);
        add(drawing);

        back = new JButton("Back");
        back.setBounds(0, 480, 100, 30);
        back.addActionListener(this);
        add(back);

        radial = new JButton("Radial");
        radial.setBounds(100, 480, 100, 30);
        radial.addActionListener(this);
        add(radial);

        axial = new JButton("Axial");
        axial.setBounds(200, 480, 100, 30);
        axial.addActionListener(this);
        add(axial);

        diagonal = new JButton("Diagonal");
        diagonal.setBounds(300, 480, 100, 30);
        diagonal.addActionListener(this);
        add(diagonal);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            previous.setVisible(true);
            previous.setBounds(getBounds());
            dispose();
        } else if (e.getSource() == diagonal) {//distance is between the center and the corner
            double[] outputData = new double[data.length];
            for (int i = 0; i < data.length; i++) {
                outputData[i] = data[i][i][i];
            }
            remove(drawing);
            drawing = new MagneticFieldGraphDrawing(outputData, increment, maximum, minimum, true);
            drawing.setBounds(0, 0, 500, 480);
            add(drawing);
            update(getGraphics());
        } else if (e.getSource() == radial) {
            double[] outputData = new double[data.length];
            for (int i = 0; i < data.length; i++) {
                outputData[i] = data[i][1][1];
            }
            remove(drawing);
            drawing = new MagneticFieldGraphDrawing(outputData, increment, maximum, minimum, false);
            drawing.setBounds(0, 0, 500, 480);
            add(drawing);
            update(getGraphics());
        }else if (e.getSource() == axial) {
            double[] outputData = new double[data.length];
            for (int i = 0; i < data.length; i++) {
                outputData[i] = data[1][1][i];
            }
            remove(drawing);
            drawing = new MagneticFieldGraphDrawing(outputData, increment, maximum, minimum, false);
            drawing.setBounds(0, 0, 500, 480);
            add(drawing);
            update(getGraphics());
        }
    }
}
