import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Display extends JPanel implements ActionListener {
    //Fenster, auf der die Simulation stattfindet, wird erstellt
    JFrame frame;
    //refresh rate:
    int delay = 50;

    //Array List in der die Objekte gespeichert sind
    public static ArrayList<Object> objects;

    //Objekte werden erstellt
    Object m1;
    Object m2;

    //timer für actionPerformed()
    Timer timer;

    //Konstruktor
    public Display(){
        objects = new ArrayList<>();
        setUp();
        //Passende Werte werden im Konstruktor übergeben
        m1 = new Object(50000,400,450,3,0);
        m2 = new Object(5,400,200,-25,0);

        //Objekte werden zur Arraylist hinzugefügt
        objects.add(m1);
        objects.add(m2);

        //Für Daten in der Konsole
        System.out.println("Zeit in " + delay + " ms");
        System.out.println("---------------------------");

    }

    public void setUp(){
        //größe des Fensters
        //int width = 800;
        //int height = 800;
        int width = 1000;
        int height = 1000;

        //Frame wird entsprechend angepasst
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setTitle("Stable Orbit");
        //timer wird entsprechen gesetzt -> delay in ms
        timer = new Timer(delay, this);
        //timer soll starten
        timer.start();
        //wichtig, damit Paint(Graphics g) funktioniert
        frame.add(this);
    }

    //alle Objekte werden auf Frame sichtbar
    public void paint(Graphics g){
        super.paint(g);
        for(Object o:objects){
            o.paint(g);
        }
    }

    //get Methode für Array List der Objekte
    public static ArrayList<Object> getObjects()
    {
        return objects;
    }

    //wird durch timer nach jedem delay ausgelöst
    @Override
    public void actionPerformed(ActionEvent e) {
        //für alle Objekte wird resultierende Kraft berechnet und jeweils übertragen
        for(Object o:objects){
            o.calculateForce();
        }

        //Daten (in Konsole):
            //Für Position:
        System.out.println("Position m1: x = " + m1.getX() +  " ; y =" + m1.getY());
        System.out.println("Position m2: x = " + m2.getX() +  " ; y =" + m2.getY());
        System.out.println(" ");

            //Für Distanz:
        System.out.println("distanz = " + m2.dData);
        System.out.println(" ");
            //Für Geschwindigkeit:
        double v1 = m1.resVelo(m1.getX(),m2.getY());
        double v2 = m2.resVelo(m2.getX(),m2.getY());
        double a2 = m2.resVelo(m2.acceleration.getX(),m2.acceleration.getY());
         double f2 = m2.resVelo(m2.resForce.getX(),m2.resForce.getY());
        System.out.println("Geschwindigkeit m1:" + v1);
        System.out.println("Geschwindigkeit m2:" + v2);
        System.out.println("ResForce m2:" + v2);
        System.out.println("Acceleration m2:" + v2);
        System.out.println("---------------------------");

        //ruft paint(Graphics g) Methode auf
        repaint();
    }
}
