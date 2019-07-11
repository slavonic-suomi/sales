package by.gsu.study.sales.core.context;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculateUtilsTest {

    @Test
    public void testCalcUtilsForAdding() {
        CalculateUtils testee = new CalculateUtils();

        int result = testee.add(1, 2);

        assertEquals(3, result);

    }

    @Test
    public void testCalcUtilsForAddingNegative() {
        CalculateUtils testee = new CalculateUtils();

        int result = testee.add(-1, 1);

        assertEquals(0, result);

    }

    @Test
    public void testDiv() {
        CalculateUtils testee = new CalculateUtils();

        int result = testee.div(-1, 1);

        assertEquals(-1, result);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivByZero() {
        CalculateUtils testee = new CalculateUtils();

        int result = testee.div(-1, 0);
    }
}