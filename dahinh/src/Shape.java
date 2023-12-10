import java.awt.*;
import java.util.Objects;

public abstract class Shape {
    protected String color;
    protected boolean filled;
    protected Point velocity;

    public Shape() {
    }

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    public abstract double getArea();

    public abstract double getPerimeter();

    public String toString() {
        return "Shape[color=" + color + ",filled=" + filled + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shape shape = (Shape) o;
        return filled == shape.filled && Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, filled);
    }

    abstract void move();

    abstract void draw(Graphics2D g2d);

    abstract boolean intersects(Shape other);

    abstract boolean isSelected();

    abstract void setSelected(boolean selected);

    abstract void setFillColor(Color color);

    abstract boolean contains(Point point);
}
