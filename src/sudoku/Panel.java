
package sudoku;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Random;

public class Panel extends JPanel {
    private final int visina = 183*3;
    private final int duzina = 183*3;

    public Panel() {
        setPreferredSize(new Dimension(duzina, visina));
    setLayout(null);
    setBackground(Color.WHITE);
    JButton[][] Sudoku;
    Sudoku = new JButton[9][9];
    for ( int i = 0; i < Sudoku[0].length; i++ )
    for ( int j = 0; j < Sudoku[0].length; j++ )
    {
        Sudoku[i][j] = new JButton("");
    }
    for(int i=0; i<9; i++)
        for(int j=0;j<9; j++)
        {
            Sudoku[i][j].setBounds(duzina/9*j+3, visina/9*i+3, duzina/9-6, visina/9-6);
            Sudoku[i][j].setVisible(true);
            add(Sudoku[i][j]);
        }

    Random rand = new Random();
    int broj1, broj2, broj3, index1, index2, index3;
    
    broj1 = rand.nextInt(10);
    broj2 = rand.nextInt(10) + broj1;
    broj3 = rand.nextInt(10) + broj2;

    for(int i=0; i<9; i++)
        for(int j=0; j<9; j++)

            Sudoku[i][j].setText(""+((broj1 + 3*(i%3)+(j+(i/3)+broj3)%3+(j/3+broj2)*3)%9+1));
    //ovde raditi iteracije i razmjenjivati redove ili kolone
    mjesanje(Sudoku);
    printanje(Sudoku);
    /*
    int brisanje = 0;
    do
    {
        index1 = rand.nextInt(9);
        index2 = rand.nextInt(9);
        Sudoku[index1][index2].setText("");
        brisanje--;
    }while(brisanje>0);*/
}

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i=0; i<visina; i=i+(visina/9))
        {
//            g.drawLine(0, i, duzina, i);
    //          g.drawLine(i, 0, i, visina);
            g.drawRect(i*3, 0, duzina/3, visina/3);
            g.drawRect(i*3, duzina/3, duzina/3, visina/3);
            g.drawRect(i*3, 2*duzina/3, duzina/3, visina/3);
        }
    }
    
    private void mjesanje(JButton[][] dugme){
        Random rand = new Random();
        int broj1 = rand.nextInt(150);
        int index1, index2;
        for(int i=0; i<broj1; i++)
        {
            String razmjena = new String();
            for(int blokovi=0; blokovi<3; blokovi++)
            {
                index1 = rand.nextInt(3);
                index2 = rand.nextInt(3);
                for(int kolone=0; kolone <9; kolone++)
                {
                    razmjena = dugme[index1+3*blokovi][kolone].getText();
                    dugme[index1+3*blokovi][kolone].setText(dugme[index2+3*blokovi][kolone].getText());
                    dugme[index2+3*blokovi][kolone].setText(razmjena);
                }
            }
            for(int blokovi=0; blokovi<3; blokovi++)
            {
                index1 = rand.nextInt(3);
                index2 = rand.nextInt(3);
                for(int redovi=0; redovi <9; redovi++)
                {
                    razmjena = dugme[redovi][index1+3*blokovi].getText();
                    dugme[redovi][index1+3*blokovi].setText(dugme[redovi][index2+3*blokovi].getText());
                    dugme[redovi][index2+3*blokovi].setText(razmjena);
                }
            }
            // ovde bi bilo pozeljno da se mjenjaju blokovi malo
        }
    }
    void printanje(JButton[][] dugme){
        for(int i = 0; i<dugme.length; i++)
        {
            if(i%3==0)
            System.out.println();
            for(int j=0; j<dugme[i].length; j++)
            {
                if(j%3==0)
                    System.out.print("\t");
                    System.out.print(dugme[i][j]);
            }
            System.out.println();
        }
    }
}