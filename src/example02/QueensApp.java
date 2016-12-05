package example02;

import my.Cons;

class Queens {
    int n;

    boolean check(Integer r, Integer c, Cons pat) {
        return pat.forall((Cons p) -> c != p.get(1) && r - p.getI(0) != Math.abs(c - p.getI(1)));
    }

    Cons queen(int r) {
        if (r == 0) {
            return Cons.of(Cons.Nil);
        } else {
            return queen(r - 1).flatMap(
                    (Cons p) -> Cons.range(1, n + 1)
                            .filter((Integer c) -> check(r, c, p))
                            .map(c -> new Cons(Cons.of(r, c), p)));
        }
    }

    String rep(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }

    void start(int n) {
        this.n = n;
        queen(n).foreach((Cons pat)->{
            System.out.println();
            pat.foreach((Cons p)->
            System.out.println(rep("＋",p.getI(1)-1)+"Ｑ"+rep("＋",n-p.getI(1)))
            );
        });
    }
}
public class QueensApp {
    public static void main(String[] args) {
        new Queens().start(10);
    }
}
