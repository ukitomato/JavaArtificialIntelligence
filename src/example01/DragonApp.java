package example01;

public class DragonApp extends CurveApp {
    public static void main(String[] args) {
        launch(args);
    }

    void run() {
        Dragon c = new Dragon();
        c.move(150, 300);
        c.draw(13,300,0,1);
    }

    class Dragon extends Curve {
        void draw(int n, double len, double angle, int sw) {
            if (n == 1) {
                forward(len, angle);
            } else {
                double l = len / (2 / Math.sqrt(2.0));
                double a = Math.PI * 0.25 * sw;
                draw(n - 1, l, angle - a, 1);
                draw(n - 1, l, angle + a, -1);
            }
        }
    }
}
