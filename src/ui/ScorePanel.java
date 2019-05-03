package ui;

import model.BrickBreakerGame;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private static final String BRICKS_DESTROYED_TXT = "Bricks Destroyed: ";
    private static final String MISSILES_TXT = "Missiles remaining: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private BrickBreakerGame brickBreakerGame;
    private JLabel bricksDestroyedLabel;

    // EFFECTS: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(BrickBreakerGame g) {
        brickBreakerGame = g;
        setBackground(new Color(180, 180, 180));
        bricksDestroyedLabel = new JLabel(BRICKS_DESTROYED_TXT + brickBreakerGame.getNumBricksDestroyed());
        bricksDestroyedLabel.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(bricksDestroyedLabel);
    }

    // MODIFIES: this
    // EFFECTS:  updates number of bricks destroyed
    //           to reflect current state of game
    public void update() {
        bricksDestroyedLabel.setText(BRICKS_DESTROYED_TXT + brickBreakerGame.getNumBricksDestroyed());
        repaint();
    }
}
