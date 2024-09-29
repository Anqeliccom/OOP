package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class ExpressionTest {

    @Test
    void testPrintSimpleExpression() {
        Expression expr = Expression.parseExpression("(x+5)");
        assertEquals("(x+5)", expr.print());
    }

    @Test
    void testDerivativeComplexExpression() {
        Expression expr = Expression.parseExpression("((x+5)*y)");
        Expression derivative = expr.derivative("x");
        assertEquals("(((1+0)*y)+((x+5)*0))", derivative.print());
    }

    @Test
    void testDerivativeLargeExpression() {
        Expression expr = Expression.parseExpression("((x+5)*((y-2)/z))");
        Expression derivative = expr.derivative("x");
        assertEquals("(((1+0)*((y-2)/z))+((x+5)*((((0-0)*z)-((y-2)*0))/(z*z))))",
            derivative.print());
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

        assertInstanceOf(Number.class, simplified);
        assertEquals(0, simplified.eval(""));
    }

    @Test
    public void testSimplifyWithConstants() {
        Expression expr = new Mul(
            new Number(2),
            new Add(new Number(3), new Number(5))
        );
        Expression simplified = expr.simplify("");

        assertInstanceOf(Number.class, simplified);
        assertEquals(16, simplified.eval(""));
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

        assertInstanceOf(Number.class, simplified);
        assertEquals(0, simplified.eval(""));
    }
}
