import GUI.CirclePanel;
import GUI.PhysicsGUI;
import PhysicsModel.Atom;
import PhysicsModel.Model;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int width = 1000;
        int height = 1000;
        Model model = new Model(width, height);
        PhysicsGUI gui = new PhysicsGUI(width, height);
        model.addListener(gui);
        model.addAtom(new Atom(100, 500, 1, -1, 1, Color.red, 50));
        model.addAtom(new Atom(100, 100, -1,1,1.5,Color.blue, 50));
        model.addAtom(new Atom(300, 300, -1,1,1.5,Color.blue, 50));
        model.addAtom(new Atom(600, 300, -1,1,1.5,Color.red, 50));
        model.addAtom(new Atom(600, 300, -1,1,1.5,Color.green, 50));
        model.addAtom(new Atom(600, 1000, -1,1,1.5,Color.green, 50));



        model.addRule(Color.red, Color.blue, 1);
        model.addRule(Color.red, Color.red, 0);
        model.addRule(Color.red, Color.green, -1);


        model.addRule(Color.blue, Color.red, 1);
        model.addRule(Color.blue, Color.blue, 0);
        model.addRule(Color.blue, Color.green, -1);

        model.addRule(Color.green, Color.red, 1);
        model.addRule(Color.green, Color.blue, 1);
        model.addRule(Color.green, Color.green, 1);



        gui.startGUI(100);
        model.start();

    }
}
