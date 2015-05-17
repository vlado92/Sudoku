/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class HighScore extends JFrame{
    private final JLabel[] imeIgraca = new JLabel[11];
    private final JLabel[] pozicija = new JLabel[11];
    private final JLabel[] postignutoVrijeme = new JLabel[11];
    private Calendar[] vrijeme = new Calendar[11]; 
    
    public HighScore() throws HeadlessException {
    this.setTitle("High Score!");
    this.setSize(350, 350);
    
    for(int i=0; i<11; i++)
    {
        imeIgraca[i] = new JLabel();
        postignutoVrijeme[i] = new JLabel();
        pozicija[i] = new JLabel();
        
        imeIgraca[i].setText("Ime igrac");
        pozicija[i].setText(""+(i+1));
        vrijeme[i] = Calendar.getInstance();
        postignutoVrijeme[i].setText(konvertuj(vrijeme[i]));
        
        pozicija[i].setBounds(0, i*20, 20, (i+1)*20);
        imeIgraca[i].setBounds(40, i*20, 220, (i+1)*20);
        postignutoVrijeme[i].setBounds(240, i*20, 300, (i+1)*20);
        
        pozicija[i].setHorizontalAlignment(JLabel.CENTER);
        imeIgraca[i].setHorizontalAlignment(JLabel.LEFT);
        
        add(pozicija[i]);
        add(imeIgraca[i]);
        add(postignutoVrijeme[i]);
    }
    postignutoVrijeme[10].setVisible(false);
    imeIgraca[10].setVisible(false);
    pozicija[10].setVisible(false);
    }
    
    public void setScore(Calendar Score){
        for(int i=0; i<10; i++)
        {
            if(Score.before(vrijeme[i]))
            {
                postignutoVrijeme[i].setText(konvertuj(Score));
                final JTextField naziv = new JTextField();
                naziv.setBounds(40, i*20, 160, (i+1)*20);
                naziv.setVisible(true);
                naziv.setEnabled(true);
                add(naziv);
                for(int j=0; j<11; j++)
                {
                add(pozicija[j]);
                add(imeIgraca[j]);
                add(postignutoVrijeme[j]);
                }
                imeIgraca[i].setVisible(false);
                final int index = i;
                naziv.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        int pritisnuto = (int) e.getKeyChar();
                        if(pritisnuto == 10)
                        {
                            naziv.setVisible(false);
                            imeIgraca[index].setText(naziv.getText());
                            naziv.setText(null);
                            imeIgraca[index].setVisible(true);
                        }           
                    }
                    @Override public void keyPressed(KeyEvent e) {}
                    @Override public void keyReleased(KeyEvent e) {}
                });
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
