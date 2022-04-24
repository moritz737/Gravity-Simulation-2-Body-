import java.awt.*;

public class Object {
    //Gravitaionskonstante
    public double g = 6; //.6742*10^-12

    //Objekt hat eine Masse
    public double mass;
    //Objekt hat eine Position
    public int x;
    public int y;

    //resultierende Kraft
    public Vector2D resForce;
    //momentane Geschwindigkeit
    public Vector2D velocity;
    //momentane Beschleunigung
    public Vector2D acceleration;
    //Distanz für Datenanalyse:
    double dData;

    //Werte werden initialisiert, welche im Parameter übergeben weden
    public Object(double m, int x, int y, int initAx, int initAy){
        mass = m;
        this.x = x;
        this.y = y;
        resForce = new Vector2D();
        //initAx, initAy stellt die Anfangsgeschwindigkeit dar
        velocity = new Vector2D(initAx, initAy);
        acceleration = new Vector2D();
    }

    public void calculateForce(){
        //Distanz Parameter
        double distance;
        //Distanz in einzelne Komponenten aufgeteilt
        double xDist;
        double yDist;
        //Magnitude der Gravitationskraft
        double forceMag;

        //neuer Vektor für auf Objekt wirkende Gravitationskraft
        Vector2D totalForce = new Vector2D();

        //for Schleife zum Durchlaufen der Array List, in dem alle Objekte gespeichert sind
        for(int i = Display.objects.size()-1;i >= 0; i--){

            //lokale Objekt variable, die das andere Objekt darstellt
            Object o = Display.getObjects().get(i);

            //es wird geprüft, ob das lokale Objekt nicht das eigene Objekt ist
            if(!(o == this)){
                //Distanz komponenten werden berechnet
                xDist = o.getX() - x;
                yDist = o.getY() - y;
                //Distanz wird berechnet
                distance = Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
                //Magnitude der Kraft wird berechnet
                forceMag = g * ((this.mass * o.getMass()) / Math.pow(distance, 2));
                //Kraft wird in x, y Komponente aufgeteilt -> für Vektor
                double xComp = (forceMag * xDist / distance);
                double yComp = (forceMag * yDist / distance);
                //Gravitationsvektor wird durch Vektoraddition mit den Komponenten (im Vektor) berechnet
                totalForce.add(new Vector2D(xComp, yComp));
                //Distanz wird für Datenanalyse gespeichert
                dData = distance;
            }
        }
        //resultierende Kraft des Objektes ist Gravitationskraft
        this.resForce = totalForce;
        //Kraft wird auf Objekt übertragen
        applyForce();
    }

    //Kraft wird auf Objekt übertragen
    public void applyForce(){
        //beschleunigung wird durch resultierender Kraft und Masse berechnet -> F = m * a ,bzw. a = F/m
        this.acceleration.set((resForce.getX()/this.mass), (resForce.getY()/this.mass)); //a=F/m
        //Beschleunigung wird auf Geschwindigkeit übertragen
        this.velocity.add(acceleration);
        //Koordinaten werden durch Geschwindigkeitskomponenten berechnet
        this.x = this.x + velocity.getX();
        this.y = this.y + velocity.getY();
    }

    //Objekt wird auf JFrame sichtbar
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        //verschiedene Größen bezogen auf Masse, damit man unterscheiden kann
        if(this.mass > 1000){
            g2D.fillOval(getX(),getY(),25,25);
        }
        else{
            g2D.fillOval(getX(),getY(),10,10);
        }
    }

    //rechnet Geschwindigkeitskomponenten in Gesamtgeschwindigkeit um
    public double resVelo(double x, double y){
        double resVelocity = 0;
        resVelocity = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return resVelocity;
    }

    //get Methoden für x, y und Masse

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double getMass(){
        return this.mass;
    }
}
