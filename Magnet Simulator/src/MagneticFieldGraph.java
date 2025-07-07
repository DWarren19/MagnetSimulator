import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MagneticFieldGraph extends JFrame implements ActionListener {
    private JFrame previous;
    private JButton back;
    private MagneticFieldGraphDrawing drawing;
    public MagneticFieldGraph(JFrame p, double[] data, double increment, double maximum) {
        previous = p;
        setSize(500, 550);
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        drawing = new MagneticFieldGraphDrawing(data, increment, maximum);
        drawing.setBounds(0, 0, 500, 480);
        add(drawing);

        back = new JButton("Back");
        back.setBounds(0, 480, 100, 30);
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        previous.setVisible(true);
        previous.setBounds(getBounds());
        dispose();
    }
}
