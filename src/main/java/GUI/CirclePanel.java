package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CirclePanel extends JPanel {

    private ArrayList<Circle> circles = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Circle c : circles) {
//            System.out.println(c.x + "  " + c.y);
            g2d.setColor(c.color); // Set circle color
            g2d.fillOval(c.x, c.y, c.diameter, c.diameter); // Draw circle
        }

    }

    public void addCircle(Circle circ) {
        this.circles.add(circ);
    }

    public void resetCircles() {
        this.circles = new ArrayList<>();
    }

    public static class Circle {
        int x;
        int y;
        Color color;
        int diameter;

        public Circle(int x, int y, Color color, int diameter){
            this.x = x;
            this.y = y;
            this.color = color;
            this.diameter = diameter;
        }
    }
}