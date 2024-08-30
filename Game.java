import static com.raylib.Jaylib.*;

public class Game {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;

    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball ball;

    public Game (){
        InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Pong");
        SetTargetFPS(60);

        // initialize objects
        leftPaddle = new Paddle(Paddle.WIDTH, SCREEN_HEIGHT / 2, true);
        rightPaddle = new Paddle(SCREEN_WIDTH - Paddle.WIDTH * 2, SCREEN_HEIGHT / 2, false);
        ball = new Ball(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
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
        // setup new method to check for scores
        if (ball.isOutOfBounds(GetScreenWidth())) {
            ball.reset(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
            System.out.println("Ball is out of bounds, someone scored.");
        }
    }

    private void render() {
        BeginDrawing();
        ClearBackground(RAYWHITE);
        leftPaddle.draw();
        rightPaddle.draw();
        ball.draw();
        EndDrawing();
    }
    
    private void closeGame() {
        CloseWindow();
    }
}
