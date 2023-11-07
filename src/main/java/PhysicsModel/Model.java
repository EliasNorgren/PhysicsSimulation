package PhysicsModel;

import GUI.PhysicsGUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {

    private HashMap<Color, HashMap<Color, Integer>> rules = new HashMap<>();
    private Listener updateGUIListener;
    private ArrayList<Atom> atoms = new ArrayList();

    private final int width;
    private final int height;


    public Model(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addRule(Color c1, Color c2, int g){
        this.rules.computeIfAbsent(c1, k -> new HashMap<>());
        this.rules.get(c1).put(c2,g*-1);
    }

    public void addAtom(Atom a){
        atoms.add(a);
    }

    public void start() throws InterruptedException {
        while (true){
            updateGUIListener.updateGUI(this.atoms);

            Thread.sleep(100);

                for (int i = 0; i < atoms.size(); i++) {
                    Atom a = atoms.get(i);
                    double fx = 0;
                    double fy = 0;
                    for (Atom b : atoms) {
                        int dx = a.x - b.x;
                        int dy = a.y - b.y;
                        int distance = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

                        if (distance > 80) {
                            int ruleG = this.rules.get(a.color).get(b.color);
                            double force = ((double) ruleG) / distance;
                            fx += force * dx;
                            fy += force * dy;
                        }
                    }
                    a.vx = (int)(a.vx + fx);
                    a.vy = (int)(a.vy + fy);
                    a.x += a.vx;
                    a.y += a.vy;

                    if (a.x < 0 || a.x > this.width){
                        a.vx *= -1;
                    }
                    if (a.y < 0 || a.y > this.height){
                        a.vy *= -1;
                    }

                    System.out.println(a);

                }
        }
    }


    public void addListener(Listener gui) {
        this.updateGUIListener = gui;
    }
}
