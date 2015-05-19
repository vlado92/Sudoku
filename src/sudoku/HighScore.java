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
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class HighScore extends JFrame {

    private static JLabel[] imeIgraca = new JLabel[11];
    private static JLabel[] pozicija = new JLabel[11];
    private static JLabel[] postignutoVrijeme = new JLabel[11];
    private static long[] vrijeme = new long[11];
    private static ArrayList<String> rijeci = new ArrayList<>();
    private static String tezina;

    public HighScore(String tesko) throws HeadlessException {
        this.setTitle("High Score!");
        this.setSize(350, 350);
        tezina = tesko;
        for (int i = 0; i < 11; i++) {
            imeIgraca[i] = new JLabel();
            postignutoVrijeme[i] = new JLabel();
            pozicija[i] = new JLabel();
        }

        readTextFileLineByLine();
        for (int i = 0; i < 11; i++) {
            pozicija[i].setBounds(0, i * 20, 20, (i + 1) * 20);
            imeIgraca[i].setBounds(40, i * 20, 220, (i + 1) * 20);
            postignutoVrijeme[i].setBounds(240, i * 20, 300, (i + 1) * 20);
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

    public void setScore(long Score) {
        Calendar tabelarno = Calendar.getInstance();
        Calendar kalendar = Calendar.getInstance();
        for (int i = 0; i < 10; i++) {
            kalendar.setTimeInMillis(Score);
            tabelarno.setTimeInMillis(vrijeme[i]);
            final long score = Score;
            if (kalendar.before(tabelarno)) {
                for (int k = 9; k >= i; k--) {
                    imeIgraca[k + 1].setText(imeIgraca[k].getText());
                    postignutoVrijeme[k + 1].setText(postignutoVrijeme[k].getText());
                }
                postignutoVrijeme[i].setText(konvertuj(kalendar));
                final JTextField naziv = new JTextField();
                naziv.setBounds(40, i * 20, 160, (i + 1) * 20);
                naziv.setVisible(true);
                naziv.setEnabled(true);
                add(naziv);
                for (int j = 0; j < 11; j++) {
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
                        if (pritisnuto == 10) {
                            naziv.setVisible(false);
                            imeIgraca[index].setText(naziv.getText());
                            naziv.setText(null);
                            imeIgraca[index].setVisible(true);

                            String zamjeni = "" + (index) + "-" + imeIgraca[index].getText() + "." + score;
                            writeTextFileLineByLine(zamjeni, index);
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
    }

    ; 
    public static String konvertuj(Calendar vrijeme) {
        return ((vrijeme.get(Calendar.HOUR) < 10) ? ("0" + (vrijeme.get(Calendar.HOUR_OF_DAY) - 1)) : ("" + vrijeme.get(Calendar.HOUR_OF_DAY)))
                + ":" + ((vrijeme.get(Calendar.MINUTE) < 10) ? ("0" + vrijeme.get(Calendar.MINUTE)) : ("" + vrijeme.get(Calendar.MINUTE)))
                + ":" + ((vrijeme.get(Calendar.SECOND) < 10) ? ("0" + vrijeme.get(Calendar.SECOND)) : ("" + vrijeme.get(Calendar.SECOND)));

    }

    ;
    
    public static void readTextFileLineByLine() {
        FileReader in = null;
        //BufferedReader dozvoljava čitanje većeg "komada" datoteke odjednom.
        BufferedReader bin = null;
        try {

            File file = new File("D:\\programiranje\\Java\\projekat\\Sudoku\\files\\rezultati" + tezina + ".txt");
            rijeci.clear();
            System.out.println(file.toPath().toString());
            in = new FileReader(file);
            bin = new BufferedReader(in);
            String data;
            while ((data = bin.readLine()) != null) {
                rijeci.add(data);
            }

            String nesto;
            int oznakaSubstringa;
            String milis;
            for (int i = 0; i < rijeci.size(); i++) {
                oznakaSubstringa = rijeci.get(i).indexOf('.');
                imeIgraca[i].setText(rijeci.get(i).substring(2, oznakaSubstringa));
                pozicija[i].setText("" + (i + 1));
                oznakaSubstringa = rijeci.get(i).indexOf('.') + 1;
                milis = rijeci.get(i).substring(oznakaSubstringa);
                Calendar konvertor = Calendar.getInstance();
                vrijeme[i] = Long.parseLong(milis);
                konvertor.setTimeInMillis(vrijeme[i]);
                postignutoVrijeme[i].setText(konvertuj(konvertor));
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
            rijeci.set(unos, duzina);
            int dataInt;
            out = new FileWriter("D:\\programiranje\\Java\\projekat\\Sudoku\\files\\rezultati" + tezina + ".txt");

            for (int i = 0; i < rijeci.size(); i++) {
                for (int j = 0; j < rijeci.get(i).length(); j++) {
                    out.write(rijeci.get(i).charAt(j));
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
