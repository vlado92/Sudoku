package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Username
 */
public class Menu {

    public Menu(final Frame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu result = new JMenu("Result");

        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem newGame = new JMenuItem("New Game");

        JMenuItem lako = new JMenuItem("Easy");
        JMenuItem srednje = new JMenuItem("Medium");
        JMenuItem tesko = new JMenuItem("Hard");
        JMenuItem test = new JMenuItem("Test");

        help.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Help nova = new Help();
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
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

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        lako.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Lako");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        srednje.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Srednje");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        tesko.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Tesko");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        test.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Test");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DifficultyLevel izbor = new DifficultyLevel(frame);
                izbor.setVisible(true);
                izbor.setLocationRelativeTo(frame);
            }
        });

        file.add(newGame);
        file.add(exit);

        result.add(lako);
        result.add(srednje);
        result.add(tesko);
        result.add(test);

        frame.setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(result);
        menuBar.add(help);
    }
}
