package example02;

import java.util.function.Supplier;
import my.Cons;

class Queens1 extends Queens {
    int level = 0;

    <T> T trace(String fname, String[] args, Supplier<T> fun) {
        String s = new String(new char[level]).replace("\0", "- ") + level + ": " + fname;
        System.out.println(s + "（" + String.join(",", args)+"）");
        level++;
        T ret = fun.get();
        level--;
        System.out.println(s + " =" + ret);
        return ret;
    }

    Cons queen(int r) {
        return trace("queen",new String[]{""+r},()->{
            if (r == 0) {
                return Cons.of(Cons.Nil);
            } else {
                return queen(r - 1).flatMap((Cons p) -> Cons.range(1, n + 1)
                        .filter((Integer c) -> check(r, c, p)).map(c -> new Cons(Cons.of(r, c), p)));
            }
        });
    }
}
public class QueensApp1 {
    public static void main(String[] args) {
        new Queens1().start(4);
    }
}
