package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Sub} class.
 * These tests validate the behavior of the {@link Sub} expression for different operations.
 */
class SubTest {

    @Test
    void testToStrVariableAndNumber() {
        Expression expr = new Sub(new Variable("x"), new Number(5));
        assertEquals("(x-5)", expr.toStr());
    }

    @Test
    void testDerivativeVariableAndNumber() {
        Expression expr = new Sub(new Variable("x"), new Number(5));
        assertEquals("(1-0)", expr.derivative("x").toStr());
    }

    @Test
    void testDerivativeTwoVariables() {
        Expression expr = new Sub(new Variable("x"), new Variable("y"));
        assertEquals("(1-0)", expr.derivative("x").toStr());
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

    @Test
    public void testSimplifySubBothNumbers() {
        Expression sub = new Sub(new Number(5), new Number(2));
        Expression simplified = sub.simplify("");
        assertEquals(new Number(3), simplified);
    }

    @Test
    public void testSimplifySubWithVariable() {
        Expression sub = new Sub(new Variable("x"), new Number(3));
        Expression simplified = sub.simplify("");
        assertEquals("(x-3)", simplified.toStr());
    }

    @Test
    public void testSimplifySubDifferentVariables() {
        Expression sub = new Sub(new Variable("x"), new Variable("y"));
        Expression simplified = sub.simplify("");
        assertEquals("(x-y)", simplified.toStr());
    }

    @Test
    public void testSimplifySubBothVariablesEqual() {
        Expression sub = new Sub(new Variable("x"), new Variable("x"));
        Expression simplified = sub.simplify("");
        assertEquals(new Number(0), simplified);
    }

    @Test
    public void testSimplifySubComplexEqualExpressions() {
        Expression sub = new Sub(
            new Add(new Variable("x"), new Variable("y")),
            new Add(new Variable("x"), new Variable("y"))
        );
        Expression simplified = sub.simplify("");
        assertEquals(new Number(0), simplified);
    }

    @Test
    public void testEquals() {
        Expression sub1 = new Sub(new Number(4), new Variable("x"));
        Expression sub2 = new Sub(new Number(4), new Variable("x"));
        Expression sub3 = new Sub(new Number(5), new Variable("y"));
        assertTrue(sub1.equals(sub2));
        assertFalse(sub1.equals(sub3));
    }

    @Test
    public void testHashCode() {
        Expression sub1 = new Sub(new Number(4), new Variable("x"));
        Expression sub2 = new Sub(new Number(4), new Variable("x"));
        assertEquals(sub1.hashCode(), sub2.hashCode());
    }
}