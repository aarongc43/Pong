import static com.raylib.Jaylib.*;

public class Game {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;

    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball ball;

    private Scoreboard scoreboard;

    public Game (){
        InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Pong");
        SetTargetFPS(60);

        // initialize objects
        leftPaddle = new Paddle(Paddle.WIDTH, SCREEN_HEIGHT / 2, true);
        rightPaddle = new Paddle(SCREEN_WIDTH - Paddle.WIDTH * 2, SCREEN_HEIGHT / 2, false);
        ball = new Ball(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        scoreboard = new Scoreboard(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void run() {
        while (!WindowShouldClose()) {
            update();
            render();
        }
        closeGame();
    }

    private void update() {
        float deltaTime = GetFrameTime();

        leftPaddle.update();
        rightPaddle.update();
        ball.update(deltaTime);

        checkCollisions();
    }

    private void checkCollisions() {
        ball.checkScreenCollision(GetScreenHeight());
        ball.checkPaddleCollision(rightPaddle);
        ball.checkPaddleCollision(leftPaddle);

        checkScore();

    }

    private void checkScore() {
        if (ball.isOutOfBounds(SCREEN_WIDTH)) {
            if (ball.getX() <= 0) {
                scoreboard.update(true);
            } else {
                scoreboard.update(false);
            }
            ball.reset(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
        }
    }

    private void DrawCenterCourtLine() {
        int topOfScoreBoard = 100;
        Vector2 startPos = new Vector2((SCREEN_WIDTH / 2), 0);
        Vector2 endPos = new Vector2((SCREEN_WIDTH / 2), SCREEN_HEIGHT - topOfScoreBoard);
        float lineThickness = 2.5f;

        DrawLineEx(startPos, endPos, lineThickness, BLACK);
    }

    private void render() {
        BeginDrawing();
        ClearBackground(RAYWHITE);
        leftPaddle.draw();
        rightPaddle.draw();
        ball.draw();
        scoreboard.draw();
        DrawCenterCourtLine();
        EndDrawing();
    }
    
    private void closeGame() {
        CloseWindow();
    }
}
