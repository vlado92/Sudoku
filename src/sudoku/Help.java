package sudoku;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Username
 */
public class Help extends JFrame {

    private JLabel createdBy = new JLabel();
    private JTextArea rulesOfGame = new JTextArea();
    private JButton redButton = new JButton("");
    private JLabel redButtonLabel = new JLabel();
    private int[] red = new int[2];
    private JButton greenButton = new JButton("");
    private JLabel greenButtonLabel = new JLabel();
    private int[] green = new int[2];
    private JLabel buttonColor = new JLabel();
    public Help(Frame frame) throws HeadlessException {
        this.setTitle("Help");
        this.setSize(262, 400);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(frame);
        createdBy.setText("Created by: Vladimir Kunarac");
        rulesOfGame.setBackground(this.getBackground());
        rulesOfGame.setText("Hello! Enjoy playing this classic number game,\n"+
                            "adored by many across the globe - SUDOKU!\n\n" +
                            "The rules are simple. You have to fill\n"+
                            "the grid with numbers, while every row,\n "+
                            "column or grid box can have only one of\n "+
                            "the same numbers.\n\n" +
                            "To start a game click on File and choose\n"+
                            "New Game. From the next menu, you can pick\n" +
                            "the grid dimensions and game difficulty.\n" +
                            "If you do well enough, your name can find\n"+
                            "it's place in the Highscores. Just search\n"+
                            "for the game mode you played in and see \n"+
                            "who solved the puzzle in the shortest time!");
        rulesOfGame.setBounds(0, 0, 262, 248);
        rulesOfGame.setEditable(false);
        
        createdBy.setVerticalAlignment(JLabel.BOTTOM);
        createdBy.setHorizontalAlignment(JLabel.RIGHT);

        buttonColor.setBounds(0, 265,300,20);
        buttonColor.setText("If button is:");
            
        red[1] = 283;
        green[0] = red[0] = 0;
        redButton.setVisible(true);
        redButton.setEnabled(false);
        redButton.setBounds(red[0], red[1], 20,20);
        redButton.setBackground(Color.red);
        redButtonLabel.setText(" - you inserted invalid number!");
        redButtonLabel.setBounds(red[0]+30, red[1], 300, 20);
        
        green[1] = red[1] + 30;
        greenButton.setBackground(Color.green);
        greenButton.setVisible(true);
        greenButton.setEnabled(false);
        greenButton.setBounds(green[0], green[1], 20,20);
        greenButtonLabel.setText("you inserted number out of bounds!");
        greenButtonLabel.setBounds(green[0]+30, green[1], 300, 20);
        
        add(rulesOfGame);
        add(redButton);
        add(redButtonLabel);
        add(greenButton);
        add(greenButtonLabel);
        add(buttonColor);
        add(createdBy);
    }
}
