
package sudoku;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel{
    public Panel() {
    setPreferredSize(new Dimension(Prozor.getVisina(), Prozor.getVisina()));
    setLayout(null);
    setBackground(Color.WHITE);
    JLabel nova = new JLabel();
    nova.setBounds(0, 0, 25, 25);
    nova.setVisible(true);
}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(0, 0, 200, 25);
    }
}