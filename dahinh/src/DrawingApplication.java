import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class DrawingApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Drawing Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DrawingPanel drawingPanel = new DrawingPanel();
            frame.getContentPane().add(drawingPanel);

            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            Random random = new Random();

            for (int i = 0; i < 5; i++) {
                int x = random.nextInt(700);
                int y = random.nextInt(500);
                int width = random.nextInt(50) + 20;
                int height = random.nextInt(50) + 20;
                Color fillColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                Rectangle rectangle = new Rectangle(new ShapeInfo(x, y, width, height, fillColor), new Point(random.nextDouble(), random.nextDouble()));
                drawingPanel.addShape(rectangle);
            }

            for (int i = 0; i < 5; i++) {
                int x = random.nextInt(700);
                int y = random.nextInt(500);
                int radius = random.nextInt(30) + 10;
                Color fillColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                Circle circle = new Circle(new ShapeInfo(x, y, 2 * radius, 2 * radius, fillColor), new Point(random.nextDouble(), random.nextDouble()));
                drawingPanel.addShape(circle);
            }

            // Add key listener for adding shapes with shortcuts
            frame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    handleKeyPress(e, drawingPanel);
                }
            });

            frame.setFocusable(true);

            Timer timer = new Timer(1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawingPanel.moveShapes();
                    drawingPanel.repaint();
                }
            });

            timer.start();
        });
    }

    private static void handleKeyPress(KeyEvent e, DrawingPanel drawingPanel) {
        int keyCode = e.getKeyCode();
        Random random = new Random();
        boolean collision;

        do {
            collision = false;
            int x = random.nextInt(700);
            int y = random.nextInt(500);

            switch (keyCode) {
                case KeyEvent.VK_C:
                    int radius = random.nextInt(30) + 10;
                    Color fillColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                    Circle circle = new Circle(new ShapeInfo(x, y, 2 * radius, 2 * radius, fillColor), new Point(random.nextDouble(), random.nextDouble()));

                    for (Shape existingShape : drawingPanel.getShapes()) {
                        if (circle.intersects(existingShape)) {
                            collision = true;
                            break;
                        }
                    }

                    if (!collision) {
                        drawingPanel.addShape(circle);
                    }
                    break;

                case KeyEvent.VK_R:
                    int width = random.nextInt(50) + 20;
                    int height = random.nextInt(50) + 20;
                    fillColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
                    Rectangle rectangle = new Rectangle(new ShapeInfo(x, y, width, height, fillColor), new Point(random.nextDouble(), random.nextDouble()));

                    for (Shape existingShape : drawingPanel.getShapes()) {
                        if (rectangle.intersects(existingShape)) {
                            collision = true;
                            break;
                        }
                    }

                    if (!collision) {
                        drawingPanel.addShape(rectangle);
                    }
                    break;
            }
        } while (collision);
    }
}
