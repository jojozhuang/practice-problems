package johnny.problem;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomIdGeneratorTest {
    @Test
    public void testRandomIdGenerator() {
        System.out.println("testRandomIdGenerator");

        // Create 5 IDs with length of 6, base62
        System.out.println("Create 5 IDs with length of 6, base62");
        for (int i = 0; i < 5; i++) {
            String base62ID = RandomIdGenerator.GetBase62(6);
            System.out.println(base62ID);
            assertEquals(6, base62ID.length());
        }

        System.out.println();

        // Create 5 IDs with length of 8, base36
        System.out.println("Create 5 IDs with length of 8, base36");
        for (int i = 0; i < 5; i++) {
            String base36ID = RandomIdGenerator.GetBase36(8);
            System.out.println(base36ID);
            assertEquals(8, base36ID.length());
        }
    }

}