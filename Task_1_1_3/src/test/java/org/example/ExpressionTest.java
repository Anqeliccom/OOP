package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

/**
 * Unit tests for the {@link Expression} class.
 * These tests cover parsing, differentiation, evaluation and simplification of complex expressions.
 */
class ExpressionTest {

    @Test
    void testToStrSimpleExpression() {
        Expression expr = Expression.parseExpression("(x+5)");
        Expression expected = new Add(new Variable("x"), new Number(5));
        assertEquals("(x+5)", expr.toStr());
        assertEquals(expected, expr);
    }

    @Test
    void testDerivativeComplexExpression() {
        Expression expr = Expression.parseExpression("((x+5)*y)");
        Expression derivative = expr.derivative("x");
        Expression expected = new Add(
            new Mul(new Add(new Number(1), new Number(0)), new Variable("y")),
            new Mul(new Add(new Variable("x"), new Number(5)), new Number(0))
        );
        assertEquals("(((1+0)*y)+((x+5)*0))", derivative.toStr());
        assertEquals(expected, derivative);
    }

    @Test
    void testDerivativeLargeExpression() {
        Expression expr = Expression.parseExpression("((x+5)*((y-2)/z))");
        Expression derivative = expr.derivative("x");
        Expression expected = new Add(
            new Mul(new Add(new Number(1), new Number(0)), new Div(new Sub(new Variable("y"), new Number(2)), new Variable("z"))),
            new Mul(new Add(new Variable("x"), new Number(5)), new Div(
                new Sub(
                    new Mul(new Sub(new Number(0), new Number(0)), new Variable("z")),
                    new Mul(new Sub(new Variable("y"), new Number(2)), new Number(0))
                ),
                new Mul(new Variable("z"), new Variable("z"))
            ))
        );
        assertEquals("(((1+0)*((y-2)/z))+((x+5)*((((0-0)*z)-((y-2)*0))/(z*z))))",
            derivative.toStr());
        assertEquals(expected, derivative);
    }

    @Test
    void testEvalComplexExpression() {
        Expression expr = Expression.parseExpression("((x+5)*((y-2)/z))");
        assertEquals(50, expr.eval("x=5;y=7;z=1"));
    }

    @Test
    void testEvalMultipleOccurrences() {
        Expression expr = Expression.parseExpression("((x+1)-(x+1))");
        assertEquals(0, expr.eval("x=5"));
    }

    @Test
    void testEvalDivideByZero() {
        Expression expr = Expression.parseExpression("(x/0)");
        assertThrows(ArithmeticException.class, () -> expr.eval("x=10"));
    }

    // simplify tests
    @Test
    public void testSimplifyIdenticalExpressions() {
        Expression complex = new Mul(
            new Add(new Number(3), new Number(4)),
            new Sub(new Variable("x"), new Variable("x"))
        );
        Expression simplified = complex.simplify("");
        assertEquals(new Number(0), simplified);
    }

    @Test
    public void testSimplifyWithConstants() {
        Expression expr = new Mul(
            new Number(2),
            new Add(new Number(3), new Number(5))
        );
        Expression simplified = expr.simplify("");
        assertEquals(new Number(16), simplified);
    }

    @Test
    public void testSimplifySubComplexIdenticalExpressions() {
        Expression expr1 = new Mul(
            new Add(new Variable("x"), new Number(2)),
            new Sub(new Variable("y"), new Number(3))
        );
        Expression expr2 = new Mul(
            new Add(new Variable("x"), new Number(2)),
            new Sub(new Variable("y"), new Number(3))
        );
        Expression subExpr = new Sub(expr1, expr2);
        Expression simplified = subExpr.simplify("");
        assertEquals(new Number(0), simplified);
    }

    @Test
    public void testEquals() {
        Expression expr1 = new Add(new Number(4), new Variable("x"));
        Expression expr2 = new Add(new Number(4), new Variable("x"));
        Expression expr3 = new Add(new Number(4), new Variable("y"));
        assertTrue(expr1.equals(expr2));
        assertFalse(expr1.equals(expr3));
    }

    @Test
    public void testHashCode() {
        Expression add1 = new Add(new Number(4), new Variable("x"));
        Expression add2 = new Add(new Number(4), new Variable("x"));
        assertEquals(add1.hashCode(), add2.hashCode());
    }

    @Test
    void testMain() {
        String input = """
            (3+(2*x))
            x
            x=10
            """;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Expression.main(new String[]{});
    }
}
