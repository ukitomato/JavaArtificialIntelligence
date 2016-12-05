package example01;


import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Affine;
import javafx.stage.*;


public class CurveApp extends Application {
    float scale = 1;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        StackPane pane = new StackPane();
        Canvas canvas = new Canvas(600 * scale, 600 * scale);
        pane.getChildren().add(canvas);
        stage.setScene(new Scene(pane));
        stage.show();
        Curve.g = canvas.getGraphicsContext2D();
        Curve.g.setTransform(new Affine(scale, 0, 0, 0, scale, 0));

        run();
    }

    void run() {
        Curve c = new Curve();
        c.move(50, 300);
        c.draw(500, 0);
    }

    static class Curve {
        static GraphicsContext g;
        double lastX = 0.0, lastY = 0.0;

        void move(double x, double y) {
            lastX = x;
            lastY = y;
        }

        void forward(double len, double angle) {
            double x = lastX + len * Math.cos(angle);
            double y = lastY + len * Math.sin(angle);
            g.strokeLine(lastX, lastY, x, y);
            move(x, y);
        }

        void draw(double len, double angle) {
            forward(len,angle);
        }

    }
}
