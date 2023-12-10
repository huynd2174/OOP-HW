import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class DrawingPanel extends JPanel {
    private List<Shape> shapes;

    public DrawingPanel() {
        shapes = new ArrayList<>();

        Timer timer = new Timer(50, e -> {
            moveShapes();
            checkCollisions();
            repaint();
        });
        timer.start();

        setFocusable(true);
        requestFocusInWindow();
    }

    public void removeSelectedShape() {
        shapes.removeIf(Shape::isSelected);
    }

    public void removeCircles() {
        shapes.removeIf(shape -> shape instanceof Circle);
    }

    public void removeDuplicates() {
        shapes = new ArrayList<>(new LinkedHashSet<>(shapes));
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void moveShapes() {
        for (Shape shape : shapes) {
            shape.move();
        }
    }

    private void checkCollisions() {
        checkWindowCollisions();

        // Check collision among shapes
        for (int i = 0; i < shapes.size(); i++) {
            for (int j = i + 1; j < shapes.size(); j++) {
                Shape shape1 = shapes.get(i);
                Shape shape2 = shapes.get(j);

                if (shape1.intersects(shape2)) {
                    handleCollision(shape1, shape2);
                    adjustPositions(shape1, shape2);
                }
            }
        }
    }

    private void checkWindowCollisions() {
        for (Shape shape : shapes) {
            handleWindowCollision(shape);
        }
    }

    private void handleWindowCollision(Shape shape) {
        if (shape instanceof Circle) {
            handleWindowCollision((Circle) shape);
        } else if (shape instanceof Rectangle) {
            handleWindowCollision((Rectangle) shape);
        }
    }

    private void handleWindowCollision(Circle circle) {
        double x = circle.getCenter().getPointX();
        double y = circle.getCenter().getPointY();
        int radius = (int) circle.getRadius();

        double xVelocity = circle.getVelocity().getPointX();
        double yVelocity = circle.getVelocity().getPointY();

        if (x - radius < 0 || x + radius > getWidth()) {
            circle.getVelocity().setPointX(-xVelocity);
            circle.getCenter().setPointX(Math.max(radius, Math.min(getWidth() - radius, x)));
        }

        if (y - radius < 0 || y + radius > getHeight()) {
            circle.getVelocity().setPointY(-yVelocity);
            circle.getCenter().setPointY(Math.max(radius, Math.min(getHeight() - radius, y)));
        }
    }

    private void handleWindowCollision(Rectangle rectangle) {
        double x = rectangle.getTopLeft().getPointX();
        double y = rectangle.getTopLeft().getPointY();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getLength();

        double xVelocity = rectangle.getVelocity().getPointX();
        double yVelocity = rectangle.getVelocity().getPointY();

        if (x < 0 || x + width > getWidth()) {
            rectangle.getVelocity().setPointX(-xVelocity);
            rectangle.getTopLeft().setPointX(Math.max(0, Math.min(getWidth() - width, x)));
        }

        if (y < 0 || y + height > getHeight()) {
            rectangle.getVelocity().setPointY(-yVelocity);
            rectangle.getTopLeft().setPointY(Math.max(0, Math.min(getHeight() - height, y)));
        }
    }

    private void adjustPositions(Shape shape1, Shape shape2) {
        shape2.move();
    }

    private void handleCollision(Shape shape1, Shape shape2) {
        // Handle collision logic here
        shape1.setFillColor(Color.RED);
        shape2.setFillColor(Color.RED);

        if (shape1 instanceof Circle && shape2 instanceof Circle) {
            handleCircleCircleCollision((Circle) shape1, (Circle) shape2);
        } else if (shape1 instanceof Rectangle && shape2 instanceof Rectangle) {
            handleRectangleRectangleCollision((Rectangle) shape1, (Rectangle) shape2);
        } else if (shape1 instanceof Circle && shape2 instanceof Rectangle) {
            handleCircleRectangleCollision((Circle) shape1, (Rectangle) shape2);
        } else if (shape1 instanceof Rectangle && shape2 instanceof Circle) {
            handleCircleRectangleCollision((Circle) shape2, (Rectangle) shape1);
        }
    }

    private void handleCircleCircleCollision(Circle circle1, Circle circle2) {
        // Handle collision logic between two circles
        // You can update the velocity or perform other actions as needed
        circle1.getVelocity().setPointX(-circle1.getVelocity().getPointX());
        circle1.getVelocity().setPointY(-circle1.getVelocity().getPointY());

        circle2.getVelocity().setPointX(-circle2.getVelocity().getPointX());
        circle2.getVelocity().setPointY(-circle2.getVelocity().getPointY());
    }

    private void handleRectangleRectangleCollision(Rectangle rectangle1, Rectangle rectangle2) {
        rectangle1.getVelocity().setPointX(-rectangle1.getVelocity().getPointX());
        rectangle1.getVelocity().setPointY(-rectangle1.getVelocity().getPointY());

        rectangle2.getVelocity().setPointX(-rectangle2.getVelocity().getPointX());
        rectangle2.getVelocity().setPointY(-rectangle2.getVelocity().getPointY());
    }

    private void handleCircleRectangleCollision(Circle circle, Rectangle rectangle) {
        Point circleCenter = circle.getCenter();
        double circleRadius = circle.getRadius();
        Point rectangleTopLeft = rectangle.getTopLeft();
        double rectangleWidth = rectangle.getWidth();
        double rectangleHeight = rectangle.getLength();

        double closestX = clamp(circleCenter.getPointX(), rectangleTopLeft.getPointX(), rectangleTopLeft.getPointX() + rectangleWidth);
        double closestY = clamp(circleCenter.getPointY(), rectangleTopLeft.getPointY(), rectangleTopLeft.getPointY() + rectangleHeight);

        double distanceX = circleCenter.getPointX() - closestX;
        double distanceY = circleCenter.getPointY() - closestY;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distance < circleRadius) {
            double overlap = circleRadius - distance;

            double normalX = distanceX / distance;
            double normalY = distanceY / distance;

            circle.getCenter().move(overlap * normalX, overlap * normalY);

            double dotProduct = 2 * (circle.getVelocity().getPointX() * normalX + circle.getVelocity().getPointY() * normalY);
            double reflectX = circle.getVelocity().getPointX() - dotProduct * normalX;
            double reflectY = circle.getVelocity().getPointY() - dotProduct * normalY;

            circle.getVelocity().setPointX(reflectX);
            circle.getVelocity().setPointY(reflectY);

            rectangle.getVelocity().setPointX(-rectangle.getVelocity().getPointX());
            rectangle.getVelocity().setPointY(-rectangle.getVelocity().getPointY());
        }
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Shape shape : shapes) {
            shape.draw(g2d);
        }
    }
}
