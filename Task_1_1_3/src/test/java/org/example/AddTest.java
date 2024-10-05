package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Add} class.
 * These tests validate the behavior of the {@link Add} expression for different operations.
 */
class AddTest {

    @Test
    void testToStrVariableAndNumber() {
        Expression expr = new Add(new Variable("x"), new Number(5));
        assertEquals("(x+5)", expr.toStr());
    }

    @Test
    void testDerivativeVariableAndNumber() {
        Expression expr = new Add(new Variable("x"), new Number(5));
        assertEquals("(1+0)", expr.derivative("x").toStr());
    }

    @Test
    void testDerivativeTwoVariables() {
        Expression expr = new Add(new Variable("x"), new Variable("y"));
        assertEquals("(1+0)", expr.derivative("x").toStr());
    }

    @Test
    void testEvalVariableAndNumber() {
        Expression expr = new Add(new Variable("x"), new Number(5));
        assertEquals(15, expr.eval("x=10"));
    }

    @Test
    void testEvalTwoVariables() {
        Expression expr = new Add(new Variable("x"), new Variable("y"));
        assertEquals(15, expr.eval("x=10;y=5"));
    }

    // simplify tests
    @Test
    public void testSimplifyAddBothNumbers() {
        Expression add = new Add(new Number(3), new Number(2));
        Expression simplified = add.simplify("");
        assertEquals(new Number(5), simplified);
    }

    @Test
    public void testSimplifyAddWithVariable() {
        Expression add = new Add(new Number(3), new Variable("x"));
        Expression simplified = add.simplify("");
        assertEquals("(3+x)", simplified.toStr());
    }

    @Test
    public void testSimplifyAddBothVariables() {
        Expression add = new Add(new Variable("x"), new Variable("y"));
        Expression simplified = add.simplify("");
        assertEquals("(x+y)", simplified.toStr());
    }

    @Test
    void testSimplifyComplexAddition() {
        Expression expr = new Add(new Add(new Number(2), new Number(3)), new Variable("x"));
        Expression simplified = expr.simplify("");
        assertEquals("(5+x)", simplified.toStr());
    }

    @Test
    public void testEquals() {
        Expression add1 = new Add(new Number(4), new Variable("x"));
        Expression add2 = new Add(new Number(4), new Variable("x"));
        Expression add3 = new Add(new Number(5), new Variable("y"));
        assertTrue(add1.equals(add2));
        assertFalse(add1.equals(add3));
    }

    @Test
    public void testHashCode() {
        Expression add1 = new Add(new Number(4), new Variable("x"));
        Expression add2 = new Add(new Number(4), new Variable("x"));
        assertEquals(add1.hashCode(), add2.hashCode());
    }
}
