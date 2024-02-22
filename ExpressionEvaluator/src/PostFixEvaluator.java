/*************************************************
File: PostFixEvaluator.java
By: Eiffel Valentino
Date: 2/21/2024
Description: Takes the string from ExpressionDriver.java
and evaluates the postfix expression
*************************************************/


import java.util.Stack;
import java.util.Map;

public class PostFixEvaluator {
    // Map to store identifier values
    private static final Map<String, Integer> identifiers = Map.of("a", 1, "b", 2);

    // Method to evaluate a postfix expression
    public static int evaluatePostfix(String str) {
        // Create a stack to store operands
        Stack<Integer> valueStack = new Stack<>();

        // Scan all characters one by one
        for (char token : str.toCharArray()) {
            // If the scanned character is an operand (number here), push it to the stack
            if (Character.isDigit(token)) {
                valueStack.push(token - '0'); // Convert digit character to integer
            } else if (isOperator(token)) {
                // If the scanned character is an operator, pop two elements from stack and apply the operator
                if (valueStack.size() < 2) {
                    System.out.println("Insufficient operands for operator: " + token);
                }
                int operand2 = valueStack.pop();
                int operand1 = valueStack.pop();
                valueStack.push(applyOperator(token, operand1, operand2)); // Reverse operand order for postfix
            } else if (Character.isAlphabetic(token)) { // Handle identifiers
                if (!identifiers.containsKey(String.valueOf(token))) {
                    System.out.println("Invalid identifier: " + token);
                }
                // Push the value of the identifier to the stack
                valueStack.push(identifiers.get(String.valueOf(token)));
            } else {
                System.out.println("Invalid character: " + token);
            }
        }

        // Return the result
        return valueStack.pop();
    }

    // Helper methods
    // Check if a character is an operator
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
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
