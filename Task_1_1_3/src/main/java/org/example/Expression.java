package org.example;

import java.util.Scanner;
import java.util.Stack;

public abstract class Expression {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение (например, (3+(2*x))): ");
        String inputExpr = scanner.nextLine();
        inputExpr = inputExpr.replaceAll("\\s", "");

        Expression expr = parseExpression(inputExpr);
        System.out.println("Введённое выражение: " + expr.print());

        System.out.println("Введите переменную для дифференцирования: ");
        String variable = scanner.nextLine();
        Expression derivative = expr.derivative(variable);
        System.out.println("Производная по " + variable + ": " + derivative.print());

        System.out.println("Введите значения переменных (например, x=10;y=5): ");
        String variables = scanner.nextLine();
        int result = expr.eval(variables);
        System.out.println("Результат вычисления: " + result);
    }

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

    public abstract String print();

    public abstract Expression derivative(String variable);

    public abstract int eval(String variables);
}
