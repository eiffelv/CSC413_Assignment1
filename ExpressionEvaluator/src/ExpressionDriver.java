/*E.	Reading in values for each identifier in the expressions above
(a) While the infix and postfix expressions are as shown above, the values for the identifiers will be provided at tun time by the user from the console
(i) The ExpressionDriver adaptor class stated 2(A) above should have a while loop that will:
-	Ask user to enter values, either integer or double, for the 4 identifiers a, b, c and d, which should be stored in the application
-	Call the evaluateInfix and evaluatePostfix methods which in turn will use the user provided values for each of the 4 identifiers(a, b, c, d), assign the values to each identifier, compute the final value for that expression and print the computed value in the following format for the two expression types:

“Value of infix string (a+b)*(c+d) with a = 10, b = 3, c = 3, d = 4 is 52” “Value of postfix string ac−b^d+ with a = 10, b = 3, c = 3, d = 4 is 68”

-	The while loop, as stated above, in each of its iteration should ask the user if computing is needed. If the user responds “yes”, identifier values will be solicited from user and both expressions are computer, If, however, the user chooses to answer “no”, the program should be ended.
 */

import java.util.Scanner;

public class ExpressionDriver {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter values for the identifiers a, b, c, and d: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            int d = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            String infix = "("+a+"+"+b+")*("+c+"+"+d+")";
            String postfix = "ac-b^d+";
            System.out.println("Value of infix string " + infix + " with a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " is " + InfixEvaluator.evaluateInfix(infix));
            //System.out.println("Value of postfix string " + postfix + " with a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " is " + PostFixEvaluator.evaluatePostfix(postfix));
            System.out.println("Do you want to continue? (yes/no)");
            if (scanner.nextLine().equalsIgnoreCase("no")) {
                break;
            }
        }
        scanner.close();
    }
}
