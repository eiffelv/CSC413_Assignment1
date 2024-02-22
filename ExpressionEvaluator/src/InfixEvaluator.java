import java.util.Stack;

public class InfixEvaluator {
    public static int evaluateInfix(String str) {

        Stack<Integer> valueStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c >= '0' && c <= '9') {
                StringBuilder sb = new StringBuilder();
                while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    sb.append(str.charAt(i++));
                }
                valueStack.push(Integer.parseInt(sb.toString()));
                i--;
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (operatorStack.peek() != '(') {
                    valueStack.push(applyOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop()));
                }
                operatorStack.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operatorStack.isEmpty() && hasPrecedence(c, operatorStack.peek())) {
                    valueStack.push(applyOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop()));
                }
                operatorStack.push(c);
            }
        }

        while (!operatorStack.isEmpty()) {
            valueStack.push(applyOperator(operatorStack.pop(), valueStack.pop(), valueStack.pop()));
        }

        return valueStack.pop();
    }
    private static boolean hasPrecedence(char op1, char op2) {
        return (op1 == '^' && op2 != '^') ||
               (op1 == '*' || op1 == '/' && (op2 == '+' || op2 == '-'));
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
