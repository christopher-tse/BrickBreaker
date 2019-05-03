package test;

import model.Ball;
import model.Brick;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrickTest {
    private Brick brick;
    private Ball ball;

    @BeforeEach
    void setUp() {
        brick = new Brick(100, 200);
    }

    @Test
    void testCollidedWithTile() {
        //collides
        ball = new Ball(100, 200);
        assertTrue(brick.collidedWithTile(ball));
        ball = new Ball(85 + Ball.BALL_SIZE, 200);
        assertTrue(brick.collidedWithTile(ball));
        int x = brick.getX() + Brick.BRICK_WIDTH/2 + Ball.BALL_SIZE/2;
        int y = brick.getY() - Brick.BRICK_HEIGHT/2 - Ball.BALL_SIZE/2;
        ball = new Ball(x - 1, y + 1);
        assertTrue(brick.collidedWithTile(ball));

        //does not collide
        ball = new Ball(200, 400);
        assertFalse(brick.collidedWithTile(ball));
        ball = new Ball(x, y);
        assertFalse(brick.collidedWithTile(ball));
    }

}