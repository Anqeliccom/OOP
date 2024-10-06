package org.example;

import java.util.Objects;


/**
 * Represents the subtraction operation in an expression.
 */
public class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs a subtraction expression with two operands.
     *
     * @param left the left operand.
     * @param right the right operand.
     */
    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toStr() {
        return "(" + left.toStr() + "-" + right.toStr() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    @Override
    public int eval(String variables) {
        return left.eval(variables) - right.eval(variables);
    }

    @Override
    public Expression simplify(String variables) {
        Expression simplifiedLeft = left.simplify(variables);
        Expression simplifiedRight = right.simplify(variables);

        if (simplifiedLeft.equals(simplifiedRight)) {
            return new Number(0);
        }

        if (simplifiedLeft instanceof Number && simplifiedRight instanceof Number) {
            return new Number(simplifiedLeft.eval("") - simplifiedRight.eval(""));
        }

        return new Sub(simplifiedLeft, simplifiedRight);
    }

    @Override
    protected boolean equalsImpl(Expression other) {
        if (!(other instanceof Sub otherSub)) {
            return false;
        }
        return this.left.equals(otherSub.left) && this.right.equals(otherSub.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
