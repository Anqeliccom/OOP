package org.example;

import java.util.Scanner;
import java.util.Stack;

/**
 * Class representing a mathematical expression.
 * Supports differentiating, evaluating and simplifying expressions.
 */
public abstract class Expression {

    /**
     * Main method for user input and interaction with the expression.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение (например, (3+(2*x))): ");
        String inputExpr = scanner.nextLine();
        inputExpr = inputExpr.replaceAll("\\s", "");

        Expression expr = parseExpression(inputExpr);
        System.out.println("Введённое выражение: " + expr.toStr());

        System.out.println("Введите переменную для дифференцирования: ");
        String variable = scanner.nextLine();
        Expression derivative = expr.derivative(variable);
        System.out.println("Производная по " + variable + ": " + derivative.toStr());

        System.out.println("Введите значения переменных (например, x=10;y=5): ");
        String variables = scanner.nextLine();
        int result = expr.eval(variables);
        System.out.println("Результат вычисления: " + result);

        Expression simplified = expr.simplify(variables);
        System.out.println("Упрощённое выражение: " + simplified.toStr());

    }

    /**
     * Parses a mathematical expression from a string.
     *
     * @param expr the string containing the mathematical expression.
     * @return the parsed expression.
     */
    public static Expression parseExpression(String expr) {
        Stack<Expression> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);

            if (c == '(') {
                operators.push(c);
                i++;
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
                i++;
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && rightHasHigherPriority(c, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(c);
                i++;
            } else {
                if (Character.isDigit(c)) {
                    i = parseNumber(expr, i, values);
                } else if (Character.isLetter(c)) {
                    i = parseVariable(expr, i, values);
                } else {
                    throw new IllegalArgumentException("Невалидный символ в выражении: " + c);
                }
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static int parseNumber(String expr, int i, Stack<Expression> values) {
        int num = 0;
        while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
            num = num * 10 + (expr.charAt(i) - '0');
            i++;
        }
        values.push(new Number(num));
        return i;
    }

    private static int parseVariable(String expr, int i, Stack<Expression> values) {
        StringBuilder varName = new StringBuilder();
        while (i < expr.length() && Character.isLetter(expr.charAt(i))) {
            varName.append(expr.charAt(i));
            i++;
        }
        values.push(new Variable(varName.toString()));
        return i;
    }

    private static Expression applyOperator(char operator, Expression right, Expression left) {
        return switch (operator) {
            case '+' -> new Add(left, right);
            case '-' -> new Sub(left, right);
            case '*' -> new Mul(left, right);
            case '/' -> new Div(left, right);
            default -> throw new IllegalArgumentException("Невалидный оператор: " + operator);
        };
    }

    private static boolean rightHasHigherPriority(char op1, char op2) {
        int priority1 = getPriority(op1);
        int priority2 = getPriority(op2);
        return priority2 >= priority1;
    }

    private static int getPriority(char op) {
        return switch (op) {
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }

    /**
     * Compares this expression with another object for basic equality.
     * Delegates the comparison to the `equalsImpl` method for specific expression comparison logic.
     *
     * @param obj the object to compare with this expression.
     * @return true if the objects are the same or represent the same expression, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        return equalsImpl((Expression) obj);
    }

    /**
     * Converts the expression-object to a string.
     *
     * @return a string representation of the expression.
     */
    public abstract String toStr();

    /**
     * Computes the derivative of the expression with respect to a given variable.
     *
     * @param variable the variable to differentiate by.
     * @return the differentiated expression.
     */
    public abstract Expression derivative(String variable);

    /**
     * Evaluates the expression using the provided variable assignments.
     *
     * @param variables a string containing variable assignments separated by semicolons.
     * @return the result of evaluating the expression.
     */
    public abstract int eval(String variables);

    /**
     * Simplifies the expression according to the specified rules.
     *
     * @param variables a string containing variable assignments separated by semicolons.
     * @return the simplified expression.
     */
    public abstract Expression simplify(String variables);

    /**
     * Performs a subclass-specific equality comparison between this expression and another.
     * Called from the `equals` method after passing the basic checks.
     *
     * @param other the other expression to compare with this one.
     * @return true if both expressions are `Subclass` instances
     * with equal left and right subexpressions, false otherwise.
     */
    protected abstract boolean equalsImpl(Expression other);
}
