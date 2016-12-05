package example01;

import my.Cons;

class TowerOfHanoi {
    int n;

    void hanoi(int m, int from, int to, int work, Cons[] tower) {

        if (m == 1) {
            tower[to] = new Cons(tower[from].head, tower[to]);
            tower[from] = tower[from].tail;
            System.out.println(disp(Cons.fromArray(tower).map((Cons x) -> x.reverse())));
        } else {
            hanoi(m - 1, from, work, to, tower);
            hanoi(1, from, to, work, tower);
            hanoi(m - 1, work, to, from, tower);
        }
    }

    String rep(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }

    String disp(Cons s) {
        if (s.forall(x -> x == Cons.Nil)) {
            return rep("ー", n * 2 * 3) + "\n";
        } else {
            return disp(s.map((Cons x) ->
                    (x == Cons.Nil) ? Cons.Nil : x.tail)) + "\n" +
                    s.map((Cons x) ->
                            (x == Cons.Nil) ? 0 : x.head).map((Integer x)->
                            rep(" ",n-x)+rep("%%",x)+rep(" ",n-x)).mkString("");
        }
    }

    void start(int n) {
        this.n = n;
        Cons[] tower = new Cons[]{
                Cons.range(1, n + 1), Cons.Nil, Cons.Nil
        };
        hanoi(n, 0, 2, 1, tower);
    }

}
public class TowerOfHanoiApp {
    public static void main(String[] args) {
        new TowerOfHanoi().start(10);
    }
}
