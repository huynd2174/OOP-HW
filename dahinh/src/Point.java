import java.awt.*;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getPointX() {
        return x;
    }

    public double getPointY() {
        return y;
    }

    public void setPointX(double x) {
        this.x = x;
    }

    public void setPointY(double y) {
        this.y = y;
    }

    public void move(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }
}
