package org.example;

import java.util.Objects;

public class Mul extends Expression {
    private final Expression left;
    private final Expression right;

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String print() {
        return "(" + left.print() + "*" + right.print() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(
            new Mul(left.derivative(variable), right),
            new Mul(left, right.derivative(variable))
        );
    }

    @Override
    public int eval(String variables) {
        return left.eval(variables) * right.eval(variables);
    }

    @Override
    public Expression simplify(String variables) {
        Expression simplifiedLeft = left.simplify(variables);
        Expression simplifiedRight = right.simplify(variables);

        if (simplifiedLeft instanceof Number && simplifiedRight instanceof Number) {
            return new Number(simplifiedLeft.eval("") * simplifiedRight.eval(""));
        }

        if (simplifiedLeft.print().equals("0") || simplifiedRight.print().equals("0")) {
            return new Number(0);
        }

        if (simplifiedLeft.print().equals("1")) {
            return simplifiedRight;
        }

        if (simplifiedRight.print().equals("1")) {
            return simplifiedLeft;
        }

        return new Mul(simplifiedLeft, simplifiedRight);
    }

    @Override
    protected boolean equalsImpl(Expression other) {
        if (!(other instanceof Mul otherMul)) return false;
        return this.left.equals(otherMul.left) && this.right.equals(otherMul.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
