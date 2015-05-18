package sudoku;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Prozor extends JFrame implements ActionListener{
    private static final int duzina = 183*Dugme.getKorijen();
    private static final int visina = 183*Dugme.getKorijen();
    private final JLabel timeLabel = new JLabel();

    public static int getVisina()   {return visina;}
    public static int getDuzina()   { return duzina;}
       
    public Prozor(){
        this.setSize(duzina, visina);
        this.setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setLayout(new FlowLayout()); 
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        
        Menu meni = new Menu(this);
        JStatusBar statusBar = new JStatusBar();
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        statusBar.addRightComponent(timeLabel);
        
        JLabel statLabel = new JLabel();
        statLabel = Dugme.getStateOfGame();
        statLabel.setHorizontalAlignment(JLabel.CENTER);
        statusBar.setLeftComponent(statLabel);
        contentPane.add(statusBar, BorderLayout.SOUTH);
        Timer timer = new  Timer(1000, this);
        timer.start();
    }
    public void actionPerformed(ActionEvent e){
    Calendar currentCalendar;
    currentCalendar = Calendar.getInstance();
        int minute = (currentCalendar.get(Calendar.MINUTE)- IzborTezine.vrijeme.get(Calendar.MINUTE));
        String Minute = (minute < 10) ? ("0"+minute) : (""+minute);
        int sekunde = (currentCalendar.get(Calendar.SECOND)- IzborTezine.vrijeme.get(Calendar.SECOND));
                        String sekund = (sekunde < 10) ? ("0"+sekunde) :(""+sekunde);
                        String prenesi = (IzborTezine.getVisible())? "00:00":""+Minute +":"+sekund; 
                        timeLabel.setText(prenesi);

    }
}
