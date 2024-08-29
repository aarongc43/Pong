import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*;
import static com.raylib.Jaylib.RAYWHITE;
import java.lang.Math;

public class main {
    // constants
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;
    private static final int PADDLE_WIDTH = 10;
    private static final int PADDLE_HEIGHT = 100;
    private static final int PADDLE_SPEED = 15;
    private static final int BALL_WIDTH = 10;
    private static final int BALL_HEIGHT = 10;
    private static float ballSpeedX = 55;
    private static float ballSpeedY = 155;

    // objects
    private static Rectangle leftPaddle;
    private static Rectangle rightPaddle;
    private static Rectangle ball;

    public static void main(String[] args) {
        initializeGame();
        gameLoop();
        closeGame();
    }

    private static void initializeGame() {
        InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Pong");
        SetTargetFPS(60);
        // create objects
        // paddle(x,y,width, height);
        leftPaddle = new Rectangle(PADDLE_WIDTH, SCREEN_HEIGHT / 2 - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle = new Rectangle(SCREEN_WIDTH - PADDLE_WIDTH * 2, SCREEN_HEIGHT / 2 - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
        // ball(x,y,width, height);
        ball = new Rectangle(SCREEN_WIDTH / 2 - BALL_WIDTH, SCREEN_HEIGHT / 2 - (BALL_HEIGHT / 2), BALL_WIDTH, BALL_HEIGHT);
    }

    private static void gameLoop() {
        while (!WindowShouldClose()) {
            update();
            render();
        }
    }

    private static void update() {
        float deltaTime = GetFrameTime();

        updatePaddles();
        updateBallPosition(deltaTime);
        checkBallCollision();
        checkPaddleCollision();
    }

    private static void updatePaddles() {
        if (IsKeyDown(KEY_W)) leftPaddle.y(leftPaddle.y() - PADDLE_SPEED);
        if (IsKeyDown(KEY_S)) leftPaddle.y(leftPaddle.y() + PADDLE_SPEED);

        if (IsKeyDown(KEY_UP)) rightPaddle.y(rightPaddle.y() - PADDLE_SPEED);
        if (IsKeyDown(KEY_DOWN)) rightPaddle.y(rightPaddle.y() + PADDLE_SPEED);

        leftPaddle.y(Clamp(leftPaddle.y(), 0, SCREEN_HEIGHT - PADDLE_HEIGHT));
        rightPaddle.y(Clamp(rightPaddle.y(), 0, SCREEN_HEIGHT - PADDLE_HEIGHT));

    }

    private static void updateBallPosition(float deltaTime) {
        float newX = ball.x() + ballSpeedX * deltaTime;
        float newY = ball.y() + ballSpeedY * deltaTime;
        ball.y(newY);
        ball.x(newX);
    }

    private static void checkBallCollision() {
        // check vertical collision detection
        if (ball.y() < 0 || ball.y() > (SCREEN_HEIGHT - BALL_HEIGHT)) {
            ballSpeedY *= -1;
        }

        // checking horizontal boundaries
        if (ball.x() < 0) {
            System.out.println("passed left paddle, right paddle score");
            resetBall();
        } else if (ball.x() > SCREEN_WIDTH - BALL_WIDTH) {
            System.out.println("passed right paddle, left paddle score");
            resetBall();
        }
    }

    // check to see if the ball has hit the paddle
    private static void checkPaddleCollision() {

        if (CheckCollisionRecs(ball, rightPaddle) || CheckCollisionRecs(ball, leftPaddle)) {
            float currentSpeed = (float)Math.sqrt(ballSpeedX * ballSpeedX + ballSpeedY * ballSpeedY);
            currentSpeed *= 1.1f;

            double bounceAngle = randomAngleRadians();
            ballSpeedX = currentSpeed * (float)Math.cos(bounceAngle);
            ballSpeedY = currentSpeed * (float)Math.sin(bounceAngle);

            if (CheckCollisionRecs(ball, rightPaddle)) {
                ballSpeedX = -Math.abs(ballSpeedX);
                ball.x(rightPaddle.x() - ball.width() - 1);
            } else {
                ballSpeedX = Math.abs(ballSpeedX);
                ball.x(leftPaddle.x() + leftPaddle.width() + 1);
            }
        }
    }

    private static double randomAngleRadians() {
        return GetRandomValue(0, 90) * DEG2RAD;
    }

    private static void resetBall() {
        ball.x(SCREEN_WIDTH / 2 - BALL_WIDTH / 2);
        ball.y(SCREEN_HEIGHT / 2 - BALL_HEIGHT / 2);

        ballSpeedX = (Math.random() > 0.5 ? 1 : -1) * Math.abs(ballSpeedX);
        ballSpeedY = (Math.random() > 0.5 ? 1 : -1) * Math.abs(ballSpeedY);
    }

    private static void render() {
        BeginDrawing();
        ClearBackground(RAYWHITE);
        DrawRectangleRec(leftPaddle, BLACK);
        DrawRectangleRec(rightPaddle, BLACK);
        DrawRectangleRec(ball, BLACK);
        EndDrawing();
    }

    private static void closeGame() {
        CloseWindow();
    }
}
