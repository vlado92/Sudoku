/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.HeadlessException;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JFrame;
/**
 *
 * @author Username
 */
public class HighScore extends JFrame {
    private final JLabel[] mjesto = new JLabel[11];
    private final JLabel[] brojevi = new JLabel[11];
    private final JLabel[] rezultat = new JLabel[11];
    private Calendar[] vrijeme = new Calendar[11]; 
    
    public HighScore() throws HeadlessException {
    this.setTitle("High Score!");
    this.setSize(350, 350);
    
    for(int i=0; i<11; i++)
    {
        mjesto[i] = new JLabel();
        rezultat[i] = new JLabel();
        brojevi[i] = new JLabel();
        
        mjesto[i].setText(" ");
        brojevi[i].setText(""+(i+1));
        vrijeme[i] = Calendar.getInstance();
        rezultat[i].setText(konvertuj(vrijeme[i]));
        
        brojevi[i].setBounds(0, i*20, 20, (i+1)*20);
        mjesto[i].setBounds(40, i*20, 220, (i+1)*20);
        rezultat[i].setBounds(240, i*20, 300, (i+1)*20);
        
        brojevi[i].setHorizontalAlignment(JLabel.CENTER);
        mjesto[i].setHorizontalAlignment(JLabel.CENTER);
        
        add(brojevi[i]);
        add(mjesto[i]);
        add(rezultat[i]);
    }
    rezultat[10].setVisible(false);
    mjesto[10].setVisible(false);
    brojevi[10].setVisible(false);
    }
    
    public void setScore(String name, Calendar Score){
        for(int i=0; i<10; i++)
        {
            if(Score.before(vrijeme[i]))
            {
                rezultat[i].setText(konvertuj(Score));
                mjesto[i].setText(name);
                break;
            }
        }
    };
    
    private String konvertuj(Calendar vrijeme)
    {
        return ((vrijeme.get(Calendar.HOUR) < 10)?("0"+vrijeme.get(Calendar.HOUR)):(""+vrijeme.get(Calendar.HOUR)))
                    + ":" +((vrijeme.get(Calendar.MINUTE) < 10)?("0"+vrijeme.get(Calendar.MINUTE)):(""+vrijeme.get(Calendar.MINUTE)))
                    + ":" +((vrijeme.get(Calendar.SECOND) < 10)?("0"+vrijeme.get(Calendar.SECOND)):(""+vrijeme.get(Calendar.SECOND)));
        
    };
}
