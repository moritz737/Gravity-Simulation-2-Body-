public class Vector2D {

    //zweidimensionaler Vektor besteht aus x und y
    public double x;
    public double y;

    //falls nichts übergeben wird: x und y sind null
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    //bei erzeugen des Vektors wird x und y initialisiert
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //aktueller Vektor wird als neuer Vektor v gesetzt
    public Vector2D(Vector2D v) {
        set(v);
    }

    //aktuelle werte werden durch neue ersetzt
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //werte des aktuellen Vekotors werden auf die des neuen gesetzt
    public void set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    //x und y sind null
    public void setZero() {
        x = 0;
        y = 0;
    }

    //x Wert bekommen
    public int getX() {
        return (int) x;
    }

    // y Wert bekommen
    public int getY(){
        return (int) y;
    }

    //Vektor addition
    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }

    //x und y Wert wird als String ausgegeben
    @Override
    public String toString() {
        //return "[" + x + ", " + y + "]";
        return "" + x + " ; " + y + "";
    }
}
