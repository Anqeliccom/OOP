package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Mul} class.
 * These tests validate the behavior of the {@link Mul} expression for different operations.
 */
class MulTest {

    @Test
    void testToStrVariableAndNumber() {
        Expression expr = new Mul(new Variable("x"), new Number(5));
        assertEquals("(x*5)", expr.toStr());
    }

    @Test
    void testDerivativeVariableAndNumber() {
        Expression expr = new Mul(new Variable("x"), new Number(5));
        assertEquals("((1*5)+(x*0))", expr.derivative("x").toStr());
    }

    @Test
    void testDerivativeTwoVariables() {
        Expression expr = new Mul(new Variable("x"), new Variable("y"));
        assertEquals("((1*y)+(x*0))", expr.derivative("x").toStr());
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
        assertEquals(new Number(8), simplified);
    }

    @Test
    public void testSimplifyMulWithZero() {
        Expression mul = new Mul(new Number(0), new Variable("x"));
        Expression simplified = mul.simplify("");
        assertEquals(new Number(0), simplified);
    }

    @Test
    public void testSimplifyMulWithOne() {
        Expression mul = new Mul(new Number(1), new Variable("x"));
        Expression simplified = mul.simplify("");
        assertEquals("x", simplified.toStr());
    }

    @Test
    public void testSimplifyMulBothVariables() {
        Expression mul = new Mul(new Variable("x"), new Variable("y"));
        Expression simplified = mul.simplify("");
        assertEquals("(x*y)", simplified.toStr());
    }

    @Test
    public void testEquals() {
        Expression mul1 = new Mul(new Number(4), new Variable("x"));
        Expression mul2 = new Mul(new Number(4), new Variable("x"));
        Expression mul3 = new Mul(new Number(5), new Variable("y"));
        assertTrue(mul1.equals(mul2));
        assertFalse(mul1.equals(mul3));
    }

    @Test
    public void testHashCode() {
        Expression mul1 = new Mul(new Number(4), new Variable("x"));
        Expression mul2 = new Mul(new Number(4), new Variable("x"));
        assertEquals(mul1.hashCode(), mul2.hashCode());
    }
}
