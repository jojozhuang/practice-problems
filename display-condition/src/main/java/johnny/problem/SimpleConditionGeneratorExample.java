package johnny.problem;

import java.util.List;

public class SimpleConditionGeneratorExample {
    public static void main(String[] args) {
        System.out.println("Create 2 dimensions with 1 conditions");
        // [[a0], [a1]]
        System.out.println(SimpleConditionGenerator.getDimensionCombinations(List.of("a")));

        System.out.println("Create 4 dimensions with 2 conditions");
        // [[a0, b0], [a0, b1], [a1, b0], [a1, b1]]
        System.out.println(SimpleConditionGenerator.getDimensionCombinations(List.of("a", "b")));

        System.out.println("Create 8 dimensions with 3 conditions");
        // [[a0, b0, c0], [a0, b0, c1], [a0, b1, c0], [a0, b1, c1], [a1, b0, c0], [a1, b0, c1], [a1, b1, c0], [a1, b1, c1]]
        System.out.println(SimpleConditionGenerator.getDimensionCombinations(List.of("a", "b", "c")));

        System.out.println("Create 16 dimensions with 4 conditions");
        //[[a0, b0, c0, d0], [a0, b0, c0, d1], [a0, b0, c1, d0], [a0, b0, c1, d1], [a0, b1, c0, d0], [a0, b1, c0, d1], [a0, b1, c1, d0], [a0, b1, c1, d1], [a1, b0, c0, d0], [a1, b0, c0, d1], [a1, b0, c1, d0], [a1, b0, c1, d1], [a1, b1, c0, d0], [a1, b1, c0, d1], [a1, b1, c1, d0], [a1, b1, c1, d1]]
        System.out.println(SimpleConditionGenerator.getDimensionCombinations(List.of("a", "b", "c", "d")));
    }
}
