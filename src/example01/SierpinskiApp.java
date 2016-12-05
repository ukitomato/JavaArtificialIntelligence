package example01;

public class SierpinskiApp extends CurveApp {

    public static void main(String[] args) {
        launch(args);
    }

    void run() {
        Sierpinski c = new Sierpinski();
        c.draw(6, 300, 300, 150);
    }

    class Sierpinski extends Curve {
        void draw(int n, double len, double x, double y) {
            double l = len / 2;
            double x1 = x - l;
            double x2 = x + l;
            double y1 = y + l * Math.sqrt(3);

            if (n == 1) {
                g.strokeLine(x, y, x1, y1);
                g.strokeLine(x1, y1, x2, y1);
                g.strokeLine(x2, y1, x, y);
            } else {
                double l2 = l / 2;
                draw(n - 1, l, x, y);
                draw(n - 1, l, x - l2, y + l2 * Math.sqrt(3));
                draw(n - 1, l, x + l2, y + l2 * Math.sqrt(3));
            }
        }
    }
}
