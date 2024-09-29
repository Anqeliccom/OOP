package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class VariableTest {

    @Test
    void testPrint() {
        Expression expr = new Variable("x");
        assertEquals("x", expr.print());
    }

    @Test
    void testDerivative() {
        Expression expr = new Variable("x");
        assertEquals("1", expr.derivative("x").print());
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
}
