package test;

import model.Ball;
import model.BrickBreakerGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BallTest {
    private static final int x = 100;
    private static final int y = 150;
    private static final int RIGHT = 1;
    private static final int LEFT = -1;
    private static final int UP = -1;
    private static final int DOWN = 1;
    private Ball ball;

    @BeforeEach
    void setUp() {
        ball = new Ball(x, y);
    }

    @Test
    void testBallCtr() {
        //assert constructor sets up ball to have directionX = RIGHT and directionY = UP
        assertEquals(RIGHT, ball.getDirectionX());
        assertEquals(LEFT, ball.getDirectionY());
        //assert x and y locations match argument inputs
        assertEquals(x, ball.getX());
        assertEquals(y, ball.getY());
    }

    @Test
    void testFaceRightLeft() {
        //faceRight when directionX = RIGHT
        assertEquals(RIGHT, ball.getDirectionX());
        ball.faceRight();
        assertEquals(RIGHT, ball.getDirectionX());

        //faceLeft when directionX = RIGHT
        ball.faceLeft();
        assertEquals(LEFT, ball.getDirectionX());

        //faceLeft when directionX = LEFT
        ball.faceLeft();
        assertEquals(LEFT, ball.getDirectionX());

        //faceRight when directionX = LEFT
        ball.faceRight();
        assertEquals(RIGHT, ball.getDirectionX());
    }

    @Test
    void testFaceUpDown() {
        //faceUp when directionY = UP
        assertEquals(UP, ball.getDirectionY());
        ball.faceUp();
        assertEquals(UP, ball.getDirectionY());

        //faceDown when directionY = UP
        ball.faceDown();
        assertEquals(DOWN, ball.getDirectionY());

        //faceDown when directionY = DOWN
        ball.faceDown();
        assertEquals(DOWN, ball.getDirectionY());

        //faceUp when directionY = DOWN
        ball.faceUp();
        assertEquals(UP, ball.getDirectionY());
    }

    @Test
    void testReverseXY() {
        //reverseX when directionX = RIGHT
        assertEquals(RIGHT, ball.getDirectionX());
        ball.reverseX();
        assertEquals(LEFT, ball.getDirectionX());

        //reverseX when directionX = LEFT
        ball.reverseX();
        assertEquals(RIGHT, ball.getDirectionX());

        //reverseY when directionY = UP
        assertEquals(UP, ball.getDirectionY());
        ball.reverseY();
        assertEquals(DOWN, ball.getDirectionY());

        //reverseY when directionY = DOWN
        ball.reverseY();
        assertEquals(UP, ball.getDirectionY());
    }

    @Test
    void testMoveBallNE() {
        //directionX = RIGHT + directionY = UP

        //move once
        ball.moveBall();
        assertEquals(x + ball.getDirectionX() * Ball.BALL_SPEED, ball.getX());
        assertEquals(y + ball.getDirectionY() * Ball.BALL_SPEED, ball.getY());

        //move more than once (3)
        ball.moveBall();
        ball.moveBall();
        ball.moveBall();
        assertEquals(x + ball.getDirectionX() * 4 * Ball.BALL_SPEED, ball.getX());
        assertEquals(y + ball.getDirectionY() * 4 * Ball.BALL_SPEED, ball.getY());
    }

    @Test
    void testMoveBallNW() {
        //directionX = LEFT + directionY = UP
        ball.faceLeft();
        ball.faceUp();

        //move once
        ball.moveBall();
        assertEquals(x + ball.getDirectionX() * Ball.BALL_SPEED, ball.getX());
        assertEquals(y + ball.getDirectionY() * Ball.BALL_SPEED, ball.getY());

        //move more than once (3)
        ball.moveBall();
        ball.moveBall();
        ball.moveBall();
        assertEquals(x + ball.getDirectionX() * 4 * Ball.BALL_SPEED, ball.getX());
        assertEquals(y + ball.getDirectionY() * 4 * Ball.BALL_SPEED, ball.getY());
    }

    @Test
    void testMoveBallSE() {
        //directionX = RIGHT + directionY = DOWN
        ball.faceRight();
        ball.faceDown();

        //move once
        ball.moveBall();
        assertEquals(x + ball.getDirectionX() * Ball.BALL_SPEED, ball.getX());
        assertEquals(y + ball.getDirectionY() * Ball.BALL_SPEED, ball.getY());

        //move more than once (3)
        ball.moveBall();
        ball.moveBall();
        ball.moveBall();
        assertEquals(x + ball.getDirectionX() * 4 * Ball.BALL_SPEED, ball.getX());
        assertEquals(y + ball.getDirectionY() * 4 * Ball.BALL_SPEED, ball.getY());
    }

    @Test
    void testMoveBallSW() {
        //directionX = LEFT + directionY = DOWN
        ball.faceLeft();
        ball.faceDown();

        //move once
        ball.moveBall();
        assertEquals(x + ball.getDirectionX() * Ball.BALL_SPEED, ball.getX());
        assertEquals(y + ball.getDirectionY() * Ball.BALL_SPEED, ball.getY());

        //move more than once (3)
        ball.moveBall();
        ball.moveBall();
        ball.moveBall();
        assertEquals(x + ball.getDirectionX() * 4 * Ball.BALL_SPEED, ball.getX());
        assertEquals(y + ball.getDirectionY() * 4 * Ball.BALL_SPEED, ball.getY());
    }

    @Test
    void testLeftBoundary() {
        ball.faceLeft();
        while (ball.getX() - Ball.BALL_SIZE/2 > 0) {
            ball.moveBall();
        }

        assertEquals(0, ball.getX() - Ball.BALL_SIZE/2);

        ball.moveBall();
        assertEquals(0, ball.getX() - Ball.BALL_SIZE/2);
    }

    @Test
    void testRightBoundary() {
        while (ball.getX() + Ball.BALL_SIZE/2 < BrickBreakerGame.WIDTH) {
            ball.moveBall();
        }

        assertEquals(BrickBreakerGame.WIDTH, ball.getX() + Ball.BALL_SIZE/2);

        ball.moveBall();
        assertEquals(BrickBreakerGame.WIDTH, ball.getX() + Ball.BALL_SIZE/2);
    }

    @Test
    void testTopBoundary() {
        while (ball.getY() - Ball.BALL_SIZE/2 > 0) {
            ball.moveBall();
        }

        assertEquals(0, ball.getY() - Ball.BALL_SIZE/2);

        ball.moveBall();
        assertEquals(0, ball.getY() - Ball.BALL_SIZE/2);
    }
}