import static com.raylib.Jaylib.*;
import com.raylib.Jaylib.Rectangle;
import com.raylib.Jaylib.Vector2;

public class Ball {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int INITIAL_SPEED = 200;
    private static float SPEED_INCREMENT = 1.1f;

    private Rectangle ball;
    private Vector2 velocity;

    public Ball(float x, float y) {
        this.ball = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
        this.resetVelocity();
    }

    public void update(float deltaTime) {
        ball.x(ball.x() + velocity.x() * deltaTime);
        ball.y(ball.y() + velocity.y() * deltaTime);
    }

    public boolean checkScreenCollision(float screenHeight) {
        if (ball.y() < 0 || ball.y() + HEIGHT > screenHeight) {
            velocity.y(velocity.y() * -1);

            // avoid sticking
            ball.y(ball.y() < 0 ? 0 : screenHeight - HEIGHT);
            return true;
        }

        return false;
    }

    public boolean checkPaddleCollision(Paddle paddle) {
        if (CheckCollisionRecs(ball, paddle.getPaddle())) {
            double bounceAngle = GetRandomValue(-45, 45) * DEG2RAD;
            float currentSpeed = Vector2Length(velocity);
            currentSpeed *= SPEED_INCREMENT;

            float direction = paddle.getPaddle().x() < ball.x() ? 1 : -1;
            velocity.x(direction * Math.abs(currentSpeed * (float)Math.cos(bounceAngle)));
            velocity.y(currentSpeed * (float)Math.sin(bounceAngle));

            if (direction > 0) {
                ball.x(paddle.getPaddle().x() + paddle.getPaddle().width());
            } else {
                ball.x(paddle.getPaddle().x() - ball.width());
            }
            return true;
        }
        return false;
    }

    public boolean isOutOfBounds(float screenWidth) {
        return ball.x() < 0 || ball.x() + WIDTH > screenWidth;
    }

    public void reset(float x, float y) {
        ball.x(x - WIDTH / 2);
        ball.y(y - HEIGHT / 2);
        resetVelocity();
    }

    private void resetVelocity() {
        float angle = (float)(Math.PI / 4 * (Math.random() - 0.5));
        velocity = new Vector2(
            INITIAL_SPEED * (float)Math.cos(angle) * (Math.random() < 0.5 ? 1 : -1),
            INITIAL_SPEED * (float)Math.sin(angle)
        );
    }

    public float getX() {
        return ball.x();
    }

    public void draw() {
        DrawRectangleRec(ball, BLACK);
    }
}
