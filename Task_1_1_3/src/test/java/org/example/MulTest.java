package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class MulTest {

    @Test
    void testPrintVariableAndNumber() {
        Expression expr = new Mul(new Variable("x"), new Number(5));
        assertEquals("(x*5)", expr.print());
    }

    @Test
    void testDerivativeVariableAndNumber() {
        Expression expr = new Mul(new Variable("x"), new Number(5));
        assertEquals("((1*5)+(x*0))", expr.derivative("x").print());
    }

    @Test
    void testDerivativeTwoVariables() {
        Expression expr = new Mul(new Variable("x"), new Variable("y"));
        assertEquals("((1*y)+(x*0))", expr.derivative("x").print());
    }

    @Test
    void testEvalVariableAndNumber() {
        Expression expr = new Mul(new Variable("x"), new Number(5));
        assertEquals(50, expr.eval("x=10"));
    }

    @Test
    void testEvalTwoVariables() {
        Expression expr = new Mul(new Variable("x"), new Variable("y"));
        assertEquals(50, expr.eval("x=10;y=5"));
    }

    // simplify tests
    @Test
    public void testSimplifyMulBothNumbers() {
        Expression mul = new Mul(new Number(4), new Number(2));
        Expression simplified = mul.simplify("");
        assertInstanceOf(Number.class, simplified);
        assertEquals(8, simplified.eval(""));
    }

    @Test
    public void testSimplifyMulWithZero() {
        Expression mul = new Mul(new Number(0), new Variable("x"));
        Expression simplified = mul.simplify("");
        assertInstanceOf(Number.class, simplified);
        assertEquals(0, simplified.eval(""));
    }

    @Test
    public void testSimplifyMulWithOne() {
        Expression mul = new Mul(new Number(1), new Variable("x"));
        Expression simplified = mul.simplify("");
        assertInstanceOf(Variable.class, simplified);
        assertEquals("x", simplified.print());
    }

    @Test
    public void testSimplifyMulBothVariables() {
        Expression mul = new Mul(new Variable("x"), new Variable("y"));
        Expression simplified = mul.simplify("");
        assertInstanceOf(Mul.class, simplified);
        assertEquals("(x*y)", simplified.print());
    }
}
