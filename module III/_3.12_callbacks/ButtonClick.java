import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ButtonClick {
    public static void main(String[] args) {
        JFrame frame = new JFrame("VGU College Result");
        JLabel label = new JLabel("Click to see Hita's result");
        JButton button = new JButton("Show Result");

        // callback: we give the button a listener. Swing calls actionPerformed() WHEN it is clicked.
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Hita: 438 / 500, PASS");
            }
        });
        // lambda version: button.addActionListener(e -> label.setText("Hita: 438 / 500, PASS"));

        frame.setLayout(new FlowLayout());
        frame.add(label);
        frame.add(button);
        frame.setSize(320, 120);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
