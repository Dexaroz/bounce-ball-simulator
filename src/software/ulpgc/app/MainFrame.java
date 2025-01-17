package software.ulpgc.app;

import software.ulpgc.arquitecture.view.BallDisplay;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final BallDisplay ballDisplay;

    public MainFrame() throws HeadlessException {
        this.setTitle("Bouncing Ball");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 800);
        add((Component) (ballDisplay = createBallDisplay()));
    }

    public BallDisplay getBallDisplay() {
        return ballDisplay;
    }

    private SwingBallDisplay createBallDisplay() {
        return new SwingBallDisplay();
    }
}
