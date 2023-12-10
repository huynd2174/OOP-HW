import java.awt.*;
import java.util.Objects;

public class Square extends Rectangle {
    public Square() {
        super();
    }

    public Square(double side) {
        super(side, side);
    }

    public Square(double side, String color, boolean filled) {
        super(new ShapeInfo(0, 0, side, side, Color.BLACK), new Point(0, 0));
        setFillColor(Color.getColor(color));
        setFilled(filled);
    }

    public Square(Point topLeft, double side, String color, boolean filled) {
        super(new ShapeInfo(topLeft.getPointX(), topLeft.getPointY(), side, side, Color.BLACK), new Point(0, 0));
        setFillColor(Color.getColor(color));
        setFilled(filled);
    }

    public double getSide() {
        return getWidth();
    }

    public void setSide(double side) {
        setWidth(side);
        setLength(side);
    }

    @Override
    public void setWidth(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    @Override
    public void setLength(double side) {
        super.setWidth(side);
        super.setLength(side);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(square.getSide(), getSide()) == 0 &&
                getColor().equals(square.getColor()) &&
                isFilled() == square.isFilled();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSide(), getColor(), isFilled());
    }

    @Override
    public String toString() {
        return String.format("Square[topLeft=(%.1f,%.1f),side=%.1f,color=%s,filled=%b]",
                getTopLeft().getPointX(),
                getTopLeft().getPointY(),
                getSide(),
                getColor(),
                isFilled());
    }
}
