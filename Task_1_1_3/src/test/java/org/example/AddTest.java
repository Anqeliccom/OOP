package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class AddTest {

    @Test
    void testPrintVariableAndNumber() {
        Expression expr = new Add(new Variable("x"), new Number(5));
        assertEquals("(x+5)", expr.print());
    }

    @Test
    void testDerivativeVariableAndNumber() {
        Expression expr = new Add(new Variable("x"), new Number(5));
        assertEquals("(1+0)", expr.derivative("x").print());
    }

    @Test
    void testDerivativeTwoVariables() {
        Expression expr = new Add(new Variable("x"), new Variable("y"));
        assertEquals("(1+0)", expr.derivative("x").print());
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
        assertInstanceOf(Number.class, simplified);
        assertEquals(5, simplified.eval(""));
    }

    @Test
    public void testSimplifyAddWithVariable() {
        Expression add = new Add(new Number(3), new Variable("x"));
        Expression simplified = add.simplify("");
        assertInstanceOf(Add.class, simplified);
        assertEquals("(3+x)", simplified.print());
    }

    @Test
    public void testSimplifyAddBothVariables() {
        Expression add = new Add(new Variable("x"), new Variable("y"));
        Expression simplified = add.simplify("");
        assertInstanceOf(Add.class, simplified);
        assertEquals("(x+y)", simplified.print());
    }

    @Test
    void testSimplifyComplexAddition() {
        Expression expr = new Add(new Add(new Number(2), new Number(3)), new Variable("x"));
        Expression simplified = expr.simplify("");
        assertEquals("(5+x)", simplified.print());
    }
}
