package johnny.problem.randomid;

public class RandomIdGeneratorExample {
    public static void main(String[] args) {

        System.out.println("Create 5 IDs with length of 6, base62");
        // Create 5 IDs with length of 6, base62
        for (int i = 0; i < 5; i++) {
            System.out.println(RandomIdGenerator.GetBase62(6));
        }

        System.out.println();

        System.out.println("Create 5 IDs with length of 8, base36");
        // Create 5 IDs with length of 8, base36
        for (int i = 0; i < 5; i++) {
            System.out.println(RandomIdGenerator.GetBase36(8));
        }
    }
}
