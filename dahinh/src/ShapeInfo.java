import java.awt.*;

public class ShapeInfo {
    private double x;
    private double y;
    private double width;
    private double height;
    private Color fillColor;

    public ShapeInfo(double x, double y, double width, double height, Color fillColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
    }

    public double getPointX() {
        return x;
    }

    public double getPointY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Color getFillColor() {
        return fillColor;
    }
}
