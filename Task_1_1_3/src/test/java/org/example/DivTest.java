package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

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

    // simplify tests
    @Test
    public void testSimplifyDivBothNumbers() {
        Expression div = new Div(new Number(10), new Number(2));
        Expression simplified = div.simplify("");
        assertInstanceOf(Number.class, simplified);
        assertEquals(5, simplified.eval(""));
    }

    @Test
    public void testSimplifyDivWithVariable() {
        Expression div = new Div(new Variable("x"), new Number(3));
        Expression simplified = div.simplify("");
        assertInstanceOf(Div.class, simplified);
        assertEquals("(x/3)", simplified.print());
    }

    @Test
    public void testSimplifyDivBothVariables() {
        Expression div = new Div(new Variable("x"), new Variable("y"));
        Expression simplified = div.simplify("");
        assertInstanceOf(Div.class, simplified);
        assertEquals("(x/y)", simplified.print());
    }
}
