package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;
import javax.net.ssl.SSLEngineResult;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Prozor extends JFrame{
    private static final int duzina = 183*Dugme.getKorijen();
    private static  final int meni = 20;
    private static final int visina = 183*Dugme.getKorijen()+meni;

    public static int getVisina()   {return visina;}
    public static int getMeniSize() { return meni;}
    public static int getDuzina()   { return duzina;}
       
    public Prozor(){
        System.out.println(duzina+ " " + visina);
       this.setSize(duzina, visina);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setResizable(true);
       setLocationRelativeTo(null);
       this.setLayout(new FlowLayout());
	
    }
}
