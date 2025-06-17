import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener {
    private JButton round;
    private JButton square;
    private JButton saved;
    public LoginGUI(){
        setSize(500, 550);
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        round = new JButton("round coil");
        round.setBounds(100, 75, 300, 100);
        round.addActionListener(this);
        add(round);

        square = new JButton("square coil");
        square.setBounds(100, 225, 300, 100);
        square.addActionListener(this);
        add(square);

        saved = new JButton("saved magnets");
        saved.setBounds(100, 375, 300, 100);
        saved.addActionListener(this);
        add(saved);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == round){
            GUI magnetDetails = new GUI(this);
            setVisible(false);
        } else if (e.getSource() == square){
            SquareMagnetGUI magnetDetails = new SquareMagnetGUI(this);
            setVisible(false);
        } else if (e.getSource() == saved){
            FileHandlerGUI magnetDetails = new FileHandlerGUI(this);
            setVisible(false);
        }
    }
}
