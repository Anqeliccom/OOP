package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals("(((1+0)*((y-2)/z))+((x+5)*((((0-0)*z)-((y-2)*0))/(z*z))))", derivative.print());
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

}
