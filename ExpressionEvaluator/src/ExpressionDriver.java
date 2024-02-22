/*************************************************
File: ExpressionDriver.java
By: Eiffel Valentino
Date: 2/21/2024
Description: Takes input from the user and
evaluates the infix and postfix expressions
*************************************************/

import java.util.Scanner;

public class ExpressionDriver {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter values for the identifiers a, b, c, and d: ");
            String a = scanner.nextLine();
            String b = scanner.nextLine();
            String c = scanner.nextLine();
            String d = scanner.nextLine();
            String infix = "("+a+"+"+b+")*("+c+"+"+d+")";
            String postfix = a+c+"-"+b+"^"+d+"+";
            System.out.println("Value of infix string " + infix + " with a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " is " + InfixEvaluator.evaluateInfix(infix));
            System.out.println("Value of postfix string " + postfix + " with a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " is " + PostFixEvaluator.evaluatePostfix(postfix));
            System.out.println("Do you want to continue? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("no")) {
                break;
            }
        }
        scanner.close();
    }
}
