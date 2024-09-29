package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

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

    // simplify tests
    @Test
    public void testSimplifySubBothNumbers() {
        Expression sub = new Sub(new Number(5), new Number(2));
        Expression simplified = sub.simplify("");
        assertInstanceOf(Number.class, simplified);
        assertEquals(3, simplified.eval(""));
    }

    @Test
    public void testSimplifySubWithVariable() {
        Expression sub = new Sub(new Variable("x"), new Number(3));
        Expression simplified = sub.simplify("");
        assertInstanceOf(Sub.class, simplified);
        assertEquals("(x-3)", simplified.print());
    }

    @Test
    public void testSimplifySubDifferentVariables() {
        Expression sub = new Sub(new Variable("x"), new Variable("y"));
        Expression simplified = sub.simplify("");
        assertInstanceOf(Sub.class, simplified);
        assertEquals("(x-y)", simplified.print());
    }

    @Test
    public void testSimplifySubBothVariablesEqual() {
        Expression sub = new Sub(new Variable("x"), new Variable("x"));
        Expression simplified = sub.simplify("");
        assertInstanceOf(Number.class, simplified);
        assertEquals(0, simplified.eval(""));
    }

    @Test
    public void testSimplifySubComplexEqualExpressions() {
        Expression sub = new Sub(
            new Add(new Variable("x"), new Variable("y")),
            new Add(new Variable("x"), new Variable("y"))
        );
        Expression simplified = sub.simplify("");
        assertInstanceOf(Number.class, simplified);
        assertEquals(0, simplified.eval(""));
    }
}