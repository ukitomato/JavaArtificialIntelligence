package example01;

public class TreeApp extends CurveApp {
    public static void main(String[] args) {
        launch(args);
    }
    void run() {
        Tree c = new Tree();
        c.move(300, 600);
        c.draw(7, 450, Math.PI * -0.5, 1);

    }

    class Tree extends Curve {
        void draw(int n, double len, double angle, int sw) {
            double x = lastX;
            double y = lastY;
            if (n == 1) {
                forward(len, angle);
            } else {
                double l = len / (2 / Math.sqrt(2));
                double a = Math.PI * 0.15 * sw;

                forward(l * 0.33, angle);
                draw(n - 1, l * 0.8, angle - a, 1);

                forward(l * 0.33, angle);
                draw(n - 1, l * 0.7, angle + a * 1.5, -1);

                forward(l * 0.33, angle);
                draw(n - 1, l * 0.6, angle, 1);
            }
            lastX = x;
            lastY = y;
        }
    }
}
