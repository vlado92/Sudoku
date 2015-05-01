package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Sudoku {
public static void main(String[] args) {
    Prozor prozor = new Prozor();
    prozor.setVisible(true);
    Menu meni = new Menu(prozor);
    
//    igra();
    }

private static void igra(){
//<editor-fold defaultstate="collapsed" desc="Sudoku na konzoli">
    int[][] Sudoku;
    Sudoku = new int[9][9];
    for ( int i = 0; i < Sudoku[0].length; i++ )
    for ( int j = 0; j < Sudoku[0].length; j++ )
    {
        Sudoku[i][j] = 0;
    }
    Random rand = new Random();
    int broj1, broj2, broj3, index1, index2, index3;
    
    broj1 = rand.nextInt(10);
    broj2 = rand.nextInt(10) + broj1;
    broj3 = rand.nextInt(10) + broj2;

    for(int i=0; i<9; i++)
        for(int j=0; j<9; j++)
            Sudoku[i][j] = (broj1 + 3*(i%3)+(j+(i/3)+broj3)%3+(j/3+broj2)*3)%9+1;
    //ovde raditi iteracije i razmjenjivati redove ili kolone
    printanje(Sudoku);
    mjesanje(Sudoku);
    System.out.println("IZMJESANO");
    printanje(Sudoku);
    broj1 = rand.nextInt(80);
    brisanje(Sudoku, broj1);
    printanje(Sudoku);
}//</editor-fold>
private static void mjesanje(int[][] dugme){
//<editor-fold defaultstate="collapsed" desc="Mjesanje Sudoku-a">
    Random rand = new Random();
    int broj1 = rand.nextInt(150);
    int index1, index2;
    for(int i=0; i<broj1; i++)
    {
        int razmjena;
        for(int blokovi=0; blokovi<3; blokovi++)
        {
            index1 = rand.nextInt(3);
            index2 = rand.nextInt(3);
            for(int kolone=0; kolone <9; kolone++)
            {
                razmjena = dugme[index1+3*blokovi][kolone];
                dugme[index1+3*blokovi][kolone] = dugme[index2+3*blokovi][kolone];
                dugme[index2+3*blokovi][kolone] = razmjena;
            }
        }
        for(int blokovi=0; blokovi<3; blokovi++)
        {
            index1 = rand.nextInt(3);
            index2 = rand.nextInt(3);
            for(int redovi=0; redovi <9; redovi++)
            {
                razmjena = dugme[redovi][index1+3*blokovi];
                dugme[redovi][index1+3*blokovi]= dugme[redovi][index2+3*blokovi];
                dugme[redovi][index2+3*blokovi] = razmjena;
            }
        }
    }
}//</editor-fold>
private  static  void printanje(int[][] dugme){
//<editor-fold defaultstate="collapsed" desc="Printanje u konzolu">
    for(int i = 0; i<dugme.length; i++)
    {
        if(i%3==0)
            System.out.println();
        for(int j=0; j<dugme[i].length; j++)
        {
            if(j%3==0)
                System.out.print("\t");
            if(dugme[i][j]!= 0)
                System.out.print(dugme[i][j]);
            else
                System.out.print("_ ");
        }
        System.out.println();
    }
}//</editor-fold>
private static void brisanje(int[][] dugme, int brisanje){
//<editor-fold defaultstate="collapsed" desc="brisanje clanova">
        System.out.println("Bice izbrisano " + brisanje+" brojeva.");
        int[][] izbrisani = new int[brisanje][2];
        int zastava, index1, index2,i=0;
        Random rand = new Random();
        do
        {
                zastava =2;
                index1 = rand.nextInt(9);
                index2 = rand.nextInt(9);
                izbrisani[i][0]=index1;
                izbrisani[i][1]=index2;
                if(i>=1)
                {
                    for(int k=0; k<=i;k++){
                    if(izbrisani[k][0]!=izbrisani[i][0] && izbrisani[k][1]!=izbrisani[i][1])
                    {
                        zastava = 1;
                        break;
                    }
                    else
                        zastava = 0;
                }
                }
                else
                   zastava =1;
                if(zastava == 1){
                    if(dugme[index1][index2]!= 0)
                    {
                        dugme[index1][index2] = 0;
                        brisanje--;
                        i++;
                    }
                }
                else if(zastava == 2)
                    System.out.println("Ispalo je sranje");
                else
                    System.out.println("nesto trece");
                
        }while(brisanje>0);   
    }//</editor-fold>
}
