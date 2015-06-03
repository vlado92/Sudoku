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
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Buttons extends JPanel implements ActionListener {

    private int index1;
    private int index2;
    private Random randomNumber = new Random();
    private static int sudokuSize;
    private static int blockSize;
    private JButton[][] Sudoku;
    private Font font;
    private static JLabel stateOfGame = new JLabel();
    private JTextField inputField;
    private int[][] intSudoku;
    private boolean finished;
    public static String difString;
    static int inputCounter = 0;
    static int number;
    static int maximum;
                      
    public boolean isFisnished() {
        return finished;
    }

    public static JLabel getStateOfGame() {
        return stateOfGame;
    }

    public static int getSudokuSize() {
        return sudokuSize;
    }

    public static void setSudokuSize(int sudokuSize) {
        Buttons.sudokuSize = sudokuSize;
    }
    
    public static int getSqrtOfSudokuSize() {
        return blockSize;
    }

    public Buttons(String tezinaString, int tezinaInt) {
        makingSudoku();
        difString = tezinaString;
        finished = false;
        generateButtons();
        deletingNumbersInSudoku(tezinaInt);
    }

    private void generateButtons() {
        int width = Frame.getDuzina();
        int height = Frame.getVisina();
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        for (int i = 0; i < sudokuSize; i++) {
            for (int j = 0; j < sudokuSize; j++) {
                Sudoku[i][j] = new JButton(""
                        + ((intSudoku[i][j] != 0) ? (intSudoku[i][j]) : (" ")));
                Sudoku[i][j].setFont(font);
                Sudoku[i][j].setBounds((Frame.getDuzina()) / (sudokuSize) * j + (6 - blockSize),
                        (Frame.getVisina()) / (sudokuSize) * i + (6 - blockSize),
                        Frame.getDuzina() / (sudokuSize) - (6 - blockSize),
                        (Frame.getVisina()) / (sudokuSize) - (6 - blockSize));
                Sudoku[i][j].setVisible(true);
                Sudoku[i][j].setEnabled(false);
                Sudoku[i][j].setFocusable(false);
                Sudoku[i][j].addActionListener(this);
                Sudoku[i][j].setName("" + i + " " + j);
                add(Sudoku[i][j]);
            }
        }
    }

    private void deletingNumbersInSudoku(int deleteCounter) {
        int[][] deletedNumbers = new int[deleteCounter][2];
        int flag, i = 0;
        for (; deleteCounter > 0;) {
            flag = 2;
            index1 = randomNumber.nextInt(sudokuSize);
            index2 = randomNumber.nextInt(sudokuSize);
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
        int[] column = new int[sudokuSize];
        int[] row = new int[sudokuSize];
        int[] cube = new int[sudokuSize];
        int rowFlag = 2, columnFlag = 2, cubeFlag = 0, cubeIndex = 0;
        System.out.println(columns + " " + rows);
        for (int i = 0; i < sudokuSize; i++) {
            column[i] = intSudoku[i][columns];
            row[i] = intSudoku[rows][i];
        }
        for (int i = 0; i < blockSize; i++) {
            for (int j = 0; j < blockSize; j++) {
                cube[cubeIndex++] = intSudoku
                [i + blockSize * ((int) rows / blockSize)]
                [j + blockSize * ((int) columns / blockSize)];
            }
        }
        for (int i = 0; i < sudokuSize; i++) {
            if (row[i] == number) {
                rowFlag = 1;
                break;
            } else {
                rowFlag = 0;
            }
        }
        for (int i = 0; i < sudokuSize; i++) {
            if (column[i] == number) {
                columnFlag = 1;
                break;
            } else {
                columnFlag = 0;
            }
        }
        for (int i = 0; i < sudokuSize; i++) {
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
        for (int i = 0; i < sudokuSize; i++) {
            for (int j = 0; j < sudokuSize; j++) {
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
        sudokuSize = DifficultyLevel.getSizeOf();
        blockSize = (int) Math.sqrt(sudokuSize);
        font  = new Font("Arial", Font.PLAIN, 28 -sudokuSize);
        Sudoku =  new JButton[sudokuSize][sudokuSize];
        intSudoku = new int[sudokuSize][sudokuSize];
        
        System.out.println(intSudoku.length);
        IntSudokuGenerator generator = new IntSudokuGenerator(intSudoku);
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i=0; i<blockSize; i++)
            for(int j=0; j<blockSize; j++){
                g.drawLine(blockSize*i*((int) Sudoku[0][0].getBounds().getMaxX()),
                           blockSize*j*((int) Sudoku[0][0].getBounds().getMaxY()),
                           blockSize*i*((int) Sudoku[0][0].getBounds().getMaxX()),
                           Frame.getVisina());
                g.drawLine(blockSize*i*((int) Sudoku[0][0].getBounds().getMaxX()),
                           blockSize*j*((int) Sudoku[0][0].getBounds().getMaxY()),
                           Frame.getDuzina(),
                           blockSize*j*((int) Sudoku[0][0].getBounds().getMaxY()));
                
                g.drawLine(blockSize*(i+1)*((int) Sudoku[0][0].getBounds().getMaxX())+(5 - blockSize),
                           blockSize*j*((int) Sudoku[0][0].getBounds().getMaxY()),
                           blockSize*(i+1)*((int) Sudoku[0][0].getBounds().getMaxX())+(5 - blockSize),
                           Frame.getVisina());
                g.drawLine(blockSize*i*((int) Sudoku[0][0].getBounds().getMaxX()),
                           blockSize*(j+1)*((int) Sudoku[0][0].getBounds().getMaxY())+(5 - blockSize),
                           Frame.getDuzina(),
                           blockSize*(j+1)*((int) Sudoku[0][0].getBounds().getMaxY())+(5 - blockSize));
            }
    }

    public void actionPerformed(ActionEvent e) {
        int i, j;
        int substringIndex = e.toString().indexOf(" on ");
        String subString = e.toString().substring(substringIndex + 4);
        i = Integer.parseInt(subString.substring(0, subString.indexOf(" ")));
        j = Integer.parseInt(subString.substring(subString.indexOf(" ")+1));
        
        if (Sudoku[i][j].isEnabled() == false) {
            System.out.println("nije moguce");
        } else {
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
            number = maximum = 0;
            int temp = sudokuSize;
            while(temp>0)
            {
                maximum++;
                temp/=10;
            }
            KeyListener keyListener;
            keyListener = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() <= '9' && e.getKeyChar() >= '0') {
                        inputCounter++;
                    }else if(e.getKeyChar() == 10) {
                        if(inputCounter == 0){
                            e.consume();
                        }else{
                            int broj = Integer.parseInt(inputField.getText());
                            inputCounter = 0;
                            if(broj>0 && broj < 17){
                                if (IsValidNumber(prvi, drugi, broj) == 1) {
                                    intSudoku[prvi][drugi] = broj;
                                    Sudoku[prvi][drugi].setText("" + intSudoku[prvi][drugi]);
                                    inputField.setVisible(false);
                                    Sudoku[prvi][drugi].setVisible(true);
                                    ispis();
                                    IsFinished(DifficultyLevel.getDificultyString());
                                } else {
                                    inputField.setVisible(false);
                                    Sudoku[prvi][drugi].setVisible(true);
                                    Sudoku[prvi][drugi].setBackground(Color.red);
                            }
                            }else{
                                inputField.setVisible(false);
                                Sudoku[prvi][drugi].setVisible(true);
                                Sudoku[prvi][drugi].setBackground(Color.green);
                            }
                        }
                    }else{
                        e.consume();
                    }
                    System.out.println("INPUT COUNTER = "+inputCounter);
                    number = (int) (number*Math.pow(10, inputCounter - 1) + e.getKeyChar() - '0');
                    System.out.println("INPUT FIELD = "+number);
                    if(inputCounter == maximum){
                        inputCounter = 0;
                        if(number > 0 && number <= sudokuSize){
                            if (IsValidNumber(prvi, drugi, number) == 1) {
                                intSudoku[prvi][drugi] = number;
                                Sudoku[prvi][drugi].setText("" + intSudoku[prvi][drugi]);
                                inputField.setVisible(false);
                                Sudoku[prvi][drugi].setVisible(true);
                                ispis();
                                IsFinished(DifficultyLevel.getDificultyString());
                            }else{
                                inputField.setVisible(false);
                                Sudoku[prvi][drugi].setVisible(true);
                                Sudoku[prvi][drugi].setBackground(Color.red);
                            }
                        }else{
                            inputField.setVisible(false);
                            Sudoku[prvi][drugi].setVisible(true);
                            Sudoku[prvi][drugi].setBackground(Color.green);
                        }
                    }
                                }
                @Override public void keyPressed(KeyEvent e) {}
                @Override public void keyReleased(KeyEvent e) {}
            };
            inputField.addKeyListener(keyListener);
        }
    }
}
