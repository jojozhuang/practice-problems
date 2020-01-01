package johnny.problem;

public class GenericBase {
    public String base(int n, int base) {
        if (n == 0) {
            return "0";
        }

        String converted = "";
        while (n != 0) {
            // Get remainder by negative base, it can be negative also
            int remainder = n % base;
            n /= base;

            // if remainder is negative, add abs(base) to it and add 1 to n
            if (remainder < 0) {
                remainder += (-base);
                n += 1;
            }

            // convert remainder to string add into the result
            converted = remainder + converted;
        }

        return converted;
    }
}
