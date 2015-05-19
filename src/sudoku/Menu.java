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

    public Menu(final Prozor novi) {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu pomoc = new JMenu("Help");
        JMenu rezultati = new JMenu("Result");

        JMenuItem izlaz = new JMenuItem("Exit");
        JMenuItem novaIgra = new JMenuItem("New Game");

        JMenuItem lako = new JMenuItem("Easy");
        JMenuItem srednje = new JMenuItem("Medium");
        JMenuItem tesko = new JMenuItem("Hard");
        JMenuItem test = new JMenuItem("Test");

        pomoc.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Pomoc nova = new Pomoc();
                nova.setVisible(true);
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
            }
        });
        srednje.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Srednje");
                nova.setVisible(true);
            }
        });
        tesko.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Tesko");
                nova.setVisible(true);
            }
        });
        test.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HighScore nova = new HighScore("Tesko");
                nova.setVisible(true);
            }
        });

        izlaz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        novaIgra.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IzborTezine izbor = new IzborTezine(novi);
                izbor.setVisible(true);
            }
        });

        file.add(novaIgra);
        file.add(izlaz);

        rezultati.add(lako);
        rezultati.add(srednje);
        rezultati.add(tesko);
        rezultati.add(test);

        novi.setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(rezultati);
        menuBar.add(pomoc);
    }
}
