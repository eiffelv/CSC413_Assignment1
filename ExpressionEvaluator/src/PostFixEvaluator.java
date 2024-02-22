import java.util.Stack;
import java.util.Map;

public class PostFixEvaluator {
    private static final Map<String, Integer> identifiers = Map.of("a", 10, "b", 20); // Modify with your identifiers and values

    public static int evaluatePostfix(String str) {
        // Add your logic here to evaluate the postfix expression
        Stack<Integer> values = new Stack<>();

        for (char token : str.toCharArray()) {
            if (Character.isDigit(token)) {
                values.push(token - '0'); // Convert digit character to integer
            } else if (isOperator(token)) {
                if (values.size() < 2) {
                    System.out.println("Insufficient operands for operator: " + token);
                }
                int operand2 = values.pop();
                int operand1 = values.pop();
                values.push(applyOperator(token, operand1, operand2)); // Reverse operand order for postfix
            } else if (Character.isAlphabetic(token)) { // Handle identifiers
                if (!identifiers.containsKey(String.valueOf(token))) {
                    System.out.println("Invalid identifier: " + token);
                }
                values.push(identifiers.get(String.valueOf(token)));
            } else {
                System.out.println("Invalid character: " + token);
            }
        }

        if (values.size() != 1) {
            System.out.println("Invalid expression: Missing operands or extra operators.");
        }

        return values.pop();
    }
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

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
