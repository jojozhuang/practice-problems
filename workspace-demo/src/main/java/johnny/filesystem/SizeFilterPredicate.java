package johnny.filesystem;

import java.util.function.Predicate;

public class SizeFilterPredicate implements Predicate<Integer> {
    @Override
    public boolean test(Integer val) {

        Integer five = 10; // 10 k

        return val > five;
    }
}
