/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Sudoku implements Runnable {

    protected TimerThread timerThread;

    @Override
    public void run() {
        Prozor frame = new Prozor();
        frame.setTitle("Sudoku");
        Menu meni = new Menu(frame);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        JStatusBar statusBar = new JStatusBar();
        
        final JLabel timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        statusBar.addRightComponent(timeLabel);
        
        JLabel statLabel = new JLabel();
        statLabel = Dugme.getLabela();
        statLabel.setHorizontalAlignment(JLabel.CENTER);
        statusBar.setLeftComponent(statLabel);
        contentPane.add(statusBar, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });

        timerThread = new TimerThread(timeLabel);
        timerThread.start();

        frame.setVisible(true);
    }

    public void exitProcedure() {
        timerThread.setRunning(false);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Sudoku());
    }

    public class TimerThread extends Thread {

        protected boolean isRunning;

        public JLabel timeLabel;

        protected SimpleDateFormat timeFormat =
                new SimpleDateFormat("h:mm a");

        public TimerThread(JLabel timeLabel) {
            this.timeLabel = timeLabel;
            this.isRunning = true;
        }

        @Override
        public void run() {
            while (isRunning) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Calendar currentCalendar;
                        currentCalendar = Calendar.getInstance();
                        int minute = (currentCalendar.get(Calendar.MINUTE)- Menu.vrijeme.get(Calendar.MINUTE));
                        String Minute = (minute < 10) ? ("0"+minute) : (""+minute);
                        int sekunde = (currentCalendar.get(Calendar.SECOND)- Menu.vrijeme.get(Calendar.SECOND));
                        String sekund = (sekunde < 10) ? ("0"+sekunde) :(""+sekunde);
                        String prenesi = (Menu.novaIgra.isEnabled())? "00:00":""+Minute +":"+sekund; 
                        timeLabel.setText(prenesi);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

    }
}
