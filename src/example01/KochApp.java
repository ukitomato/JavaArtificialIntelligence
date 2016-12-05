package example01;

public class KochApp extends CurveApp {
    public static void main(String[] args) {
        launch(args);
    }

    void run() {
        Koch c = new Koch();
        c.move(20, 300);
        c.draw(5, 500, 0);

    }

    class Koch extends Curve {
        void draw(int n, double len, double angle) {
            if (n == 1) {
                forward(len, angle);
            } else {
                double l = len / (2 / Math.sqrt(2.0) + 2);
                double a = Math.PI * 0.25;
                draw(n - 1, l, angle);
                draw(n - 1, l, angle - a);
                draw(n - 1, l, angle + a);
                draw(n - 1, l, angle);
            }
        }
    }
}
