/*************************************************
File: InfixEvaluator.java
By: Eiffel Valentino
Date: 2/21/2024
Description: Takes the string from ExpressionDriver.java
and evaluates the infix expression
*************************************************/

import java.util.Stack;

public class InfixEvaluator {
    // Method to evaluate an infix expression
    public static int evaluateInfix(String str) {

        // Create stacks for operators and operands
        Stack<Integer> valueStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        // Scan all characters one by one
        for (int i = 0; i < str.length(); i++) {
            char token = str.charAt(i);
            if (token == ' ') {
                continue;
            }
            // If the scanned character is an operand (number here), push it to the stack
            if (token >= '0' && token <= '9') {
                // Extract the number from the string and push it to the stack
                StringBuilder sb = new StringBuilder();
                while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    sb.append(str.charAt(i++));
                }
                valueStack.push(Integer.parseInt(sb.toString()));
                // Move the index back by one to process the next character
                i--;
            } else if (token == '(') { // If the scanned character is an '(', push it to the stack 
                operatorStack.push(token);
            } else if (token == ')') { // If the scanned character is an ')', pop and apply operators until '(' is found
                while (operatorStack.peek() != '(') {
                    valueStack.push(applyOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop()));
                }
                operatorStack.pop();
            } else if (token == '+' || token == '-' || token == '*' || token == '/') {
                // If the scanned character is an operator, pop two elements from stack and apply the operator
                while (!operatorStack.isEmpty() && hasPrecedence(token, operatorStack.peek())) {
                    valueStack.push(applyOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop()));
                }
                operatorStack.push(token);
            }
        }

        // Apply remaining operators to the remaining values
        while (!operatorStack.isEmpty()) {
            valueStack.push(applyOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop()));
        }

        // Return the result
        return valueStack.pop();
    }

    // Check if an operator has higher or equal precedence than another operator
    private static boolean hasPrecedence(char op1, char op2) {
        return (op1 == '^' && op2 != '^') ||
               (op1 == '*' || op1 == '/' && (op2 == '+' || op2 == '-'));
    }

    // Apply an operator to two operands
    private static int applyOperator(char op, int operand1, int operand2) {
        switch (op) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            case '^':
                return (int) Math.pow(operand1, operand2);
            default:
                System.out.println("Invalid operator: " + op);
                return 0;
        }
    }
}
