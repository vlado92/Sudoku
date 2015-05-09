package sudoku;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.sqrt;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dugme extends JPanel implements MouseListener{
    private int index1;
    private int index2;
    private Random rand = new Random();
    private static int velicina = 9;
    private static int korijen = (int) sqrt(velicina);
    private JButton[][] Sudoku = new JButton[velicina][velicina];
    private final Font font = new Font("Arial", Font.PLAIN, 15);
    private static JLabel labela = new JLabel();
    public long vreme = System.currentTimeMillis();

    public static JLabel getLabela() {
        return labela;
    }
    
    public static int getVelicina() {
        return velicina;
    }

    public static int getKorijen() {
        return korijen;
    }
    
    public Dugme() {
    //<editor-fold defaultstate="collapsed" desc=" Generisanje Dugmadi ">
    
        int duzina = Prozor.getDuzina();
        int visina = Prozor.getVisina();
        setPreferredSize(new Dimension(duzina, visina));
        setLayout(null);
        setBackground(Color.white);
        for ( int i = 0; i < Sudoku[0].length; i++ )
            for ( int j = 0; j < Sudoku[0].length; j++ )
            {
                Sudoku[i][j] = new JButton(" ");
            }
        for(int i=0; i<velicina; i++)
            for(int j=0;j<velicina; j++)
            {   
                Sudoku[i][j].setFont(font);
                Sudoku[i][j].setBounds((Prozor.getDuzina())/velicina*j+3, (Prozor.getVisina())/velicina*i +3, Prozor.getDuzina()/velicina-3, (Prozor.getVisina())/velicina-3);
                Sudoku[i][j].setVisible(true);
                Sudoku[i][j].setEnabled(false);
                Sudoku[i][j].setFocusable(false);
                Sudoku[i][j].addMouseListener(this);
                addMouseListener(this);
                add(Sudoku[i][j]);
            }
        int broj1, broj2, broj3;
        broj1 = rand.nextInt(velicina+1);
        broj2 = rand.nextInt(velicina+1) + broj1;
        broj3 = rand.nextInt(velicina+1) + broj2;

        for(int i=0; i<velicina; i++)
            for(int j=0; j<velicina; j++)
                Sudoku[i][j].setText(""+((broj1 + korijen*(i%korijen)+(j+(i/korijen)+broj3)%korijen+(j/korijen+broj2)*korijen)%velicina+1));
    //</editor-fold>        
    //ovde raditi iteracije i razmjenjivati redove ili kolone
        mjesanje(Sudoku);
        brisanje(Sudoku, 1/*velicina*velicina - 25*/);
        ispis(Sudoku);
        kraj(Sudoku);
    }
    //mjesanje je moguce jos malo doraditi
    private void mjesanje(JButton[][] dugme){
    //<editor-fold defaultstate="collapsed" desc=" Algoritam mjesanja ovoga ">
        int broj1 = rand.nextInt(150);
    for(int i=0; i<broj1; i++)
    {
        String razmjena = new String();
        for(int blokovi=0; blokovi<3; blokovi++)
        {
            index1 = rand.nextInt(3);
            index2 = rand.nextInt(3);
            for(int kolone=0; kolone <velicina; kolone++)
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
            for(int redovi=0; redovi <velicina; redovi++)
            {
                razmjena = dugme[redovi][index1+3*blokovi].getText();
                dugme[redovi][index1+3*blokovi].setText(dugme[redovi][index2+3*blokovi].getText());
                dugme[redovi][index2+3*blokovi].setText(razmjena);
            }
        }
        // ovde bi bilo pozeljno da se mjenjaju blokovi malo
    }
}//</editor-fold>
    private void brisanje(JButton[][] dugme, int brisanje){
    //<editor-fold defaultstate="collapsed" desc=" brisanje clanova ">
    int[][] izbrisani = new int[brisanje][2];
    int zastava, i=0;
    for(;brisanje >0;){
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
            if(!"".equals(dugme[index1][index2].getText()))
            {
                dugme[index1][index2].setEnabled(true);
                dugme[index1][index2].setText(" ");
                brisanje--;
                i++;
            }
        }
        else if(zastava == 2)
            System.out.println("Ispalo je sranje");
        else
            System.out.println("nesto trece");
    }
}//</editor-fold>    
    private int ispitivanje(JButton[][] dugme, int redovi, int kolona, char t){
    //<editor-fold defaultstate="collapsed" desc=" Ispitivanje da li je moguce unijeti broj ">
String[] kolone = new String[9];
String[] red = new String[9];
String[] kocka = new String[9];
String te = "" + t;
int redP=2, redK=2, redKoc=0, prolaz=0;
System.out.println(kolona + " " + redovi);
for(int i=0; i<9; i++)
{
    kolone[i] = dugme[i][kolona].getText();
    red[i] = dugme[redovi][i].getText();
}
for(int i=0; i<3; i++)
    for(int j=0; j<3; j++)
        kocka[prolaz++] = dugme[i+ 3*((int) redovi/3)][j+3*((int)kolona/3)].getText();
for(int i=0; i<9; i++)
    if(red[i].equals(te))
    {
        redP = 1;
        break;
    }
    else
        redP=0;
    for(int i=0; i<9; i++)
    if(kolone[i].equals(te))
    {
        redK = 1;
        break;
    }
    else
        redK=0;
    for(int i = 0; i<9; i++)
        if(kocka[i] == "")
            System.out.print("0 ");
        else
            System.out.print(kocka[i]+ " ");
for(int i=0; i<9; i++)
    if(kocka[i].equals(te))
        {
        redKoc = 1;
        break;
        }
    else
        redKoc=0;
    
    if(redP== 0)
        System.out.println("Uredu je po redovima ");
    else
        System.out.println("Nije uredu po redovima");
    
    if(redK == 0)
        System.out.println("Uredu je po kolonama ");
    else
        System.out.println("Nije Uredu je po kolonama ");
    
    if(redKoc==0)
        System.out.print("Uredu je po kockama ");
    else
        System.out.println("NIJE Uredu je po kockama ");
    
    if(redP == 0 && redK == 0 && redKoc == 0)
        return 1;
    return 0;
}//</editor-fold>
    private void kraj(JButton[][] dugme){
    //<editor-fold defaultstate="collapsed" desc=" Ispituje da li su sva polja popunjena ">
    int p=2;
    for(int i=0; i<9; i++)
        for(int j=0; j<9; j++)
            if(dugme[i][j].getText() == " ")
            {
                p=1;
                break;
            }
            else
            {
                p=0;
            }
    if(p==0)
    {
        Dugme.labela.setText("Kraj Igre");
        JFrame nesto = new JFrame();
        
        Calendar krajnje = Calendar.getInstance();
        krajnje.add(Calendar.HOUR_OF_DAY, -(Menu.vrijeme.get(Calendar.HOUR_OF_DAY)));
        krajnje.add(Calendar.MINUTE, -(Menu.vrijeme.get(Calendar.MINUTE)));
        krajnje.add(Calendar.SECOND, -(Menu.vrijeme.get(Calendar.SECOND)));
        
        HighScore novi = new HighScore();
        novi.setVisible(true);
        novi.setScore("IME", krajnje);
        Menu.setNovaIgra(true);
        
    }
    else if (p==1)
        Dugme.labela.setText("Igra traje!");
    else
        Dugme.labela.setText("Odredjena greska");
}////</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Finkcija koja u konzolu ispisuje Sudoku">
    private void ispis(JButton[][] dugme){
    for(int i=0; i<9; i++)
    {
        for(int j=0; j<9; j++)
            if(dugme[i][j].getText() == " ")
              System.out.print("? ");
            else
                System.out.print(dugme[i][j].getText() + " ");
        System.out.println();
    }
}
    //</editor-fold>
@Override
public void paint(Graphics g) {
    super.paint(g);
    for(int i=0; i<korijen; i++)
        {
            for(int j=0; j<korijen; j++)
            g.drawRect(i*Prozor.getDuzina()/(korijen) + 2, j*(Prozor.getVisina())/korijen+ 2, Prozor.getDuzina()/korijen - 2, (Prozor.getVisina())/korijen - 2);
        }
    //ovde je potrebno ponovo osmisliti kako iscrtati sve ovo sa dugmadima
}

//<editor-fold defaultstate="collapsed" desc="Event Handling">
//vjerovatno dobro, ali treba jos ispitivanja
    @Override
    public void mouseClicked(MouseEvent e) {
        
        System.out.println(this.getMousePosition().toString());
        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                if(this.getMousePosition().x >= Sudoku[i][j].getX() && this.getMousePosition().x <=Sudoku[i][j].getWidth()*(j+1)
                    && this.getMousePosition().y >= Sudoku[i][j].getY() && this.getMousePosition().y <=Sudoku[i][j].getHeight()*(i+1))
                        if(Sudoku[i][j].isEnabled() == false)
                        {
                            System.out.println("nije moguce");
                            break;
                        }
                        else
                        {
                            System.out.println("MOGUCE");
                            final JTextField text = new JTextField();
                            text.setBounds(Sudoku[i][j].getBounds());
                            Sudoku[i][j].setVisible(false);
                            Sudoku[i][j].setText("");
                            text.setVisible(true);
                            text.setEnabled(true);
                            text.setFont(font);
                            add(text);
                            final int prvi = i;
                            final int drugi = j;
                            text.setHorizontalAlignment(JTextField.CENTER);
                            KeyListener t = new KeyListener() {               
                @Override
                public void keyTyped(KeyEvent e) {
                    if(e.getKeyChar() <= '9' && e.getKeyChar() >='1')
                    {
                        System.out.println(e.getKeyChar());
                        if(ispitivanje(Sudoku, prvi, drugi, e.getKeyChar()) == 1)
                        {
                            Sudoku[prvi][drugi].setText("" + e.getKeyChar());
                            text.setVisible(false);
                            Sudoku[prvi][drugi].setVisible(true);
                            ispis(Sudoku);
                            kraj(Sudoku);
                        }
                        text.setText("");
                    }
                    else if(e.getKeyChar() == '0')
                    {
                        Sudoku[prvi][drugi].setText(" ");
                        text.setVisible(false);
                        Sudoku[prvi][drugi].setVisible(true);
                        ispis(Sudoku);
                        text.setText("");
                        
                    }
                    else
                    {
                        e.consume();
                        text.setText("");
                    }
                }
                
                @Override
                public void keyPressed(KeyEvent e) {}
                @Override
                public void keyReleased(KeyEvent e) {}
            };
                            text.addKeyListener( t);
                            addKeyListener(t);
                            break;
                        }
    }
    
    @Override public void mouseExited(MouseEvent e){}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    //</editor-fold>
}
