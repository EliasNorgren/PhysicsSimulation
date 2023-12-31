package GUI;

import PhysicsModel.Atom;
import PhysicsModel.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PhysicsGUI extends JFrame implements Listener {

    private CirclePanel panel;

    public PhysicsGUI (int width, int height) throws InterruptedException {
        super("Physics Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setVisible(true);

        this.panel = new CirclePanel();
        panel.setBackground(new Color(77,76,73));
        this.add(this.panel);

    }

    public void startGUI(int timerDelay){
        Timer t = new Timer(timerDelay, new RepaintListener(this));
        t.start();
    }

    @Override
    public void updateGUI(ArrayList<Atom> atoms) {
        this.panel.resetCircles();
        for (Atom a : atoms){
//            int radius = a.diameter / 2;
            this.panel.addCircle(new CirclePanel.Circle(a.x,a.y,a.color,a.diameter));
        }
        this.repaint();
    }


    public static class RepaintListener implements ActionListener{

        private final PhysicsGUI parent;

        public RepaintListener(PhysicsGUI parent){
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.parent.repaint();
        }
    }

}
