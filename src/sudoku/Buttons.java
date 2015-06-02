package sudoku;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Math.sqrt;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Buttons extends JPanel implements ActionListener {

    private int index1;
    private int index2;
    private Random randomNumber = new Random();
    private static final int sudokuSize = 9;
    private static final int sqrtOfSize = (int) sqrt(sudokuSize);
    private final JButton[][] Sudoku = new JButton[sudokuSize][sudokuSize];
    private final Font font = new Font("Arial", Font.PLAIN, 15);
    private static JLabel stateOfGame = new JLabel();
    private JTextField inputField;
    private final int[][] intSudoku = new int[sudokuSize][sudokuSize];
    private boolean finished;
    public static String difString;

    public boolean isFisnished() {
        return finished;
    }
    
    public static JLabel getStateOfGame() {
        return stateOfGame;
    }
    public static int getSudokuSize() {
        return sudokuSize;
    }
    public static int getSqrtOfSudokuSize() {
        return sqrtOfSize;
    }

    public Buttons(String tezinaString, int tezinaInt) {    
        makingSudoku();
        difString = tezinaString;
        finished = false;
        generateButtons();
        deletingNumbersInSudoku(tezinaInt);
        IsFinished(tezinaString);
    }

    private void generateButtons(){
        int width = Frame.getDuzina();
        int height = Frame.getVisina();
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        for (int i = 0; i < sudokuSize; i++) {
            for (int j = 0; j < sudokuSize; j++) {
                Sudoku[i][j] = new JButton(""+
                        ((intSudoku[i][j] != 0) ? (intSudoku[i][j]) : (" ")));
                Sudoku[i][j].setFont(font);
                Sudoku[i][j].setBounds((Frame.getDuzina()) / sudokuSize * j + 3, 
                        (Frame.getVisina()) / sudokuSize * i + 3, 
                        Frame.getDuzina() / sudokuSize - 3, 
                        (Frame.getVisina()) / sudokuSize - 3);
                Sudoku[i][j].setVisible(true);
                Sudoku[i][j].setEnabled(false);
                Sudoku[i][j].setFocusable(false);
                Sudoku[i][j].addActionListener(this);
                Sudoku[i][j].setName(""+i+" "+j);
                add(Sudoku[i][j]);
            }
        }
    }
    private void deletingNumbersInSudoku(int deleteCounter) {
        int[][] deletedNumbers = new int[deleteCounter][2];
        int flag, i = 0;
        for (; deleteCounter > 0;) {
            flag = 2;
            index1 = randomNumber.nextInt(9);
            index2 = randomNumber.nextInt(9);
            deletedNumbers[i][0] = index1;
            deletedNumbers[i][1] = index2;
            if (i >= 1) {
                for (int k = 0; k <= i; k++) {
                    if (deletedNumbers[k][0] != deletedNumbers[i][0] && deletedNumbers[k][1] != deletedNumbers[i][1]) {
                        flag = 1;
                        break;
                    } else {
                        flag = 0;
                    }
                }
            } else {
                flag = 1;
            }
            if (flag == 1) {
                if (intSudoku[index1][index2] != 0) {
                    Sudoku[index1][index2].setEnabled(true);
                    Sudoku[index1][index2].setText(" ");
                    intSudoku[index1][index2] = 0;
                    deleteCounter--;
                    i++;
                }
            } else if (flag == 2) {
                System.out.println("ERROR int deleting");
            } else {
                System.out.println("ERROR: mistake in deletation");
            }
        }
    }
    private int IsValidNumber(int rows, int columns, int number) {
        int[] column = new int[9];
        int[] row = new int[9];
        int[] cube = new int[9];
        int rowFlag = 2, columnFlag = 2, cubeFlag = 0, cubeIndex = 0;
        System.out.println(columns + " " + rows);
        for (int i = 0; i < 9; i++) {
            column[i] = intSudoku[i][columns];
            row[i] = intSudoku[rows][i];
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[cubeIndex++] = intSudoku[i + 3 * ((int) rows / 3)][j + 3 * ((int) columns / 3)];
            }
        }
        for (int i = 0; i < 9; i++) {
            if (row[i] == number) {
                rowFlag = 1;
                break;
            } else {
                rowFlag = 0;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (column[i] == number) {
                columnFlag = 1;
                break;
            } else {
                columnFlag = 0;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (cube[i] == number) {
                cubeFlag = 1;
                break;
            } else {
                cubeFlag = 0;
            }
        }
        if (rowFlag == 0 && columnFlag == 0 && cubeFlag == 0) {
            return 1;
        }
        return 0;
    }
    private void IsFinished(String dificultyString) {
        int flag = 2;
        for (int i = 0; i < sudokuSize; i++) {
            for (int j = 0; j < sudokuSize; j++) {
                if (intSudoku[i][j] == 0) {
                    flag = 1;
                    break;
                } else {
                    flag = 0;
                }
            }
        }
        if (flag == 0) {
            stateOfGame.setText("Game Over");
            finished = true;
            DifficultyLevel.setButtonFinished(finished);
        } else if (flag == 1) {
            stateOfGame.setText("Still playing!");
        } else {
            stateOfGame.setText("ERROR");
        }
    }
    private void ispis() {
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
    private void makingSudoku() {
        System.out.println("fazu pravljenja proslo");
        IntSudokuGenerator generator = new IntSudokuGenerator(intSudoku);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < sqrtOfSize; i++) {
            for (int j = 0; j < sqrtOfSize; j++) {
                g.drawRect(i * Frame.getDuzina() / (sqrtOfSize) + 2, j * (Frame.getVisina()) / sqrtOfSize + 2, Frame.getDuzina() / sqrtOfSize - 2, (Frame.getVisina()) / sqrtOfSize - 2);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString().charAt(e.toString().length()-1));
        System.out.println(e.toString().charAt(e.toString().length()-3));
        int i = Integer.parseInt(""+e.toString().charAt(e.toString().length()-3));
        int j = Integer.parseInt(""+e.toString().charAt(e.toString().length()-1));
        if (Sudoku[i][j].isEnabled() == false) {
            System.out.println("nije moguce");
        } 
        else {
            JButton example = new JButton();
            example.setEnabled(true);
            for (int k = 0; k < sudokuSize; k++) {
                for (int l = 0; l < sudokuSize; l++) {
                    Sudoku[k][l].setBackground(example.getBackground());
                    if (!Sudoku[k][l].isVisible()) {
                        Sudoku[k][l].setVisible(true);
                        inputField.setVisible(false);
                        inputField = null;
                    }
                }
            }
            System.out.println("MOGUCE");
            inputField = new JTextField();
            inputField.setBounds(Sudoku[i][j].getBounds());
            Sudoku[i][j].setText(" ");
            intSudoku[i][j] = 0;
            inputField.setText("");
            inputField.setVisible(true);
            inputField.setEnabled(true);
            inputField.setFont(font);
            Sudoku[i][j].setVisible(false);
            add(inputField);
            inputField.grabFocus();
            inputField.setHorizontalAlignment(JTextField.CENTER);
            final int prvi = i;
            final int drugi = j;
            KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() <= '9' && e.getKeyChar() >= '1') {
                System.out.println(e.getKeyChar());
                if (IsValidNumber(prvi, drugi, e.getKeyChar() - '0') == 1) {
                    intSudoku[prvi][drugi] = e.getKeyChar() - '0';
                    Sudoku[prvi][drugi].setText(""+intSudoku[prvi][drugi]);
                    inputField.setVisible(false);
                    Sudoku[prvi][drugi].setVisible(true);
                    ispis();
                    IsFinished(DifficultyLevel.getDificultyString());
                } 
                else {
                    inputField.setVisible(false);
                    Sudoku[prvi][drugi].setVisible(true);
                    Sudoku[prvi][drugi].setBackground(Color.red);
                    }
                } 
            else{
                e.consume();
                inputField.setText("");
                } 
            }
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
                };
            inputField.addKeyListener(keyListener);
        }        
    }
}
