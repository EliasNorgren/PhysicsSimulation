package PhysicsModel;

import GUI.PhysicsGUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model {

    private HashMap<Color, HashMap<Color, Integer>> rules = new HashMap<>();
    private Listener updateGUIListener;
    private ArrayList<Atom> atoms = new ArrayList();

    private final int width;
    private final int height;

    private final int maxSpeed = 1;

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

    public void update() throws InterruptedException {
            updateGUIListener.updateGUI(this.atoms);

                for (int i = 0; i < atoms.size(); i++) {
                    Atom a = atoms.get(i);
                    double fx = 0;
                    double fy = 0;
                    for (Atom b : atoms) {
                        if (a == b){
                            continue;
                        }
                        int dx = a.x - b.x;
                        int dy = a.y - b.y;
                        int distance = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
//                        System.out.println(distance);
                        if (distance > 0 && distance < 80) {
                            int ruleG = this.rules.get(a.color).get(b.color);
                            double force = ((double) ruleG) / distance;
                            fx += force * dx;
                            fy += force * dy;
                        }
                    }

                    if (Math.abs(a.vx) > this.maxSpeed){
                        a.vx = a.vx > 0 ? this.maxSpeed : this.maxSpeed * -1;
                    }else{
                        a.vx = (int) ((a.vx + fx) * 0.5);
                    }
                    if (Math.abs(a.vy) > this.maxSpeed){
                        a.vy = a.vy > 0 ? this.maxSpeed : this.maxSpeed * -1;
                    }else{
                        a.vy = (int)((a.vy + fy) * 0.5);
                    }

                    a.x += a.vx;
                    a.y += a.vy;

                    if (a.x < 0){
                        a.x = this.width - 1;
                    }else if (a.x > this.width){
                        a.x = 1;
                    }
                    if (a.y < 0){
                        a.y = this.height - 1;
                    }else if (a.y > this.height){
                        a.y = 1;
                    }
                }
        }



    public void addListener(Listener gui) {
        this.updateGUIListener = gui;
    }

    public void logStateToFile(String logPath) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(logPath, false));

        for (Map.Entry<Color, HashMap<Color, Integer>> entry : this.rules.entrySet()){
            Color c1 = entry.getKey();

            HashMap<Color, Integer> map = entry.getValue();

            for (Map.Entry<Color, Integer> entry2 : map.entrySet()){
                String write = "Rule " + c1.toString() + " -> " + entry2.getKey().toString() + " = " + entry2.getValue();
                System.out.println(write);
                writer.write(write);
                writer.newLine();
            }
        }

        writer.close();
    }
}
