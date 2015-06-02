package sudoku;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Username
 */
public class Help extends JFrame {

    private JLabel createdBy = new JLabel();
    private JLabel lastEdit = new JLabel();

    public Help() throws HeadlessException {
        this.setTitle("Help");
        this.setSize(300, 300);
        this.setResizable(false);
        
        String noviRed = "nesto\n drugo";
        System.out.println(noviRed);
        createdBy.setText("Napravio: Vladimir Kunarac");
        lastEdit.setText("Poslednja izmjena: 10.5.2015. 17:33");
        createdBy.setBounds(0, 0, 200, 20);
        lastEdit.setBounds(0, 30, 500, 50);
        createdBy.setVerticalAlignment(JLabel.TOP);
        lastEdit.setVerticalAlignment(JLabel.TOP);

        add(lastEdit);
        add(createdBy);
    }
}
