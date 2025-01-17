package software.ulpgc.app;

import software.ulpgc.arquitecture.view.BallDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SwingBallDisplay extends JPanel implements BallDisplay {
    private final List<Circle> circles;
    private Optional<Circle> circle;
    private Grabbed grabbed = null;
    private Released released = null;


    public SwingBallDisplay() {
        this.circles = new ArrayList<>();
        this.addMouseListener(createMouseListener());
        this.addMouseMotionListener(createMouseMotionListener());
    }

    private MouseListener createMouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                circle = findCircle(Coordinates.at(e.getX(), e.getY()));
                circle.ifPresent(c -> grabbed.at(c));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (released == null) return;
                Coordinates coordinates = Coordinates.at(e.getX(), e.getY());
                circle.ifPresent(c -> released.at(new Circle(c.id(), coordinates.x, coordinates.y, c.r())));
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        };
    }

    private MouseMotionListener createMouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (circle.isEmpty())return;
                Coordinates coordinates = Coordinates.at(e.getX(), e.getY());
                circle.ifPresent(c -> {grabbed.at(new Circle(c.id(), coordinates.x, coordinates.y, c.r()));
                });
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    private Optional<Circle> findCircle(Coordinates coordinates) {
        synchronized (circles) {
            return circles.stream()
                    .filter(c -> c.isAt(coordinates.x, coordinates.y))
                    .findFirst();
        }
    }

    @Override
    public void draw(List<Circle> circles) {
        synchronized (this.circles) {
            this.circles.clear();
            this.circles.addAll(circles);
            repaint();
        }
    }

    @Override
    public void on(Grabbed grabbed) {this.grabbed = grabbed;}

    @Override
    public void off(Released released) {
        this.released = released;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        synchronized (this.circles) {
            super.paintComponent(graphics);
            Coordinates.width = getWidth();
            Coordinates.height = getHeight() - 5; // Base {White line}
            clearCanvas(graphics);
            circles.forEach(c -> draw(graphics, c));
        }
    }

    private void draw(Graphics graphics, Circle c) {
        graphics.setColor(Color.red);
        graphics.fillOval(Coordinates.width /2 + c.x() - c.r(), Coordinates.height - c.y() - c.r(), c.r()*2,c.r()*2);
    }

    private static void clearCanvas(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0,0, Coordinates.width, Coordinates.height);
        graphics.setColor(Color.white);
        graphics.fillRect(0, Coordinates.height, Coordinates.width, Coordinates.height + 5);
    }

    record Coordinates(int x, int y) {
        public static int width;
        public static int height;

        public static Coordinates at(int x, int y) {
            return new Coordinates(x-width/2, height-y);
        }


    }

}
