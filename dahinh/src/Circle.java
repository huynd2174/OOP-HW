import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Circle extends Shape {
    private Point center;
    private double radius;
    private Point velocity;
    private Color fillColor;
    private boolean selected;
    public static final double PI = Math.PI;

    public Circle() {

    }

    public Circle(double radius) {
        this.radius = radius;
        this.center = new Point(0, 0);
        this.velocity = new Point(0, 0);
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
        this.velocity = new Point(0, 0);
    }

    public Circle(Point center, double radius, String color, boolean filled, Point velocity) {
        super(color, filled);
        this.center = center;
        this.radius = radius;
        this.velocity = velocity;
    }

    public Circle(ShapeInfo shapeInfo, Point velocity) {
        this.center = new Point(shapeInfo.getPointX() + shapeInfo.getWidth() / 2, shapeInfo.getPointY() + shapeInfo.getHeight() / 2);
        this.radius = Math.min(shapeInfo.getWidth(), shapeInfo.getHeight()) / 2;
        this.fillColor = shapeInfo.getFillColor();
        this.velocity = velocity;
        this.selected = false;
    }

    public Circle(ShapeInfo shapeInfo) {
        this.center = new Point(shapeInfo.getPointX() + shapeInfo.getWidth() / 2, shapeInfo.getPointY() + shapeInfo.getHeight() / 2);
        this.radius = Math.min(shapeInfo.getWidth(), shapeInfo.getHeight()) / 2;
        this.fillColor = shapeInfo.getFillColor();
        this.velocity = new Point(0, 0);
        this.selected = false;
    }

    @Override
    public void move() {
        if (velocity != null) {
            center.setPointX(center.getPointX() + velocity.getPointX());
            center.setPointY(center.getPointY() + velocity.getPointY());
        }
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(fillColor);
        Ellipse2D.Double ellipse = new Ellipse2D.Double(
                center.getPointX() - radius,
                center.getPointY() - radius,
                2 * radius,
                2 * radius);
        g2d.fill(ellipse);

        if (selected) {
            g2d.setColor(Color.BLACK);
            g2d.draw(ellipse);
        }
    }

    @Override
    public boolean intersects(Shape other) {
        if (other instanceof Circle) {
            Circle otherCircle = (Circle) other;
            double distance = Math.sqrt(Math.pow(center.getPointX() - otherCircle.center.getPointX(), 2) +
                    Math.pow(center.getPointY() - otherCircle.center.getPointY(), 2));
            return distance <= radius + otherCircle.radius;
        }
        // Handle intersection with other shapes if needed
        return false;
    }

    @Override
    public boolean contains(Point point) {
        double distance = Math.sqrt(Math.pow(center.getPointX() - point.getPointX(), 2) +
                Math.pow(center.getPointY() - point.getPointY(), 2));
        return distance <= radius;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Circle[center=(%.1f,%.1f),radius=%.1f,color=%s,filled=%b]",
                center.getPointX(), center.getPointY(), radius, color, filled);
    }

    // Thêm getter và setter cho velocity
    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }
}
