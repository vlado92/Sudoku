package sudoku;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Username
 */
public class Pomoc extends JFrame {

    private JLabel ime = new JLabel();
    private JLabel proizvodnja = new JLabel();

    public Pomoc() throws HeadlessException {
        this.setTitle("Pomoć");
        this.setSize(300, 300);
        //this.setResizable(false);
        String noviRed = "nesto\n drugo";
        System.out.println(noviRed);
        ime.setText("Napravio: Vladimir Kunarac");
        proizvodnja.setText("Poslednja izmjena: 10.5.2015. 17:33");
        ime.setBounds(0, 0, 200, 20);
        proizvodnja.setBounds(0, 30, 500, 50);
        ime.setVerticalAlignment(JLabel.TOP);
        proizvodnja.setVerticalAlignment(JLabel.TOP);

        add(proizvodnja);
        add(ime);

    }
}
