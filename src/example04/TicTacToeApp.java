package example04;

import java.util.Scanner;

import my.Cons;

//TicTacToe基本バージョンクラス
class TicTacToe {
    //3*3ゲーム盤(2次元配列）
    Character[][] bd = Cons.makeCharArray2(3, 3, '　');

    //勝ちの添え字パターン
    Cons a = Cons.of(0, 1, 2);
    Cons pat = new Cons(
            a.map(x -> Cons.of(x, x)),//斜め3マス
            a.map((Integer x) -> Cons.of(2 - x, x)),//斜め3マス
            a.map(r -> a.map(c -> Cons.of(r, c))).append(a.map(r -> a.map(c -> Cons.of(r, c))))//水平3*3マス
    );

    boolean playing = true;//ゲーム続行
    char winner = '　';//勝者格納
    Scanner scan;//キー入力

    boolean goal(char p) {//勝者判定
        return pat.exists((Cons t) -> t.forall((Cons a) -> bd[a.getI(0)][a.getI(1)] == p));
    }

    boolean fin() {//終了判定
        return !Cons.range(0, 3).exists((Integer r) -> Cons.range(0, 3).exists((Integer c) -> bd[r][c] == '　'));
    }

    void computer(char p) {//コンピュータの手
        Cons free = Cons.range(0, 3).flatMap((Integer r) -> Cons.range(0, 3).map((Integer c) -> Cons.of(r, c)))
                .filter((Cons x) -> bd[x.getI(0)][x.getI(1)] == '　');
        Cons f = (Cons) free.get((int) (Math.random() * free.length()));
        int r = f.getI(0);
        int c = f.getI(1);
        bd[r][c] = p;
        System.out.println("computer:" + p + " = " + r + "," + c);
    }

    void human(char p) {//人間の手
        System.out.print("row col =>");
        int r = scan.nextInt();
        int c = scan.nextInt();
        if (bd[r][c] == '　') bd[r][c]=p;
        else human(p);//置けない場所なので再試行
    }

    char turn(char p) {//プレイヤー交代
        return p=='◯'?'×':'◯';
    }

    void disp() {
        System.out.println(
                Cons.fromArray(bd).map((Character[] x)->Cons.fromArray(x).mkString(" | ")).mkString("\n")
        );
    }

    void play() {//ゲームメイン
        scan = new Scanner(System.in);
        char p = '◯';//最初のプレイヤー
        disp();//盤表示
        do {
            if (p == '◯') human(p);
            else computer(p);
            disp();
            if (goal(p)) {
                winner = p;
                playing = false;
            } else if (fin()) {
                playing = false;
            } else {
                p = turn(p);
            }
        } while (playing);
        if (winner!='　') System.out.println(winner + " Win!");
        else System.out.println("drawn");
        scan.close();
    }
}

public class TicTacToeApp {
    public static void main(String[] args) {
        new TicTacToe().play();
    }


}
