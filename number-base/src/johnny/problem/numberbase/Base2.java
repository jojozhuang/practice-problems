package johnny.problem.numberbase;

public class Base2 {
    public static void main(String args[]) {
        System.out.println(base2(13));
        System.out.println(baseNeg2(13));
    }

    public static String base2(int N) {
        StringBuilder res = new StringBuilder();
        while (N != 0) {
            res.append(N & 1);
            N = N >> 1;
        }
        return res.length() > 0 ? res.reverse().toString() : "0";
    }

    public static String baseNeg2(int N) {
        StringBuilder res = new StringBuilder();
        while (N != 0) {
            res.append(N & 1);
            N = -(N >> 1);
        }
        return res.length() > 0 ? res.reverse().toString() : "0";
    }
}
