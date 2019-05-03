package ui;

import model.Brick;
import model.BrickBreakerGame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GamePanel extends JPanel {

    private static final String GAME_OVER = "GAME OVER!";
    private static final String REPLAY = "PRESS 'R' TO PLAY AGAIN";
    private BrickBreakerGame brickBreakerGame;

    // Effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(BrickBreakerGame g) {
        setPreferredSize(new Dimension(BrickBreakerGame.WIDTH, BrickBreakerGame.HEIGHT));
        setBackground(Color.cyan);
        brickBreakerGame = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (brickBreakerGame.isGameOver()) {
            gameOver(g);
        }
    }

    // MODIFIES: g
    // EFFECTS:  the game is drawn onto the Graphics object g
    private void drawGame(Graphics g) {
        brickBreakerGame.getBall().draw(g);
        brickBreakerGame.getPlatform().draw(g);
        drawBricks(g, brickBreakerGame.getBricks());
    }

    private void drawBricks(Graphics g, List<Brick> bricks) {
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    // MODIFIES: g
    // EFFECTS:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color( 0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(GAME_OVER, g, fm, BrickBreakerGame.HEIGHT / 2);
        centreString(REPLAY, g, fm, BrickBreakerGame.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    // MODIFIES: g
    // EFFECTS:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int yPos) {
        int width = fm.stringWidth(str);
        g.drawString(str, (BrickBreakerGame.WIDTH - width) / 2, yPos);
    }

}
