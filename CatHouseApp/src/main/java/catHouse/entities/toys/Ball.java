package catHouse.entities.toys;

public class Ball extends BaseToy {
    private final static int SOFTNESS_BALL = 1;
    private final static double PRICE_BALL = 10.0;

    public Ball() {
        super(SOFTNESS_BALL, PRICE_BALL);
    }
}
