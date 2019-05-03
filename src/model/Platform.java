package model;

// Represents a platform

import java.awt.*;

public class Platform {

    public static final int PLATFORM_WIDTH = 100;
    public static final int PLATFORM_HEIGHT = 10;
    public static final int PLATFORM_SPEED = 4;
    public static final int HEIGHT_DISP = BrickBreakerGame.HEIGHT - BrickBreakerGame.HEIGHT/4;
    private static final int RIGHT = 1;
    private static final int LEFT = -1;
    private static final Color COLOUR = new Color(110, 140, 100);

    private int x;
    private int directionX;

    //MODIFIES: this
    //EFFECTS: constructs a platform at position (x, HEIGHT_DISP)
    public Platform(int x) {
        this.x = x;
        directionX = RIGHT;
    }

    // MODIFIES: g
    // EFFECTS: draws the platform on the Graphics object g
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(COLOUR);
        g.fillRoundRect(getX() - PLATFORM_WIDTH/2, HEIGHT_DISP - PLATFORM_HEIGHT/2,
                PLATFORM_WIDTH, PLATFORM_HEIGHT, 20, 20);
        g.setColor(savedCol);
    }

    //MODIFIES this
    //EFFECTS changes the direction of the platform so that it is facing right
    public void faceRight() {
        directionX = RIGHT;
    }

    //MODIFIES this
    //EFFECTS changes the direction of the platform so that it is facing left
    public void faceLeft() {
        directionX = LEFT;
    }

    //MODIFIES: this
    //EFFECTS: moves the platform in the direction it is currently facing while
    //         ensuring that it does not go beyond the bounds of the screen
    public void movePlatform() {
        x = x + directionX * PLATFORM_SPEED;

        handleBoundaries();
    }

    //EFFECTS: return true if ball has collided right of platform, otherwise return false
    public boolean collidedPlatformRight(Ball ball) {
        Rectangle platformRect = new Rectangle(getX(), HEIGHT_DISP - PLATFORM_HEIGHT/2,
                PLATFORM_WIDTH/2, PLATFORM_HEIGHT/2);
        Rectangle ballRect = new Rectangle(ball.getX() - Ball.BALL_SIZE/2, ball.getY() - Ball.BALL_SIZE/2,
                Ball.BALL_SIZE, Ball.BALL_SIZE);
        return platformRect.intersects(ballRect);
    }

    //EFFECTS: return true if ball has collided left of platform, otherwise return false
    public boolean collidedPlatformLeft(Ball ball) {
        Rectangle platformRect = new Rectangle(getX() - PLATFORM_WIDTH/2, HEIGHT_DISP - PLATFORM_HEIGHT/2,
                PLATFORM_WIDTH, PLATFORM_HEIGHT);
        Rectangle ballRect = new Rectangle(ball.getX() - Ball.BALL_SIZE/2, ball.getY() - Ball.BALL_SIZE/2,
                Ball.BALL_SIZE, Ball.BALL_SIZE);
        return platformRect.intersects(ballRect);
    }

    public int getX() {
        return x;
    }

    public int getDirectionX() {
        return directionX;
    }

    //MODIFIES: this
    //EFFECTS: platform is constrained to remain within the horizontal
    //         boundaries of the game
    private void handleBoundaries() {
        if (x - PLATFORM_WIDTH/2 < 0) {
            x = PLATFORM_WIDTH/2;
        } else if (x + PLATFORM_WIDTH/2 > BrickBreakerGame.WIDTH) {
            x = BrickBreakerGame.WIDTH - PLATFORM_WIDTH/2;
        }
    }
}
