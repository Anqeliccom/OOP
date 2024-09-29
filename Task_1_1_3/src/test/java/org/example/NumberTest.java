package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    void testPrint() {
        Expression expr = new Number(5);
        assertEquals("5", expr.print());
    }

    @Test
    void testDerivative() {
        Expression expr = new Number(5);
        assertEquals("0", expr.derivative("x").print());
    }

    @Test
    void testEval() {
        Expression expr = new Number(5);
        assertEquals(5, expr.eval("x=1"));
    }
}
