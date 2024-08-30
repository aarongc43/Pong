import static com.raylib.Jaylib.*;
import com.raylib.Jaylib.Rectangle;

public class Paddle {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 100;
    public static final int SPEED = 15;

    private Rectangle paddle;
    private boolean isLeftPaddle;

    public Paddle(float x, float y, boolean isLeftPaddle) {
        this.paddle = new Rectangle(x, y - HEIGHT / 2, WIDTH, HEIGHT);
        this.isLeftPaddle = isLeftPaddle;
    }

    public void update () {
        if (isLeftPaddle) {
            if (IsKeyDown(KEY_W)) moveUp();
            if (IsKeyDown(KEY_S)) moveDown();
        } else {
            if (IsKeyDown(KEY_UP)) moveUp();
            if (IsKeyDown(KEY_DOWN)) moveDown();
        }

        clampPosition();
    }

    private void moveUp() {
        paddle.y(paddle.y() - SPEED);
    }

    private void moveDown() {
        paddle.y(paddle.y() + SPEED);
    }

    private void clampPosition () {
        paddle.y(Clamp(paddle.y(), 0, GetScreenHeight() - HEIGHT));
    }

    public void draw() {
        DrawRectangleRec(paddle, BLACK);
    }

    public Rectangle getPaddle() {
        return paddle;
    }
}
