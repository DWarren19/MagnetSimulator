import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FileHandlerGUI extends JFrame implements ActionListener, KeyListener {
    private MagnetButton[] buttons;
    private JTextField fileName;
    private JLabel fileLabel;
    private JButton back;
    private LoginGUI previous;
    public FileHandlerGUI(LoginGUI p){
        setBounds(p.getBounds());
        setTitle("Magnet Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        fileLabel = new JLabel("File Name:");
        fileLabel.setBounds(10, 10, 100, 20);
        add(fileLabel);

        fileName = new JTextField();
        fileName.setBounds(10, 40, 100, 20);
        fileName.addKeyListener(this);
        add(fileName);

        back = new JButton("Back");
        back.setBounds(150, 10, 100, 50);
        back.addActionListener(this);
        add(back);

        previous = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        previous.setVisible(true);
        dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == '\n'){
            System.out.println(fileName.getText());
            String[] magnetNames = FileHandler.readMagnetArray(fileName.getText());
            if(magnetNames != null) {
                System.out.println(fileName.getText());
                buttons = new MagnetButton[magnetNames.length];
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i] = new MagnetButton(magnetNames[i], this, fileName.getText());
                    buttons[i].setBounds(10, 60*i+70, buttons[i].getWidth(), buttons[i].getHeight());
                    add(buttons[i]);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
