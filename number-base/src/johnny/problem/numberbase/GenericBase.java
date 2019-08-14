package johnny.problem.numberbase;

public class GenericBase {
    public static void main(String args[]) {
        System.out.println(base(13, 2));
        System.out.println(base(13, -2));
    }

    public static String base(int N, int base) {
        if (N == 0) {
            return "0";
        }

        String converted = "";
        while (N != 0) {
            // Get remainder by negative base, it can be negative also
            int remainder = N % base;
            N /= base;

            // if remainder is negative, add abs(base) to it and add 1 to n
            if (remainder < 0) {
                remainder += (-base);
                N += 1;
            }

            // convert remainder to string add into the result
            converted = remainder + converted;
        }

        return converted;
    }
}
