package sudoku;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.sqrt;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dugme extends JPanel implements MouseListener {

    private int index1;
    private int index2;
    private Random randomNumber = new Random();
    private static int velicina = 9;
    private static int korijen = (int) sqrt(velicina);
    private JButton[][] Sudoku = new JButton[velicina][velicina];
    private final Font font = new Font("Arial", Font.PLAIN, 15);
    private static JLabel stateOfGame = new JLabel();
    private JTextField poljeZaUnos = new JTextField();

    private static String tezina;

    public static JLabel getStateOfGame() {
        return stateOfGame;
    }

    public static int getVelicina() {
        return velicina;
    }

    public static int getKorijen() {
        return korijen;
    }

    public Dugme(String tezinaString, int tezinaInt) {
        //<editor-fold defaultstate="collapsed" desc=" Generisanje Dugmadi ">
        tezina = tezinaString;
        int duzina = Prozor.getDuzina();
        int visina = Prozor.getVisina();
        setPreferredSize(new Dimension(duzina, visina));
        setLayout(null);
        setBackground(Color.white);
        for (int i = 0; i < Sudoku[0].length; i++) {
            for (int j = 0; j < Sudoku[0].length; j++) {
                Sudoku[i][j] = new JButton(" ");
            }
        }
        for (int i = 0; i < velicina; i++) {
            for (int j = 0; j < velicina; j++) {
                Sudoku[i][j].setFont(font);
                Sudoku[i][j].setBounds((Prozor.getDuzina()) / velicina * j + 3, (Prozor.getVisina()) / velicina * i + 3, Prozor.getDuzina() / velicina - 3, (Prozor.getVisina()) / velicina - 3);
                Sudoku[i][j].setVisible(true);
                Sudoku[i][j].setEnabled(false);
                Sudoku[i][j].setFocusable(false);
                Sudoku[i][j].addMouseListener(this);
                addMouseListener(this);
                add(Sudoku[i][j]);
            }
        }
        //</editor-fold>
        algoritam();
        mjesanje();
        brisanje(tezinaInt);
        ispis();
        kraj(tezinaString);
    }

    private void mjesanje() {
        //<editor-fold defaultstate="collapsed" desc=" Algoritam mjesanja ovoga ">
        int broj1 = randomNumber.nextInt(100) + 60;
        for (int i = 0; i < broj1; i++) {
            String razmjena = new String();
            for (int blokovi = 0; blokovi < 3; blokovi++) {
                index1 = randomNumber.nextInt(3);
                index2 = randomNumber.nextInt(3);
                for (int kolone = 0; kolone < velicina; kolone++) {
                    razmjena = Sudoku[index1 + 3 * blokovi][kolone].getText();
                    Sudoku[index1 + 3 * blokovi][kolone].setText(Sudoku[index2 + 3 * blokovi][kolone].getText());
                    Sudoku[index2 + 3 * blokovi][kolone].setText(razmjena);
                }
            }
            for (int blokovi = 0; blokovi < 3; blokovi++) {
                index1 = randomNumber.nextInt(3);
                index2 = randomNumber.nextInt(3);
                for (int redovi = 0; redovi < velicina; redovi++) {
                    razmjena = Sudoku[redovi][index1 + 3 * blokovi].getText();
                    Sudoku[redovi][index1 + 3 * blokovi].setText(Sudoku[redovi][index2 + 3 * blokovi].getText());
                    Sudoku[redovi][index2 + 3 * blokovi].setText(razmjena);
                }
            }
            // ovde bi bilo pozeljno da se mjenjaju blokovi malo
        }
    }//</editor-fold>

    private void brisanje(int brisanje) {
        //<editor-fold defaultstate="collapsed" desc=" brisanje clanova ">
        int[][] izbrisani = new int[brisanje][2];
        int zastava, i = 0;
        for (; brisanje > 0;) {
            zastava = 2;
            index1 = randomNumber.nextInt(9);
            index2 = randomNumber.nextInt(9);
            izbrisani[i][0] = index1;
            izbrisani[i][1] = index2;
            if (i >= 1) {
                for (int k = 0; k <= i; k++) {
                    if (izbrisani[k][0] != izbrisani[i][0] && izbrisani[k][1] != izbrisani[i][1]) {
                        zastava = 1;
                        break;
                    } else {
                        zastava = 0;
                    }
                }
            } else {
                zastava = 1;
            }
            if (zastava == 1) {
                if (!" ".equals(Sudoku[index1][index2].getText())) {
                    Sudoku[index1][index2].setEnabled(true);
                    Sudoku[index1][index2].setText(" ");
                    brisanje--;
                    i++;
                }
            } else if (zastava == 2) {
                System.out.println("Ispalo je sranje");
            } else {
                System.out.println("nesto trece");
            }
        }
    }//</editor-fold>    

    private int ispitivanje(int redovi, int kolona, char t) {
        //<editor-fold defaultstate="collapsed" desc=" Ispitivanje da li je moguce unijeti broj ">
        String[] kolone = new String[9];
        String[] red = new String[9];
        String[] kocka = new String[9];
        String te = "" + t;
        int redP = 2, redK = 2, redKoc = 0, prolaz = 0;
        System.out.println(kolona + " " + redovi);
        for (int i = 0; i < 9; i++) {
            kolone[i] = Sudoku[i][kolona].getText();
            red[i] = Sudoku[redovi][i].getText();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                kocka[prolaz++] = Sudoku[i + 3 * ((int) redovi / 3)][j + 3 * ((int) kolona / 3)].getText();
            }
        }
        for (int i = 0; i < 9; i++) {
            if (red[i].equals(te)) {
                redP = 1;
                break;
            } else {
                redP = 0;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (kolone[i].equals(te)) {
                redK = 1;
                break;
            } else {
                redK = 0;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (kocka[i] == "") {
                System.out.print("0 ");
            } else {
                System.out.print(kocka[i] + " ");
            }
        }
        for (int i = 0; i < 9; i++) {
            if (kocka[i].equals(te)) {
                redKoc = 1;
                break;
            } else {
                redKoc = 0;
            }
        }

        if (redP == 0) {
            System.out.println("Uredu je po redovima ");
        } else {
            System.out.println("Nije uredu po redovima");
        }

        if (redK == 0) {
            System.out.println("Uredu je po kolonama ");
        } else {
            System.out.println("Nije Uredu je po kolonama ");
        }

        if (redKoc == 0) {
            System.out.print("Uredu je po kockama ");
        } else {
            System.out.println("NIJE Uredu je po kockama ");
        }

        if (redP == 0 && redK == 0 && redKoc == 0) {
            return 1;
        }
        return 0;
    }//</editor-fold>

    private void kraj(String tezina) {
        //<editor-fold defaultstate="collapsed" desc=" Ispituje da li su sva polja popunjena ">
        int p = 2;
        for (int i = 0; i < velicina; i++) {
            for (int j = 0; j < velicina; j++) {
                if (Sudoku[i][j].getText().equals(" ")) {
                    p = 1;
                    break;
                } else {
                    p = 0;
                }
            }
        }
        if (p == 0) {
            Dugme.stateOfGame.setText("Game Over");
            JFrame nesto = new JFrame();
            long krajnje = Calendar.getInstance().getTimeInMillis() - IzborTezine.vrijeme.getTimeInMillis();
            HighScore novi = new HighScore(tezina);
            novi.setVisible(true);
            novi.setScore(krajnje);
        } else if (p == 1) {
            Dugme.stateOfGame.setText("Still playing!");
        } else {
            Dugme.stateOfGame.setText("ERROR");
        }
    }////</editor-fold>

    private void ispis() {
        //<editor-fold defaultstate="collapsed" desc="Finkcija koja u konzolu ispisuje Sudoku">
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Sudoku[i][j].getText() == " ") {
                    System.out.print("? ");
                } else {
                    System.out.print(Sudoku[i][j].getText() + " ");
                }
            }
            System.out.println();
        }
    }

    //</editor-fold>
    private void algoritam() {
        //<editor-fold defaultstate="collapsed" desc="Algoritam postavljanja brojeva">
        int broj1, broj2, broj3;
        broj1 = randomNumber.nextInt(velicina + 1);
        broj2 = randomNumber.nextInt(velicina + 1) + broj1;
        broj3 = randomNumber.nextInt(velicina + 1) + broj2;

        for (int i = 0; i < velicina; i++) {
            for (int j = 0; j < velicina; j++) {
                Sudoku[i][j].setText("" + ((broj1 + korijen * (i % korijen) + (j + (i / korijen) + broj3) % korijen + (j / korijen + broj2) * korijen) % velicina + 1));
            }
        }
    }
    //</editor-fold>

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < korijen; i++) {
            for (int j = 0; j < korijen; j++) {
                g.drawRect(i * Prozor.getDuzina() / (korijen) + 2, j * (Prozor.getVisina()) / korijen + 2, Prozor.getDuzina() / korijen - 2, (Prozor.getVisina()) / korijen - 2);
            }
        }
    }

//<editor-fold defaultstate="collapsed" desc="Event Handling">
//vjerovatno dobro, ali treba jos ispitivanja
    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.getMousePosition().x >= Sudoku[i][j].getX() && this.getMousePosition().x <= Sudoku[i][j].getWidth() * (j + 1)
                        && this.getMousePosition().y >= Sudoku[i][j].getY() && this.getMousePosition().y <= Sudoku[i][j].getHeight() * (i + 1)) {
                    if (Sudoku[i][j].isEnabled() == false) {
                        System.out.println("nije moguce");
                        break;
                    } else {
                        for (int k = 0; k < velicina; k++) {
                            for (int l = 0; l < velicina; l++) {
                                if (!Sudoku[k][l].isVisible()) {
                                    Sudoku[k][l].setVisible(true);
                                    poljeZaUnos.setVisible(false);
                                }
                            }
                        }
                        System.out.println("MOGUCE");
                        poljeZaUnos.setBounds(Sudoku[i][j].getBounds());
                        Sudoku[i][j].setVisible(false);
                        Sudoku[i][j].setText(" ");
                        poljeZaUnos.setText("");
                        poljeZaUnos.setVisible(true);
                        poljeZaUnos.setEnabled(true);
                        poljeZaUnos.setFont(font);
                        add(poljeZaUnos);
                        poljeZaUnos.setHorizontalAlignment(JTextField.CENTER);
                        final int drugi = j;
                        final int prvi = i;
                        KeyListener keyListener = new KeyListener() {
                            @Override
                            public void keyTyped(KeyEvent e) {
                                if (e.getKeyChar() <= '9' && e.getKeyChar() >= '1') {
                                    System.out.println(e.getKeyChar());
                                    if (ispitivanje(prvi, drugi, e.getKeyChar()) == 1) {
                                        Sudoku[prvi][drugi].setText("" + e.getKeyChar());
                                        poljeZaUnos.setVisible(false);
                                        Sudoku[prvi][drugi].setVisible(true);
                                        ispis();
                                        kraj(tezina);
                                    } else {
                                        poljeZaUnos.setVisible(false);
                                        Sudoku[prvi][drugi].setVisible(true);
                                        //                           Component rootPane = null;
                                        //                           JOptionPane.showMessageDialog(rootPane, "Taj broj nije moguce unijeti!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                                    }
                                } else if (e.getKeyChar() == '0') {
                                    Sudoku[prvi][drugi].setText(" ");
                                    poljeZaUnos.setVisible(false);
                                    Sudoku[prvi][drugi].setVisible(true);
                                    ispis();
                                    poljeZaUnos.setText(null);
                                } else {
                                    e.consume();
                                    poljeZaUnos.setText("");
                                }
                            }

                            @Override
                            public void keyPressed(KeyEvent e) {
                            }

                            @Override
                            public void keyReleased(KeyEvent e) {
                            }
                        };
                        poljeZaUnos.addKeyListener(keyListener);
                        addKeyListener(keyListener);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }
    //</editor-fold>
}
