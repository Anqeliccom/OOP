package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Number} class.
 */
class NumberTest {

    @Test
    void testToStr() {
        Expression expr = new Number(5);
        assertEquals("5", expr.toStr());
    }

    @Test
    void testDerivative() {
        Expression expr = new Number(5);
        assertEquals("0", expr.derivative("x").toStr());
    }

    @Test
    void testEval() {
        Expression expr = new Number(5);
        assertEquals(5, expr.eval("x=1"));
    }

    @Test
    public void testEquals() {
        Expression number1 = new Number(5);
        Expression number2 = new Number(5);
        Expression number3 = new Number(6);
        assertTrue(number1.equals(number2));
        assertFalse(number1.equals(number3));
    }

    @Test
    public void testHashCode() {
        Expression number1 = new Number(5);
        Expression number2 = new Number(5);
        assertEquals(number1.hashCode(), number2.hashCode());
    }
}
