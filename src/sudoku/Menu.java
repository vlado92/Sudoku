/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Username
 */
public class Menu {
    public static JMenuItem novaIgra = new JMenuItem("Nova igra");
    public static Calendar vrijeme = Calendar.getInstance();
    public static void setNovaIgra(boolean novaIgra) {
        Menu.novaIgra.setEnabled(novaIgra);
    }
    
    public Menu(final Prozor novi) {
            JMenuBar menuBar = new JMenuBar();
        JMenu opcije = new JMenu("Opcije");
        menuBar.add(opcije);
        JMenu pomoc = new JMenu("Pomoć");
        menuBar.add(pomoc);
        pomoc.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                   HighScore nova = new HighScore();
                   nova.setVisible(true);
                }

                @Override public void mousePressed(MouseEvent e) {}
                @Override public void mouseReleased(MouseEvent e) {}
                @Override public void mouseEntered(MouseEvent e) {}
                @Override public void mouseExited(MouseEvent e) {}
            }); 
        JMenuItem izlaz = new JMenuItem("Exit");
        izlaz.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        novaIgra.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            Dugme dugme = new Dugme();
            novi.add(dugme);
            novi.pack();
            vrijeme = Calendar.getInstance();
            novaIgra.setEnabled(false);
            
            }
        });        
        opcije.add(novaIgra);
        opcije.add(izlaz);
        novi.setJMenuBar(menuBar);
        
    }
}
