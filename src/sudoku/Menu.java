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
    public static JMenuItem novaIgra = new JMenuItem("New Game");
    public static Calendar vrijeme = Calendar.getInstance();
    public static void setNovaIgra(boolean novaIgra) {
        Menu.novaIgra.setEnabled(novaIgra);
    }
    
    public Menu(final Prozor novi) {
            JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu pomoc = new JMenu("Help");
        JMenu rezultati = new JMenu("Result");
        JMenuItem izlaz = new JMenuItem("Exit");
        
        pomoc.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                   Pomoc nova = new Pomoc();    
                   nova.setVisible(true);
                }

                @Override public void mousePressed(MouseEvent e) {}
                @Override public void mouseReleased(MouseEvent e) {}
                @Override public void mouseEntered(MouseEvent e) {}
                @Override public void mouseExited(MouseEvent e) {}
            }); 
        rezultati.addMouseListener(new MouseListener() {

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
        
        JMenuItem opcije = new JMenuItem("Options");
        opcije.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                           Pomoc nova = new Pomoc();    
                   nova.setVisible(true);
            }
        });
        
        
        file.add(novaIgra);
        file.add(opcije);
        file.add(izlaz);
        novi.setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(rezultati);
        menuBar.add(pomoc);
    }
}
