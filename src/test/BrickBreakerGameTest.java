package test;

import model.Ball;
import model.Brick;
import model.BrickBreakerGame;
import model.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrickBreakerGameTest {
    private BrickBreakerGame brickBreakerGame;

    @BeforeEach
    void setUp() {
        brickBreakerGame = new BrickBreakerGame();
    }

    @Test
    void testBrickBreakerGameCtr() {
        Ball ball = brickBreakerGame.getBall();
        Platform platform = brickBreakerGame.getPlatform();

        assertEquals(BrickBreakerGame.WIDTH/2, ball.getX());
        assertEquals(BrickBreakerGame.WIDTH/2, platform.getX());
        List<Brick> bricks = brickBreakerGame.getBricks();
        assertEquals(20, bricks.size());
    }

    @Test
    void testUpdate() {
        Ball ball = brickBreakerGame.getBall();
        Platform platform = brickBreakerGame.getPlatform();
        List<Brick> bricks = brickBreakerGame.getBricks();

        brickBreakerGame.update();

        ball.moveBall();
        platform.movePlatform();
        assertEquals(ball.getX(), brickBreakerGame.getBall().getX());
        assertEquals(platform.getX(), brickBreakerGame.getPlatform().getX());

        int num_destroyed = 0;
        for (int i = 0; i < bricks.size(); i++) {
            if (bricks.get(i).collidedWithTile(ball)) {
                bricks.remove(bricks.get(i));
                num_destroyed++;
            }
        }

        assertEquals(bricks, brickBreakerGame.getBricks());
        assertEquals(num_destroyed, brickBreakerGame.getNumBricksDestroyed());

        if (platform.collidedPlatformRight(ball)) {
            ball.faceRight();
            ball.faceUp();
        } else if (platform.collidedPlatformLeft(ball)) {
            ball.faceLeft();
            ball.faceUp();
        }

        assertEquals(ball.getDirectionX(), brickBreakerGame.getBall().getDirectionX());
        assertEquals(ball.getDirectionY(), brickBreakerGame.getBall().getDirectionY());

        assertEquals(ball.getY() > BrickBreakerGame.HEIGHT, brickBreakerGame.isGameOver());

    }
}