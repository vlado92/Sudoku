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
    private static final JLabel stateOfGame = new JLabel();
    private JTextField inputField;
    private int[][] intSudoku;
    private static boolean finished;
    static int inputCounter = 0;
    static int number;
    static int numberCount;
    static int emptySpaces;
                      
    public static boolean isFisnished() {
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

    public Buttons(int numbersToDelete) {
        makingSudoku();
        finished = false;
        generateButtons();
        deletingNumbersInSudoku(numbersToDelete);
    }

    private void makingSudoku() {
        sudokuSize = DifficultyLevel.getSizeOf();
        blockSize = (int) Math.sqrt(sudokuSize);
        font  = new Font("Arial", Font.PLAIN, 28 -sudokuSize);
        Sudoku =  new JButton[sudokuSize][sudokuSize];
        intSudoku = new int[sudokuSize][sudokuSize];
        IntSudokuGenerator generator = new IntSudokuGenerator(intSudoku);
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
        emptySpaces = deleteCounter;
        int randomIndex1;
        int randomIndex2;
        while(deleteCounter > 0){
            randomIndex1 = randomNumber.nextInt(sudokuSize);
            randomIndex2 = randomNumber.nextInt(sudokuSize);
            if(intSudoku[randomIndex1][randomIndex2] != 0)
            {
                intSudoku[randomIndex1][randomIndex2] = 0;
                Sudoku[randomIndex1][randomIndex2].setEnabled(true);
                Sudoku[randomIndex1][randomIndex2].setText(" ");
                intSudoku[randomIndex1][randomIndex2] = 0;
                deleteCounter--;
            }
        }
    }
    private boolean IsValidNumber(int rows, int columns, int number) {
        int[] column = new int[sudokuSize];
        int[] row = new int[sudokuSize];
        int[] cube = new int[sudokuSize];
        int rowFlag = 2, columnFlag = 2, cubeFlag = 0, cubeIndex = 0;
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
            emptySpaces--;
            return true;
        }
        return false;
    }
    private void placeNumberInSudoku(int firstIndex, int secondIndex, int number) {
        inputCounter = 0;
        if(number > 0 && number <= sudokuSize){
            if (IsValidNumber(firstIndex, secondIndex, number)) {
                intSudoku[firstIndex][secondIndex] = number;
                Sudoku[firstIndex][secondIndex].setText("" + intSudoku[firstIndex][secondIndex]);
                inputField.setVisible(false);
                Sudoku[firstIndex][secondIndex].setVisible(true);
                if(emptySpaces == 0)
                {
                    stateOfGame.setText("Game Over");
                    finished = true;
                }else
                {
                    stateOfGame.setText("Still playing!");
                }
            }else{
                inputField.setVisible(false);
                Sudoku[firstIndex][secondIndex].setVisible(true);
                Sudoku[firstIndex][secondIndex].setBackground(Color.red);
            }
        }else{
            inputField.setVisible(false);
            Sudoku[firstIndex][secondIndex].setVisible(true);
            Sudoku[firstIndex][secondIndex].setBackground(Color.green);
        }
    }
    @Override public void paint(Graphics g) {
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
    @Override public void actionPerformed(ActionEvent e) {
        int i, j;
        int substringIndex = e.toString().indexOf(" on ");
        String subString = e.toString().substring(substringIndex + 4);
        i = Integer.parseInt(subString.substring(0, subString.indexOf(" ")));
        j = Integer.parseInt(subString.substring(subString.indexOf(" ")+1));
        
        if (Sudoku[i][j].isEnabled()) {
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
            if(intSudoku[i][j]!=0)
            {
                emptySpaces++;
                Sudoku[i][j].setText(" ");
                intSudoku[i][j] = 0;
            }
            inputField.setText("");
            inputField.setVisible(true);
            inputField.setEnabled(true);
            inputField.setFont(font);
            Sudoku[i][j].setVisible(false);
            add(inputField);
            inputField.grabFocus();
            inputField.setHorizontalAlignment(JTextField.CENTER);
            final int firstIndex = i;
            final int secondIndex = j;
            number = numberCount = 0;
            int temp = sudokuSize;
            while(temp>0)
            {
                numberCount++;
                temp/=10;
            }
            KeyListener keyListener = new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() <= '9' && e.getKeyChar() >= '0') {
                        inputCounter++;
                    }else if(e.getKeyChar() == 10) {
                        if(inputCounter == 0){
                            e.consume();
                        }else{
                            number = Integer.parseInt(inputField.getText());
                            placeNumberInSudoku(firstIndex, secondIndex, number);
                        }
                    }else{
                        e.consume();
                    }
                    number = (int) (number*Math.pow(10, inputCounter - 1) + e.getKeyChar() - '0');
                    if(inputCounter == numberCount){
                        placeNumberInSudoku(firstIndex, secondIndex, number);
                    }
                }
                @Override public void keyPressed(KeyEvent e) {}
                @Override public void keyReleased(KeyEvent e) {}
            };
            inputField.addKeyListener(keyListener);
        } else {
            System.out.println("nije moguce");
        }
    }
}