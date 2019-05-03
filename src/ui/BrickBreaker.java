package ui;

import model.BrickBreakerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BrickBreaker extends JFrame {

    private static final int INTERVAL = 20;
    private BrickBreakerGame brickBreakerGame;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private Timer t;


    //EFFECTS: sets up window in which Brick Breaker game will be played
    public BrickBreaker() {
        super("Brick Breaker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        brickBreakerGame= new BrickBreakerGame();
        gamePanel = new GamePanel(brickBreakerGame);
        scorePanel = new ScorePanel(brickBreakerGame);
        add(gamePanel);
        add(scorePanel, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
        t.start();
    }

    // EFFECTS:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        t = new Timer(INTERVAL, ae -> {
            brickBreakerGame.update();
            gamePanel.repaint();
            scorePanel.update();
        });
    }

    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            brickBreakerGame.keyPressed(e.getKeyCode());
        }
    }

    public static void main(String[] args) {
        new BrickBreaker();
    }
}
