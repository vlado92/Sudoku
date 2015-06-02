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
        JMenuItem newGame = new JMenuItem("New Game");
        JMenuItem exit = new JMenuItem("Exit");
        
        file.add(newGame);
        file.add(exit);
        
        JMenu help = new JMenu("Help");
        
        JMenu result = new JMenu("Result");
        JMenu lako = new JMenu("Easy");
        JMenu srednje = new JMenu("Medium");
        JMenu tesko = new JMenu("Hard");
        JMenu test = new JMenu("Test");

        JMenuItem lako2 = new JMenuItem("4x4");
        JMenuItem lako3 = new JMenuItem("9x9");
        JMenuItem lako4 = new JMenuItem("16x16");
        
        lako.add(lako2);
        lako.add(lako3);
        lako.add(lako4);
        
        JMenuItem srednje2 = new JMenuItem("4x4");
        JMenuItem srednje3 = new JMenuItem("9x9");
        JMenuItem srednje4 = new JMenuItem("16x16");
        
        srednje.add(srednje2);
        srednje.add(srednje3);
        srednje.add(srednje4);
        
        JMenuItem tesko2 = new JMenuItem("4x4");
        JMenuItem tesko3 = new JMenuItem("9x9");
        JMenuItem tesko4 = new JMenuItem("16x16");
        
        tesko.add(tesko2);
        tesko.add(tesko3);
        tesko.add(tesko4);
        
        JMenuItem test2 = new JMenuItem("4x4");
        JMenuItem test3 = new JMenuItem("9x9");
        JMenuItem test4 = new JMenuItem("16x16");
        
        test.add(test2);
        test.add(test3);
        test.add(test4);
        
        result.add(lako);
        result.add(srednje);
        result.add(tesko);
        result.add(test);

        frame.setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(result);
        menuBar.add(help);
        
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DifficultyLevel izbor = new DifficultyLevel(frame);
                izbor.setVisible(true);
                izbor.setLocationRelativeTo(frame);
                frame.timer.stop();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
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
        lako2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Lako2");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        lako3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Lako3");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        lako4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Lako4");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        srednje2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Srednje2");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        srednje3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Srednje3");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        srednje4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Srednje4");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        tesko2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Tesko2");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        tesko3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Tesko3");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        tesko4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Tesko4");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        test2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Test2");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        test3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Test");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        test4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Test4");
                nova.setVisible(true);
                nova.setLocationRelativeTo(frame);
            }
        });
        
    }
}
