package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Variable} class.
 */
class VariableTest {

    @Test
    void testToStr() {
        Expression expr = new Variable("x");
        assertEquals("x", expr.toStr());
    }

    @Test
    void testDerivative() {
        Expression expr = new Variable("x");
        assertEquals("1", expr.derivative("x").toStr());
    }

    @Test
    void testEvalSingleVariable() {
        Expression expr = new Variable("x");
        assertEquals(10, expr.eval("x=10"));
    }

    @Test
    void testEvalVariableNotFound() {
        Expression expr = new Variable("x");
        assertThrows(IllegalArgumentException.class, () -> expr.eval("y=10"));
    }

    @Test
    public void testEquals() {
        Expression var1 = new Variable("x");
        Expression var2 = new Variable("x");
        Expression var3 = new Variable("y");
        assertTrue(var1.equals(var2));
        assertFalse(var1.equals(var3));
    }

    @Test
    public void testHashCode() {
        Expression var1 = new Variable("x");
        Expression var2 = new Variable("x");
        assertEquals(var1.hashCode(), var2.hashCode());
    }
}
