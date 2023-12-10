import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Rectangle extends Shape {
    private Point topLeft;
    private double width;
    private double length;
    private Color fillColor;
    private boolean selected;
    private Point velocity; // Thêm thuộc tính velocity

    public Rectangle() {
        this.velocity = new Point(0, 0); // Initialize velocity with default values
    }

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
        this.velocity = new Point(0, 0);
    }

    public Rectangle(double width, double length, String color, boolean filled, Point velocity) {
        super(color, filled);
        this.width = width;
        this.length = length;
        this.velocity = velocity;
    }

    public Rectangle(Point topLeft, double width, double length, String color, boolean filled, Point velocity) {
        super(color, filled);
        this.topLeft = topLeft;
        this.width = width;
        this.length = length;
        this.velocity = velocity;
    }

    public Rectangle(ShapeInfo shapeInfo, Point velocity) {
        this.topLeft = new Point(shapeInfo.getPointX(), shapeInfo.getPointY());
        this.width = shapeInfo.getWidth();
        this.length = shapeInfo.getHeight();
        this.fillColor = shapeInfo.getFillColor();
        this.velocity = velocity;
        this.selected = false;
    }

    public Rectangle(ShapeInfo shapeInfo) {
        this.topLeft = new Point(shapeInfo.getPointX(), shapeInfo.getPointY());
        this.width = shapeInfo.getWidth();
        this.length = shapeInfo.getHeight();
        this.fillColor = shapeInfo.getFillColor();
        this.selected = false;
        this.velocity = new Point(0, 0);
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getArea() {
        return length * width;
    }

    public double getPerimeter() {
        return (length + width) * 2;

    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }


    @Override
    public void move() {
        topLeft = new Point(topLeft.getPointX() + velocity.getPointX(), topLeft.getPointY() + velocity.getPointY());
    }
    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(fillColor);
        Rectangle2D.Double rectangle = new Rectangle2D.Double(topLeft.getPointX(), topLeft.getPointY(), width, length);
        g2d.fill(rectangle);

        if (selected) {
            g2d.setColor(Color.BLACK);
            g2d.draw(rectangle);
        }
    }

    @Override
    public boolean intersects(Shape other) {
        if (other instanceof Rectangle) {
            Rectangle otherRectangle = (Rectangle) other;
            return topLeft.getPointX() < otherRectangle.topLeft.getPointX() + otherRectangle.width &&
                    topLeft.getPointX() + width > otherRectangle.topLeft.getPointX() &&
                    topLeft.getPointY() < otherRectangle.topLeft.getPointY() + otherRectangle.length &&
                    topLeft.getPointY() + length > otherRectangle.topLeft.getPointY();
        } else if (other instanceof Circle) {
            Circle circle = (Circle) other;
            return false;
        }
        return false;
    }

    @Override
    public boolean contains(Point point) {
        return point.getPointX() >= topLeft.getPointX() && point.getPointX() <= topLeft.getPointX() + width &&
                point.getPointY() >= topLeft.getPointY() && point.getPointY() <= topLeft.getPointY() + length;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Rectangle)) {
            return false;
        }
        Rectangle r = (Rectangle) o;
        return Double.compare(this.width, r.width) == 0
                && Double.compare(this.length, r.length) == 0;
    }

    @Override
    public int hashCode() {
        int hashTopLeft = this.topLeft.hashCode();
        int hashWidth = Double.hashCode(this.width);
        int hashLength = Double.hashCode(this.length);
        return hashTopLeft ^ hashWidth ^ hashLength;
    }


    @Override
    public String toString() {
        return String.format("Rectangle[topLeft=(%.1f,%.1f)"
                        + ",width=%.1f,length=%.1f,color=%s,filled=%b]",
                this.topLeft.getPointX(),
                this.topLeft.getPointY(),
                this.width,
                this.length,
                this.color,
                this.filled);
    }

}
