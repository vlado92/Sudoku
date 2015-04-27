
package sudoku;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel{
    public Panel() {
    setPreferredSize(new Dimension(Prozor.getVisina(), Prozor.getVisina()));
    setLayout(null);
    setBackground(Color.WHITE);
}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //potrebno je da se iscrta linije ali tako da 
        
        for(int i=1; i<4; i++)
        {
            g.drawRect(i*3, 0, Prozor.getDuzina()/3, (Prozor.getVisina()-50)/3);
            g.drawRect(i*3, Prozor.getDuzina()/3, Prozor.getDuzina()/3, (Prozor.getVisina()-50)/3);
            g.drawRect(i*3, 2*Prozor.getDuzina()/3, Prozor.getDuzina()/3, (Prozor.getVisina()-50)/3);
            //g.drawLine(0, i*Prozor.getDuzina()/3, Prozor.getVisina(), i*Prozor.getDuzina()/3);
            //g.drawRect((i-1)*Prozor.getVisina()/3, 0, Prozor.getDuzina()/3, Prozor.getVisina());
        }
    }
}