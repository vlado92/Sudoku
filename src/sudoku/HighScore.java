/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTextField;

public class HighScore extends JFrame {

    private static JLabel[] playerName = new JLabel[11];
    private static JLabel[] position = new JLabel[11];
    private static JLabel[] playerTime = new JLabel[11];
    private static long[] time = new long[11];
    private static ArrayList<String> scoreTracker = new ArrayList<>();
    private static String dificultyString;

    public HighScore(String tesko) throws HeadlessException {
        this.setTitle("High Score!");
        this.setSize(350, 350);
        dificultyString = tesko;
        for (int i = 0; i < 11; i++) {
            playerName[i] = new JLabel();
            playerTime[i] = new JLabel();
            position[i] = new JLabel();
        }

        readTextFileLineByLine();
        for (int i = 0; i < 11; i++) {
            position[i].setBounds(0, i * 20, 20, (i + 1) * 20);
            playerName[i].setBounds(40, i * 20, 220, (i + 1) * 20);
            playerTime[i].setBounds(240, i * 20, 300, (i + 1) * 20);
            position[i].setHorizontalAlignment(JLabel.CENTER);
            playerName[i].setHorizontalAlignment(JLabel.LEFT);
            add(position[i]);
            add(playerName[i]);
            add(playerTime[i]);
        }
        playerTime[10].setVisible(false);
        playerName[10].setVisible(false);
        position[10].setVisible(false);
    }

    public void setScore(long Score) {
        Calendar timeOnScore = Calendar.getInstance();
        Calendar timeOfPlayer = Calendar.getInstance();
        for (int i = 0; i < 10; i++) {
            timeOfPlayer.setTimeInMillis(Score);
            timeOnScore.setTimeInMillis(time[i]);
            final long score = Score;
            if (timeOfPlayer.before(timeOnScore)) {
                for (int k = 9; k >= i; k--) {
                    playerName[k + 1].setText(playerName[k].getText());
                    playerTime[k + 1].setText(playerTime[k].getText());
                }
                playerTime[i].setText(convertTimeFromLongToString(timeOfPlayer));
                final JTextField nameInput = new JTextField();
                nameInput.setBounds(40, i * 20, 160, (i + 1) * 20);
                nameInput.setVisible(true);
                nameInput.setEnabled(true);
                add(nameInput);
                for (int j = 0; j < 11; j++) {
                    add(position[j]);
                    add(playerName[j]);
                    add(playerTime[j]);
                }
                playerName[i].setVisible(false);
                final int index = i;
                nameInput.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        int pressed = (int) e.getKeyChar();
                        if (pressed == 10) {
                            nameInput.setVisible(false);
                            playerName[index].setText(nameInput.getText());
                            nameInput.setText(null);
                            playerName[index].setVisible(true);

                            String nameToPut = "" + (index) + "-" + playerName[index].getText() + "." + score;
                            writeTextFileLineByLine(nameToPut, index);
                        }
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

                break;
            }
        }
    }; 
    public static String convertTimeFromLongToString(Calendar time) {
        return ((time.get(Calendar.HOUR) < 10) ? ("0" + (time.get(Calendar.HOUR_OF_DAY) - 1)) : ("" + time.get(Calendar.HOUR_OF_DAY)))
                + ":" + ((time.get(Calendar.MINUTE) < 10) ? ("0" + time.get(Calendar.MINUTE)) : ("" + time.get(Calendar.MINUTE)))
                + ":" + ((time.get(Calendar.SECOND) < 10) ? ("0" + time.get(Calendar.SECOND)) : ("" + time.get(Calendar.SECOND)));

    };
    
    public static void readTextFileLineByLine() {
        FileReader in = null;
        //BufferedReader dozvoljava čitanje većeg "komada" datoteke odjednom.
        BufferedReader bin = null;
        try {

            File file = new File(".\\files\\rezultati" + dificultyString + ".txt");
            scoreTracker.clear();
            System.out.println(file.toPath().toString());
            in = new FileReader(file);
            bin = new BufferedReader(in);
            String data;
            while ((data = bin.readLine()) != null) {
                scoreTracker.add(data);
            }

            String nesto;
            int oznakaSubstringa;
            String milis;
            for (int i = 0; i < scoreTracker.size(); i++) {
                oznakaSubstringa = scoreTracker.get(i).indexOf('.');
                playerName[i].setText(scoreTracker.get(i).substring(2, oznakaSubstringa));
                position[i].setText("" + (i + 1));
                oznakaSubstringa = scoreTracker.get(i).indexOf('.') + 1;
                milis = scoreTracker.get(i).substring(oznakaSubstringa);
                Calendar konvertor = Calendar.getInstance();
                time[i] = Long.parseLong(milis);
                konvertor.setTimeInMillis(time[i]);
                playerTime[i].setText(convertTimeFromLongToString(konvertor));
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        }
    }

    public static void writeTextFileLineByLine(String duzina, int unos) {
        FileWriter out = null;

        try {
            scoreTracker.set(unos, duzina);
            int dataInt;
            out = new FileWriter(".\\files\\rezultati" + dificultyString + ".txt");

            for (int i = 0; i < scoreTracker.size(); i++) {
                for (int j = 0; j < scoreTracker.get(i).length(); j++) {
                    out.write(scoreTracker.get(i).charAt(j));
                }
                out.write(10);
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        }
    }
}
