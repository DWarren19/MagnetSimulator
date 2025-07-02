import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MagneticFieldGraph extends JFrame implements ActionListener {
    private JFrame previous;
    private JButton back;
    public MagneticFieldGraph(JFrame p, double[] data, double increment, double maximum) {
        previous = p;
        setSize(500, 550);
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        MagneticFieldGraphDrawing drawing = new MagneticFieldGraphDrawing(data, increment, maximum);
        drawing.setBounds(0, 0, 500, 500);

        back = new JButton("Back");
        back.setBounds(0, 500, 100, 50);
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
