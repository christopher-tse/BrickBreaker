package test;

import model.Ball;
import model.BrickBreakerGame;
import model.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {
    private static final int RIGHT = 1;
    private static final int LEFT = -1;
    private static final int x = 100;
    private Platform platform;
    private Ball ball;

    @BeforeEach
    void setUp() {
        platform = new Platform(x);
    }

    @Test
    void testPlatformCtr() {
        //assert platform initial direction is RIGHT
        assertEquals(RIGHT, platform.getDirectionX());
        //assert location of platform is the same as input location
        assertEquals(100, platform.getX());
    }

    @Test
    void testFaceRightLeft() {
        //faceRight when directionX = RIGHT
        assertEquals(RIGHT, platform.getDirectionX());
        platform.faceRight();
        assertEquals(RIGHT, platform.getDirectionX());

        //faceLeft when directionX = RIGHT
        platform.faceLeft();
        assertEquals(LEFT, platform.getDirectionX());

        //faceLeft when directionX = LEFT
        platform.faceLeft();
        assertEquals(LEFT, platform.getDirectionX());

        //faceRight when directionX = LEFT
        platform.faceRight();
        assertEquals(RIGHT, platform.getDirectionX());
    }

    @Test
    void testMovePlatformE() {
        //move once
        platform.movePlatform();
        assertEquals(x + platform.getDirectionX() * Platform.PLATFORM_SPEED, platform.getX());

        //move more than once (3)
        platform.movePlatform();
        platform.movePlatform();
        platform.movePlatform();
        assertEquals(x + platform.getDirectionX() * 4 * Platform.PLATFORM_SPEED, platform.getX());
    }

    @Test
    void testMovePlatformW() {
        platform.faceLeft();
        //move once
        platform.movePlatform();
        assertEquals(x + platform.getDirectionX() * Platform.PLATFORM_SPEED, platform.getX());

        //move more than once (3)
        platform.movePlatform();
        platform.movePlatform();
        platform.movePlatform();
        assertEquals(x + platform.getDirectionX() * 4 * Platform.PLATFORM_SPEED, platform.getX());
    }

    @Test
    void testLeftBoundary() {
        platform.faceLeft();
        while (platform.getX() - Platform.PLATFORM_WIDTH/2 > 0) {
            platform.movePlatform();
        }

        assertEquals(0, platform.getX() - Platform.PLATFORM_WIDTH/2);

        platform.movePlatform();
        assertEquals(0, platform.getX() - Platform.PLATFORM_WIDTH/2);
    }

    @Test
    void testRightBoundary() {
        while (platform.getX() + Platform.PLATFORM_WIDTH/2 < BrickBreakerGame.WIDTH) {
            platform.movePlatform();
        }

        assertEquals(BrickBreakerGame.WIDTH, platform.getX() + Platform.PLATFORM_WIDTH/2);

        platform.movePlatform();
        assertEquals(BrickBreakerGame.WIDTH, platform.getX() + Platform.PLATFORM_WIDTH/2);
    }

    @Test
    void testCollidedPlatformRight() {
        //collides
        ball = new Ball(x + Platform.PLATFORM_WIDTH/2 - 1, Platform.HEIGHT_DISP);
        assertTrue(platform.collidedPlatformRight(ball));

        //does not collide
        ball = new Ball(x + Platform.PLATFORM_WIDTH - 1, Platform.HEIGHT_DISP);
        assertFalse(platform.collidedPlatformRight(ball));
        ball = new Ball(x - Platform.PLATFORM_WIDTH, Platform.HEIGHT_DISP);
        assertFalse(platform.collidedPlatformLeft(ball));
    }

    @Test
    void testCollidedPlatformLeft() {
        //collides
        ball = new Ball(x - Platform.PLATFORM_WIDTH/2 + 1, Platform.HEIGHT_DISP);
        assertTrue(platform.collidedPlatformLeft(ball));

        //does not collide
        ball = new Ball(x - Platform.PLATFORM_WIDTH + 1, Platform.HEIGHT_DISP);
        assertFalse(platform.collidedPlatformLeft(ball));
        ball = new Ball(x + Platform.PLATFORM_WIDTH, Platform.HEIGHT_DISP);
        assertFalse(platform.collidedPlatformLeft(ball));
    }
}