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

public class Frame extends JFrame implements ActionListener {

    private static final int width = 183 * Buttons.getSqrtOfSudokuSize();
    private static final int height = 183 * Buttons.getSqrtOfSudokuSize();
    private final JLabel timeLabel = new JLabel();

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
        statusBar.addRightComponent(timeLabel);

        JLabel statLabel = new JLabel();
        statLabel = Buttons.getStateOfGame();
        statLabel.setHorizontalAlignment(JLabel.CENTER);
        statusBar.setLeftComponent(statLabel);
        contentPane.add(statusBar, BorderLayout.SOUTH);
        Timer timer = new Timer(1000, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        Calendar currentCalendar;
        currentCalendar = Calendar.getInstance();
        int minute = (currentCalendar.get(Calendar.MINUTE) - DifficultyLevel.startTime.get(Calendar.MINUTE));
        String Minute = (minute < 10) ? ("0" + minute) : ("" + minute);
        int sekunde = (currentCalendar.get(Calendar.SECOND) - DifficultyLevel.startTime.get(Calendar.SECOND));
        String sekund = (sekunde < 10) ? ("0" + sekunde) : ("" + sekunde);
        String prenesi = (DifficultyLevel.getVisible()) ? "00:00" : "" + Minute + ":" + sekund;
        timeLabel.setText(prenesi);
    }
}
