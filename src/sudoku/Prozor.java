package sudoku;

import javax.swing.JFrame;

public class Prozor extends JFrame {

    public Prozor() {
       Panel novi = new Panel();
       add(novi);
       pack();
       
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setResizable(false);
       setLocationRelativeTo(null);
       
       
    }
}
