package example04;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

import static javafx.application.Application.launch;

class TicTacToeGraphics extends TicTacToe {
    TicTacToeGraphicsApp app;
    int selR = -1, selC = -1;

    public TicTacToeGraphics(TicTacToeGraphicsApp app) {
        this.app = app;
    }

    void human(char p) {
        selR = -1;
        while (selR == -1) {//マウス選択を待つ
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!playing) return;
        }
        if (bd[selR][selC]!='　') human(p);
        else bd[selR][selC] = p;
        System.out.println("human　:" + p + " = " + selR + "," + selC);
    }

    void disp() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                app.draw(bd);
            }
        });
    }
}

public class TicTacToeGraphicsApp extends TicTacToeApp {

    int w = 300, h = 300;
    GraphicsContext graphicsContext;
    TicTacToeGraphics game;

    public static void main(String[] args) {
        launch(args);
    }

    p
}
