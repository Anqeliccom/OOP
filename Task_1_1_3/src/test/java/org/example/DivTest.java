package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DivTest {

    @Test
    void testPrintVariableAndNumber() {
        Expression expr = new Div(new Variable("x"), new Number(5));
        assertEquals("(x/5)", expr.print());
    }

    @Test
    void testDerivativeVariableAndNumber() {
        Expression expr = new Div(new Variable("x"), new Number(5));
        assertEquals("(((1*5)-(x*0))/(5*5))", expr.derivative("x").print());
    }

    @Test
    void testDerivativeTwoVariables() {
        Expression expr = new Div(new Variable("x"), new Variable("y"));
        assertEquals("(((1*y)-(x*0))/(y*y))", expr.derivative("x").print());
    }

    @Test
    void testEvalVariableAndNumber() {
        Expression expr = new Div(new Variable("x"), new Number(5));
        assertEquals(2, expr.eval("x=10"));
    }

    @Test
    void testEvalTwoVariables() {
        Expression expr = new Div(new Variable("x"), new Variable("y"));
        assertEquals(2, expr.eval("x=10;y=5"));
    }
}
