import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class DrawingMap extends JFrame {
    private DrawingPanel drawingPanel;

    public DrawingMap() {
        setTitle("Drawing Map");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawingPanel = new DrawingPanel();
        add(drawingPanel);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        setFocusable(true);
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_C:
                drawingPanel.addShape(generateRandomCircle());
                break;
            case KeyEvent.VK_R:
                drawingPanel.addShape(generateRandomRectangle());
                break;
            case KeyEvent.VK_DELETE:
                drawingPanel.removeSelectedShape();
                break;
            case KeyEvent.VK_X:
                drawingPanel.removeCircles();
                break;
        }
    }

    private Circle generateRandomCircle() {
        Random random = new Random();
        int x = random.nextInt(getWidth() - 50);
        int y = random.nextInt(getHeight() - 50);
        int radius = random.nextInt(30) + 20;
        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        Circle circle = new Circle(new ShapeInfo(x, y, 2 * radius, 2 * radius, color), new Point(random.nextDouble(), random.nextDouble()));

        return circle;
    }

    private Rectangle generateRandomRectangle() {
        Random random = new Random();
        int x = random.nextInt(getWidth() - 50);
        int y = random.nextInt(getHeight() - 50);
        int width = random.nextInt(50) + 20;
        int height = random.nextInt(50) + 20;
        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        Rectangle rectangle = new Rectangle(new ShapeInfo(x, y, width, height, color), new Point(random.nextDouble(), random.nextDouble()));

        return rectangle;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DrawingMap drawingMap = new DrawingMap();
            drawingMap.setVisible(true);
        });
    }
}
