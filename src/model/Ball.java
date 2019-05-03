package model;

// Represents a Ball
// ** Coordinate system: the origin (0,0) is the upper left corner of screen,
//                       x increases from left to right, y increases from top to bottom

import java.awt.*;

public class Ball {

    public static final int BALL_SIZE = 20;
    public static final int BALL_SPEED = 4;
    private static final int RIGHT = 1;
    private static final int LEFT = -1;
    private static final int UP = -1;
    private static final int DOWN = 1;
    private static final Color COLOUR = new Color(100, 100, 100);

    private int x;
    private int y;
    private int directionX;
    private int directionY;

    //MODIFIES: this
    //EFFECTS: constructs a ball at location (x, y)
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        directionX = RIGHT;
        directionY = UP;
    }

    // MODIFIES: g
    // EFFECTS: draws the ball on the Graphics object g
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(COLOUR);
        g.fillOval(getX() - BALL_SIZE/2, getY() - BALL_SIZE/2, BALL_SIZE, BALL_SIZE);
        g.setColor(savedCol);
    }

    //MODIFIES: this
    //EFFECTS: changes the x-direction of ball so that it is facing right
    public void faceRight() {
        directionX = RIGHT;
    }

    //MODIFIES: this
    //EFFECTS: changes the x-direction of ball so that it is facing left
    public void faceLeft() {
        directionX = LEFT;
    }

    //MODIFIES: this
    //EFFECTS: changes the y-direction of ball so that it is facing up
    public void faceUp() {
        directionY = UP;
    }

    //MODIFIES: this
    //EFFECTS: changes the x-direction of ball so that it is facing down
    public void faceDown() {
        directionY = DOWN;
    }

    //MODIFIES: this
    //EFFECTS: reverses the x-direction of ball
    public void reverseX() {
        directionX = directionX * -1;
    }

    //MODIFIES: this
    //EFFECTS: reverses the y-direction of ball
    public void reverseY() {
        directionY = directionY * -1;
    }

    //MODIFIES: this
    //EFFECTS: moves the ball in the direction it is currently facing while
    //         ensuring that it does not go beyond the bounds of the screen
    public void moveBall() {
        x = x + directionX * BALL_SPEED;
        y = y + directionY * BALL_SPEED;

        handleBoundaries();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirectionX() {
        return directionX;
    }

    public int getDirectionY() {
        return directionY;
    }

    //MODIFIES: this
    //EFFECTS: ball is constrained to remain within the horizontal and vertical
    //         boundaries of the game, changing direction if boundary is hit
    private void handleBoundaries() {
        if (x - BALL_SIZE/2 < 0) {
            x = BALL_SIZE/2;
            reverseX();
        }
        if (x + BALL_SIZE/2 > BrickBreakerGame.WIDTH) {
            x = BrickBreakerGame.WIDTH - BALL_SIZE/2;
            reverseX();
        }
        if (y - BALL_SIZE/2 < 0) {
            y = BALL_SIZE/2;
            reverseY();
        }
    }

}