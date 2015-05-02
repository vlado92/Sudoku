package sudoku;

import java.awt.FlowLayout;
import javax.swing.JFrame;

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
