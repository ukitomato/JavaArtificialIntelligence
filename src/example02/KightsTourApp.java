package example02;

import my.Cons;

class KightsTour {
    int n;
    Integer[][] bd;
    Cons[] pat;

    public KightsTour(int n) {
        this.n = n;
        bd = Cons.makeIntArray2(n, n, 0);
        pat = new Cons[]{
                Cons.of(1, 2), Cons.of(1, -2), Cons.of(-1, 2), Cons.of(-1, -2),
                Cons.of(2, 1), Cons.of(2, -1), Cons.of(-2, 1), Cons.of(-2, -1)
        };
    }

    public Cons kight(int r, int c, int cnt, Cons route) {
        if (r >= 0 && r < n && c >= 0 && c < n && bd[r][c] == 0) {
            bd[r][c] = cnt;
            if (cnt == n * n) {
                return new Cons(Cons.of(r, c), route);
            }
            for (Cons p : pat) {
                Cons rt = kight(r + p.getI(0), c + p.getI(1), cnt + 1, new Cons(Cons.of(r, c), route));

                if (rt != Cons.Nil) {
                    return rt;
                }

            }
            bd[r][c] = 0;
        }
        return Cons.Nil;
    }

    void start(int r, int c) {
        System.out.println(kight(r, c, 1, Cons.Nil));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.printf("%02d ", bd[i][j]);
            System.out.print("\n");
        }
    }
}

public class KightsTourApp {
    public static void main(String[] args) {
        new KightsTour(7).start(0, 0);
    }
}
