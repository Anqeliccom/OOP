package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SubTest {

    @Test
    void testPrintVariableAndNumber() {
        Expression expr = new Sub(new Variable("x"), new Number(5));
        assertEquals("(x-5)", expr.print());
    }

    @Test
    void testDerivativeVariableAndNumber() {
        Expression expr = new Sub(new Variable("x"), new Number(5));
        assertEquals("(1-0)", expr.derivative("x").print());
    }

    @Test
    void testDerivativeTwoVariables() {
        Expression expr = new Sub(new Variable("x"), new Variable("y"));
        assertEquals("(1-0)", expr.derivative("x").print());
    }

    @Test
    void testEvalVariableAndNumber() {
        Expression expr = new Sub(new Variable("x"), new Number(5));
        assertEquals(5, expr.eval("x=10"));
    }

    @Test
    void testEvalTwoVariables() {
        Expression expr = new Sub(new Variable("x"), new Variable("y"));
        assertEquals(5, expr.eval("x=10;y=5"));
    }
}