package org.example;

import java.util.Objects;

/**
 * Represents the division operation in an expression.
 */
public class Div extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs a division expression with two operands.
     *
     * @param left the left operand.
     * @param right the right operand.
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toStr() {
        return "(" + left.toStr() + "/" + right.toStr() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Div(
            new Sub(
                new Mul(left.derivative(variable), right),
                new Mul(left, right.derivative(variable))
            ),
            new Mul(right, right)
        );
    }

    @Override
    public int eval(String variables) {
        return left.eval(variables) / right.eval(variables);
    }

    @Override
    public Expression simplify(String variables) {
        Expression simplifiedLeft = left.simplify(variables);
        Expression simplifiedRight = right.simplify(variables);

        if (simplifiedLeft instanceof Number && simplifiedRight instanceof Number) {
            return new Number(simplifiedLeft.eval("") / simplifiedRight.eval(""));
        }

        return new Div(simplifiedLeft, simplifiedRight);
    }

    @Override
    protected boolean equalsImpl(Expression other) {
        if (!(other instanceof Div otherDiv)) {
            return false;
        }
        return this.left.equals(otherDiv.left) && this.right.equals(otherDiv.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
