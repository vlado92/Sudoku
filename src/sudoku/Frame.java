package sudoku;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Frame extends JFrame implements ActionListener {

    private static final int width = 183 * Buttons.getSqrtOfSudokuSize();
    private static final int height = 183 * Buttons.getSqrtOfSudokuSize();
    private final JLabel timeLabel = new JLabel();
    private static int seconds = 0;
    public Timer timer = new Timer(1000, this);

    public static int getSeconds() {
        return seconds;
    }

    public static void setSeconds(int seconds) {
        Frame.seconds = seconds;
    }
    
    public static int getVisina() {
        return height;
    }

    public static int getDuzina() {
        return width;
    }

    public Frame() {
        this.setSize(width, height);
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
        timeLabel.setText("00:00");
        statusBar.addRightComponent(timeLabel);

        JLabel statLabel = new JLabel();
        statLabel = Buttons.getStateOfGame();
        statLabel.setHorizontalAlignment(JLabel.CENTER);
        statusBar.setLeftComponent(statLabel);
        contentPane.add(statusBar, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        seconds++;
        int secondsTemp = seconds%60;
        int minutes = seconds/60;
        String prenesi = new String();
        prenesi = ((minutes > 10) ? (""+minutes) : ("0"+minutes)) + ":"
                + ((secondsTemp > 10) ? (""+secondsTemp) : ("0"+secondsTemp));
        timeLabel.setText(prenesi);
        if(DifficultyLevel.isButtonFinished() && seconds!=0)
        {
            HighScore score = new HighScore(Buttons.difString);
            score.setVisible(true);
            score.setLocationRelativeTo(this);
            score.setScore(seconds);
            seconds=0;
            timer.restart();
            timer.stop();
        }
    }
}
