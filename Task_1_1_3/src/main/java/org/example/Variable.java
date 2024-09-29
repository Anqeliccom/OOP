package org.example;

import java.util.Objects;

public class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String print() {
        return name;
    }

    @Override
    public Expression derivative(String variable) {
        return name.equals(variable) ? new Number(1) : new Number(0);
    }

    @Override
    public int eval(String variables) {
        String[] significations = variables.split(";");
        for (String signification : significations) {
            String[] pair = signification.split("=");
            if (pair[0].trim().equals(name)) {
                return Integer.parseInt(pair[1].trim());
            }
        }
        throw new IllegalArgumentException("Переменная " + name + " не обнаружена");
    }

    @Override
    public Expression simplify(String variables) {
        return this;
    }

    @Override
    protected boolean equalsImpl(Expression other) {
        if (!(other instanceof Variable otherVariable)) return false;
        return this.name.equals(otherVariable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


