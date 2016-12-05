package example01;

import java.util.function.Supplier;

public class RecursiveCallApp {
    int level = 0;

    <T> T trace(String fname,String[] args, Supplier<T> fun ) {
        String s = new String(new char[level]).replace("\0", "- ") + level + ": " + fname;
        System.out.println(s + "（" + String.join(",", args) + ")");
        level++;
        T ret = fun.get();
        level--;
        System.out.println(s + " =" + ret);
        return ret;
    }


    int fact(int x) {
        return trace("fact", new String[]{"" + x}, () ->{
            if (x == 1) return 1;
            else return x * fact(x - 1);
        });
    }
    public static void main(String[] args) {
        RecursiveCallApp app = new RecursiveCallApp();
        System.out.println("階乗="+app.fact(5));
    }
}
