package software.ulpgc.arquitecture.model;

public class BallSimulator {
    private final double dt;

    public BallSimulator(double dt) {
        this.dt = dt;
    }

    public Ball simulate(Ball ball){
        return new Ball(
                ball.id(),
                ball.x(),
                ball.r(),
                newHeightOf(ball),
                newVelocityOf(ball),
                ball.g(),
                ball.cr()
        );
    }

    private double newVelocityOf(Ball ball) {
        return willBounce(ball) ? newVelocityAfterBounce(ball):  ball.v() + ball.g() * dt;
    }

    private double newVelocityAfterBounce(Ball ball) {
        return -ball.cr() * (ball.v() + ball.g() * timeToBounceOf(ball));
    }

    private double newHeightOf(Ball ball) {
        return  willBounce(ball) ? newHeightAfterBounce(ball):ball.h() + ball.v() * dt;
    }

    private double newHeightAfterBounce(Ball ball) {
        double remainingTime = dt - timeToBounceOf(ball);
        return ball.r() + newVelocityAfterBounce(ball) * remainingTime + 0.5 * ball.g() * Math.pow(remainingTime,2);
    }

    private double timeToBounceOf(Ball ball) {
        return -(ball.h() - ball.r()) / ball.v();
    }

    private boolean willBounce(Ball ball) {
        return isFalling(ball) && dt > timeToBounceOf(ball);
    }

    private static boolean isFalling(Ball ball) {
        return ball.v() < 0;
    }
}
