package PhysicsModel;

import java.awt.*;

public class Atom {
    public int x;
    public int y;
    public int vx;
    public int vy;
    public final double weight;

    public final Color color;
    public final int diameter;

    public Atom(int x, int y, int vy, int vx, double weight, Color color, int diameter){
        this.x = x;
        this.y = y;
        this.vy = vy;
        this.vx = vx;
        this.weight = weight;
        this.color = color;
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return "color: "+ this.color.toString() + " x: " + x + " y: " + y + " vx: " + vx + " vy: " + vy + " weight: " + this.weight;
    }
}
