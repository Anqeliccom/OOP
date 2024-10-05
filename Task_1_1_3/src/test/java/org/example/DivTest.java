package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Div} class.
 * These tests validate the behavior of the {@link Div} expression for different operations.
 */
class DivTest {

    @Test
    void testToStrVariableAndNumber() {
        Expression expr = new Div(new Variable("x"), new Number(5));
        assertEquals("(x/5)", expr.toStr());
    }

    @Test
    void testDerivativeVariableAndNumber() {
        Expression expr = new Div(new Variable("x"), new Number(5));
        assertEquals("(((1*5)-(x*0))/(5*5))", expr.derivative("x").toStr());
    }

    @Test
    void testDerivativeTwoVariables() {
        Expression expr = new Div(new Variable("x"), new Variable("y"));
        assertEquals("(((1*y)-(x*0))/(y*y))", expr.derivative("x").toStr());
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
        assertEquals(new Number(5), simplified);
    }

    @Test
    public void testSimplifyDivWithVariable() {
        Expression div = new Div(new Variable("x"), new Number(3));
        Expression simplified = div.simplify("");
        assertEquals("(x/3)", simplified.toStr());
    }

    @Test
    public void testSimplifyDivBothVariables() {
        Expression div = new Div(new Variable("x"), new Variable("y"));
        Expression simplified = div.simplify("");
        assertEquals("(x/y)", simplified.toStr());
    }

    @Test
    public void testEquals() {
        Expression div1 = new Div(new Number(4), new Variable("x"));
        Expression div2 = new Div(new Number(4), new Variable("x"));
        Expression div3 = new Div(new Number(5), new Variable("y"));
        assertTrue(div1.equals(div2));
        assertFalse(div1.equals(div3));
    }

    @Test
    public void testHashCode() {
        Expression div1 = new Div(new Number(4), new Variable("x"));
        Expression div2 = new Div(new Number(4), new Variable("x"));
        assertEquals(div1.hashCode(), div2.hashCode());
    }
}
