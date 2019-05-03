package model;

// Represents a Brick

import java.awt.*;

public class Brick {

    public static final int BRICK_WIDTH = 40;
    public static final int BRICK_HEIGHT = 20;
    private static final Color COLOUR = new Color(10, 200, 20);
    private static final Color OUTLINE_COLOUR = new Color(150, 10, 200);

    private int x;
    private int y;


    //MODIFIES: this
    //EFFECTS: constructs a brick at location (x, y)
    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // MODIFIES: g
    // EFFECTS: draws the brick on the Graphics object g
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(COLOUR);
        g.fillRect(getX() - BRICK_WIDTH/2, getY() - BRICK_HEIGHT/2, BRICK_WIDTH, BRICK_HEIGHT);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(OUTLINE_COLOUR);
        g2.drawRect(getX() - BRICK_WIDTH/2, getY() - BRICK_HEIGHT/2, BRICK_WIDTH, BRICK_HEIGHT);
        g.setColor(savedCol);
    }

    //EFFECTS: return true if ball has collided with a brick, otherwise return false
    public boolean collidedWithTile(Ball ball) {
        Rectangle brickRect = new Rectangle(getX() - BRICK_WIDTH/2, getY() - BRICK_HEIGHT/2, BRICK_WIDTH, BRICK_HEIGHT);
        Rectangle ballRect = new Rectangle(ball.getX() - Ball.BALL_SIZE/2, ball.getY() - Ball.BALL_SIZE/2,
                Ball.BALL_SIZE, Ball.BALL_SIZE);
        return brickRect.intersects(ballRect);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
