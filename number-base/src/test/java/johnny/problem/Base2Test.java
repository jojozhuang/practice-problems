package johnny.problem;

import org.junit.Test;

import static org.junit.Assert.*;

public class Base2Test {

    @Test
    public void testBase2() {
        System.out.println("testBase2");
        Base2 instance = new Base2();
        assertEquals("101", instance.base2(5));
        assertEquals("1101", instance.base2(13));
        assertEquals("100000", instance.base2(32));
    }

    @Test
    public void testBaseNeg2() {
        System.out.println("testBaseNeg2");
        Base2 instance = new Base2();
        assertEquals("101", instance.baseNeg2(5));
        assertEquals("11101", instance.baseNeg2(13));
        assertEquals("1100000", instance.baseNeg2(32));
    }
}