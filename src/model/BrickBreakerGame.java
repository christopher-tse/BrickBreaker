package model;

// Represents a BrickBreaker Game

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class BrickBreakerGame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int STARTING_BRICKS = 60;

    private List<Brick> bricks;
    private Ball ball;
    private Platform platform;
    private boolean isGameOver;
    private int numBricksDestroyed;


    //MODIFIES: this
    //EFFECTS: sets up brick breaker game
    public BrickBreakerGame() {
        bricks = new ArrayList<Brick>();
        initialize();
        reset();
    }

    //MODIFIES: this
    //EFFECTS: updates ball, platform, and bricks for any collisions
    public void update() {
        ball.moveBall();
        platform.movePlatform();
        platformRebound();
        checkCollisions();
        checkGameOver();

    }

    // MODIFIES: this
    // EFFECTS:  turns platform and  resets game in response to
    //           given key pressed code
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_R && isGameOver) {
            initialize();
            reset();
        } else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        } else if (keyCode == KeyEvent.VK_LEFT) {
            platform.faceLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            platform.faceRight();
        }
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public Platform getPlatform() {
        return platform;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public int getNumBricksDestroyed() {
        return numBricksDestroyed;
    }

    //MODIFIES: this
    //EFFECTS: initializes the bricks, ball, and platform
    private void initialize() {
        platform = new Platform(WIDTH/2);
        ball = new Ball(WIDTH/2, Platform.HEIGHT_DISP - Platform.PLATFORM_HEIGHT/2);
        bricks.clear();

        int x = Brick.BRICK_WIDTH/2;
        int y = Brick.BRICK_HEIGHT/2;

        for (int i = 0; i < STARTING_BRICKS; i++) {
            if (x > WIDTH) {
                x = Brick.BRICK_WIDTH/2;
                y = y + Brick.BRICK_HEIGHT;
            }
            bricks.add(new Brick(x,y));
            x = x + Brick.BRICK_WIDTH;
        }
    }

    //MODIFIES: this
    //EFFECTS: resets number of bricks destroyed; game is not over
    private void reset() {
        isGameOver = false;
        numBricksDestroyed = 0;
    }

    // MODIFIES: this
    // EFFECTS:  removes any bricks that have been hit by ball and increments
    //           the number of bricks destroyed for every brick hit
    private void checkCollisions() {
        List<Brick> bricksToBeRemoved = new ArrayList<Brick>();

        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).collidedWithTile(getBall())) {
                numBricksDestroyed++;
                bricksToBeRemoved.add(bricks.get(i));
            }
        }

        if (!bricksToBeRemoved.isEmpty()) {
            ball.reverseX();
            ball.reverseY();
        }

        bricks.removeAll(bricksToBeRemoved);
    }

    // MODIFIES: this
    // EFFECTS:  if ball has hit the bottom of the screen then game is over
    private void checkGameOver() {
        if (ball.getY() > HEIGHT) {
            isGameOver = true;
        }
    }

    //MODIFIES: this
    //EFFECTS: changes ball direction depending on where it hits the platform
    private void platformRebound() {
        if (platform.collidedPlatformRight(getBall())) {
            ball.faceRight();
            ball.faceUp();
        } else if (platform.collidedPlatformLeft(getBall())) {
            ball.faceLeft();
            ball.faceUp();
        }
    }
}
