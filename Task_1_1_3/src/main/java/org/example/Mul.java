package org.example;

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
    public Expression simplify() {
        Expression simplifiedLeft = left.simplify();
        Expression simplifiedRight = right.simplify();

        if (simplifiedLeft instanceof Number && simplifiedRight instanceof Number) {
            return new Number(simplifiedLeft.eval("") * simplifiedRight.eval(""));
        }

        if (simplifiedLeft.eval("") == 0 || simplifiedRight.eval("") == 0) {
            return new Number(0);
        }

        if (simplifiedLeft.eval("") == 1) {
            return simplifiedRight;
        }
        if (simplifiedRight.eval("") == 1) {
            return simplifiedLeft;
        }

        return new Mul(simplifiedLeft, simplifiedRight);
    }
}
