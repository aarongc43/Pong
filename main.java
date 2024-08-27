import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*;
import static com.raylib.Jaylib.RAYWHITE;

public class main {
    // constants
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;
    private static final int PADDLE_WIDTH = 15;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_WIDTH = 10;
    private static final int BALL_HEIGHT = 10;
    private static float ballSpeedX = 15;
    private static float ballSpeedY = 15;

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
        leftPaddle = new Rectangle(0, SCREEN_HEIGHT / 2 - (PADDLE_WIDTH / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
        rightPaddle = new Rectangle(SCREEN_WIDTH - PADDLE_WIDTH, SCREEN_HEIGHT / 2 - (PADDLE_WIDTH / 2), PADDLE_WIDTH, PADDLE_HEIGHT);
        ball = new Rectangle(SCREEN_WIDTH / 2 - BALL_WIDTH, SCREEN_HEIGHT / 2 - (BALL_HEIGHT / 2), BALL_WIDTH, BALL_HEIGHT);
    }

    private static void gameLoop() {
        while (!WindowShouldClose()) {
            update();
            render();
        }
    }

    private static void update() {
        float delatTime = GetFrameTime();

        if (IsKeyDown(KEY_W)) leftPaddle.y(leftPaddle.y() - 15);
        if (IsKeyDown(KEY_S)) leftPaddle.y(leftPaddle.y() + 15);

        if (IsKeyDown(KEY_UP)) rightPaddle.y(rightPaddle.y() - 15);
        if (IsKeyDown(KEY_DOWN)) rightPaddle.y(rightPaddle.y() + 15);

        leftPaddle.y(Clamp(leftPaddle.y(), 0, SCREEN_HEIGHT - PADDLE_HEIGHT));
        rightPaddle.y(Clamp(rightPaddle.y(), 0, SCREEN_HEIGHT - PADDLE_HEIGHT));

        // need to update the ball position
        float newX = ball.y() + ballSpeedX * delatTime;
        float newY = ball.x() + ballSpeedY * delatTime;
        ball.y(ball.y() + 3);
        ball.x(ball.x() + 3);

        // add Collision detection

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

    private static void startBall() {
        int direction = getDirection();
        int dx = (int)(Math.random() * 3);
        int dy = (int)(Math.random() * 3);
    }

    private static int getDirection() {
        int random = (int)(Math.random() * 50 - 1);
        int direction = 0;

        if (random % 2 >= 1) {
            direction = 1;
        } else {
            direction = 0;
        }
        return direction;
    }
}
