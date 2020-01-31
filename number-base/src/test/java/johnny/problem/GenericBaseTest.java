package johnny.problem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenericBaseTest {

    @Test
    public void testGenericBase() {
        System.out.println("testGenericBase");
        GenericBase instance = new GenericBase();
        assertEquals("101", instance.base(5, 2));
        assertEquals("1101", instance.base(13, 2));
        assertEquals("100000", instance.base(32, 2));

        assertEquals("101", instance.base(5, -2));
        assertEquals("11101", instance.base(13, -2));
        assertEquals("1100000", instance.base(32, -2));

        assertEquals("21", instance.base(17, 8));
        assertEquals("36", instance.base(30, 8));

        assertEquals("11", instance.base(17, 16));
        assertEquals("1E", instance.base(30, 16));
    }
}