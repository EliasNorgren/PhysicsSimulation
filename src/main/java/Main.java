import GUI.CirclePanel;
import GUI.PhysicsGUI;
import PhysicsModel.Atom;
import PhysicsModel.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.PrivilegedExceptionAction;
import java.util.HashSet;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        int width = 1870;
        int height = 1050;
        Model model = new Model(width, height);
        PhysicsGUI gui = new PhysicsGUI(width, height);
        model.addListener(gui);

        Random rnd = new Random();

        HashSet<Color> colors = new HashSet<>();
        colors.add(Color.red);
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.YELLOW);

        int maxN = 3000;

        for (Color c : colors){
            for (int i = 0; i < rnd.nextInt(100, maxN); i++ ){
                int x = rnd.nextInt(0, width);
                int y = rnd.nextInt(0, height);
                Atom a = new Atom(x,y, 0, 0, 0, c, 10);
                model.addAtom(a);
            }

        }

        int lowerG = -4;
        int upperG = 4;



        for (Color c1 : colors){
            for (Color c2 : colors){
                int G = 0;
                while(G == 0){
                    G = rnd.nextInt(lowerG,upperG);
                }
                model.addRule(c1, c2, G);
            }
        }


        model.logStateToFile("log");

        Timer t = new Timer(50, e -> {
            try {
                model.update();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        t.start();

    }
}
