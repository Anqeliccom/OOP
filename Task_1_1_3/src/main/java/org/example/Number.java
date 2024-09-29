package org.example;

import java.util.Objects;

public class Number extends Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public String print() {
        return Integer.toString(value);
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    public int eval(String variables) {
        return value;
    }

    @Override
    public Expression simplify(String variables) {
        return this;
    }

    @Override
    protected boolean equalsImpl(Expression other) {
        if (!(other instanceof Number otherNumber)) return false;
        return this.value == otherNumber.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
