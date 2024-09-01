import static com.raylib.Jaylib.*;

public class Scoreboard {
    private static final int WIDTH = 110;
    private static final int HEIGHT = 100;
    private static final int FONT_SIZE = 35;
    private static final int PADDING = 0;
    private static final int LINE_THICKNESS = 5;

    private Rectangle scoreboard;
    private int leftPaddleScore;
    private int rightPaddleScore;

    public Scoreboard (int screenWidth, int screenHeight) {
        this.scoreboard = new Rectangle(
                    (screenWidth / 2f - WIDTH / 2f),
                    screenHeight - HEIGHT - PADDING,
                    WIDTH,
                    HEIGHT
                );
        this.leftPaddleScore = 0;
        this.rightPaddleScore = 0;
    }

    public void update(boolean leftScore) {
        if (leftScore) {
            leftPaddleScore++;
        } else {
            rightPaddleScore++;
        }
    }

    public void reset() {
        leftPaddleScore = 0;
        rightPaddleScore = 0;
    }

    public void draw() {
        DrawRectangleLinesEx(scoreboard, LINE_THICKNESS, BLACK);

        String scoreText = leftPaddleScore + " - " + rightPaddleScore;
        int textSize =  MeasureText(scoreText, FONT_SIZE);

        float textX = scoreboard.x() + (WIDTH - textSize) / 2;
        float textY = scoreboard.y() + (HEIGHT - FONT_SIZE) / 2;

        DrawText(scoreText, (int)textX, (int)textY, FONT_SIZE, BLACK);
    }
}
