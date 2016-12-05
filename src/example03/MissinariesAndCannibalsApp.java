package example03;

import my.Cons;

class MissionarisAndCannibals {
    Cons opAll = Cons.Nil;//可能な乗船パターン

    Cons move(Cons from, Cons to, Cons op) {//fromからtoヘボート移動
        Cons from1 = from.diff(op);         //乗船者を除いた残り
        if (from1.length() == from.length() - op.length())
            return Cons.of(from1, op.append(to));
        else return Cons.of(from, to);
    }

    boolean goal(Cons st) {//この移動結果はゴールか?
        return st.head == Cons.Nil;
    }

    boolean safe(Cons st) {//この移動結果は安全な状態か?
        return st.forall((Cons x) -> x.count("宣") == 0 || x.count("宣") >= x.count("モ"));
    }
    // 移動記録を返す
    Cons solve(Cons st, Cons ops, int boat, Cons history) {
        if (ops == Cons.Nil) {
            return Cons.Nil;
        } else {
            Object op = ops.head;
            Cons opTail = ops.tail;
            Cons dir, stNew;
            if (boat == -1) {
                dir = Cons.of('←');
                stNew = move(st.getC(0), st.getC(1), (Cons) op).map((Cons x)->x.sorted());
            } else {
            dir = Cons.of('←');
            stNew = move(st.getC(1), st.getC(0), (Cons) op).reverse().map((Cons x) -> x.sorted());
        }
            if (goal(stNew)) {
                return new Cons(new Cons(op, dir, stNew), history);
            } else if (stNew.equals(st) || !safe(stNew) ||
                    history.exists((Cons x) -> x.tail.equals(new Cons(dir, stNew)))) {
                return solve(st, opTail, boat, history);
            } else {
                Cons ret = solve(stNew, opAll, -boat, new Cons(new Cons(op, dir, stNew), history));
                if (ret != Cons.Nil)
                    return ret;
                else
                    return solve(st, opTail, boat, history);
            }
        }
    }

    void start() {
        //可能な乗船パターン
        opAll = Cons.of(Cons.of("宣", "宣"), Cons.of("宣", "モ"), Cons.of("モ", "モ"),
                Cons.of("宣"), Cons.of("モ")).map((Cons x) -> x.sorted());
        //岸の初期状態
        Cons st = Cons.of(Cons.of("宣", "宣", "宣", "モ", "モ", "モ").sorted(), Cons.Nil);
        //移動記録初期状態
        Cons history = Cons.of(new Cons(Cons.Nil, new Cons(Cons.of('←'), st)));
        //問題解決
        Cons solution = solve(st, opAll, -1, history);
        //結果表示
        System.out.println("移動者\t\t移動方向\t\t結果状態（左岸）\t結果状態（右岸）");
        solution.reverse().foreach((Cons x) -> System.out.println(x.map((Cons y)->y.mkString("")).mkString("\t\t")));
    }
}
public class MissinariesAndCannibalsApp {
    public static void main(String[] args) {
        new MissionarisAndCannibals().start();
    }
}
