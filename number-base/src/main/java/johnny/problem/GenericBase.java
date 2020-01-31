package johnny.problem;

public class GenericBase {
    // Given a decimal number n and an integer k, Convert decimal number n to base-k.
    public String base(int n, int k) {
        if (n == 0) {
            return "0";
        }

        String ans = "";
        while (n != 0) {
            int r = n % k;
            n /= k;

            // if remainder is negative, add abs(base) to it and add 1 to n
            if (r < 0) {
                r += (-k);
                n += 1;
            }

            // convert remainder to string add into the result
            if (r <= 9) {
                ans = r + ans;
            } else {
                ans = (char)(r - 10 + 'A') + ans;
            }
        }

        return ans;
    }
}
