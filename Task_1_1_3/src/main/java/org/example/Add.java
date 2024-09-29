package org.example;

import java.util.Objects;

public class Add extends Expression {
    private final Expression left;
    private final Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String print() {
        return "(" + left.print() + "+" + right.print() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(left.derivative(variable), right.derivative(variable));
    }

    @Override
    public int eval(String variables) {
        return left.eval(variables) + right.eval(variables);
    }

    @Override
    public Expression simplify(String variables) {
        Expression simplifiedLeft = left.simplify(variables);
        Expression simplifiedRight = right.simplify(variables);

        if (simplifiedLeft instanceof Number && simplifiedRight instanceof Number) {
            return new Number(simplifiedLeft.eval("") + simplifiedRight.eval(""));
        }

        return new Add(simplifiedLeft, simplifiedRight);
    }

    @Override
    protected boolean equalsImpl(Expression other) {
        if (!(other instanceof Add otherAdd)) return false;
        return this.left.equals(otherAdd.left) && this.right.equals(otherAdd.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
