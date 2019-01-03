package com.calculator;

import java.util.Stack;

import com.exception.InvalidExpressionException;

public class StringCalculator {

    public static String calculate(String s) {

        try {
            return calculateString(s);
        } catch (InvalidExpressionException ie) {
            return ie.getMessage();
        }
    }


    private static String calculateString(String s) {

        Stack<Integer> numbers = new Stack<Integer>();
        Stack<Character> operators = new Stack<Character>();
        boolean isInvalid = false;
        char[] chars = s.toCharArray();

        for (int i = 0; i < s.length(); i++) {

            if (Character.isDigit(chars[i])) {

                StringBuffer sb = new StringBuffer();
                sb.append("" + chars[i]);
                while (i + 1 < s.length() && Character.isDigit(chars[i + 1])) {
                    sb.append("" + chars[i + 1]);
                    i++;
                }

                numbers.push(Integer.parseInt(sb.toString()));
            } else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/' || chars[i] == '^') {

                //Check current character operator has less priority than existing operators
                //if less priority then pop values and perform operation

                while (!operators.empty() && isLessPriority(chars[i], operators.peek())) {
                    int result = processData(numbers, operators);
                    numbers.push(result);
                }
                operators.push(chars[i]);

            } else if (chars[i] == '(') {
                operators.push(chars[i]);
            } else if (chars[i] == ')') {
                //Brackets should be solved first
                while (operators.peek() != '(') {
                    int result = processData(numbers, operators);
                    numbers.push(result);
                }
                operators.pop();  //remove '('
            }


        }
        while (!operators.empty()) {
            int result = processData(numbers, operators);
            numbers.push(result);
        }
        if (numbers.size() != 1) {
            throw new InvalidExpressionException();
        } else {
            return ""+numbers.pop();
        }


    }

    private static boolean isLessPriority(Character op1, Character op2) {

        if (op2 == '^') {
            return true;
        } else if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            return true;  //+,- has less priority than *,/
        } else if ((op1 == '*' || op1 == '/') && (op2 == '/' || op2 == '*')) {
            return true;
        } else if ((op1 == '+' || op1 == '-') && (op2 == '+' || op2 == '-')) {
            return true;
        }
        return false;
    }

    private static int performOperation(char operator, int val2, int val1) {

        switch (operator) {

            case '^':
                return (int) Math.pow(val1, val2);
            case '+':
                return val1 + val2;
            case '-':
                return val1 - val2;
            case '*':
                return val1 * val2;
            case '/':
                return val1 / val2;


            default:
                return 0;

        }
    }

    private static int processData(Stack<Integer> numbers, Stack<Character> operators) {
        int result = 0;
        Integer num1 = null;
        Integer num2 = null;
        if (!numbers.empty()) {
            num1 = numbers.pop();
        } else {
            throw new InvalidExpressionException();
        }
        if (!numbers.empty()) {
            num2 = numbers.pop();
        } else {
            throw new InvalidExpressionException();
        }
        if (!operators.empty()) {
            result = performOperation(operators.pop(), num1, num2);
        } else {
            throw new InvalidExpressionException();
        }
        return result;
    }

}
