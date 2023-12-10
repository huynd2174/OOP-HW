import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Layer extends JPanel {

    private List<Shape> shapes;

    public Layer() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Shape shape : shapes) {
            shape.draw(g2d);
        }
    }

    public void removeCircles() {
        shapes.removeIf(shape -> shape instanceof Circle);
    }

    public void removeDuplicates() {
        shapes = new ArrayList<>(new LinkedHashSet<>(shapes));
    }
}
