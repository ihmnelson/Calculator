package com.isaachmnelson;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Type in your equation or \"quit\" to end.");
        while (true) {
            String consoleInput = console.nextLine();
            if (consoleInput.equals("quit")) {
                break;
            }
            System.out.println("The answer is: ");
            System.out.println(produceAnswer(consoleInput));
        }
        System.out.println("Shutting down.");
        console.close();

    }

    /**
     * ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
     * test your code.
     * 
     * This function takes a String 'input' and produces the result. The input is a
     * string that needs to be evaluated. For your program, this will be the user
     * input. For example, input ==> "1 + 3" the function should return the result
     * of the expression after it has been calculated: return ==> "4" If there is an
     * error in the user input, the appropriate error message is returned.
     * 
     * @param input the arithmetic expression to be evaluated
     * @return the result of the arithmetic expression or an error message
     */
    public static String produceAnswer(String input) {
        if (input.equals("")) {
            return "";
        }

        Scanner console1 = new Scanner(input);

        int ans = 0;
        String val1;
        String operator;
        String val2;
        String whileOperator;
        String whileVal;
        int whileValue;
        int value1;
        int value2;

        // Dealing with the first three arguments
        if (console1.hasNext()) {
            val1 = console1.next();
        } else {
            console1.close();
            return "";
        }

        if (console1.hasNext()) {
            operator = console1.next();
        } else {
            console1.close();
            return val1;
        }

        if (console1.hasNext()) {
            val2 = console1.next();
        } else {
            console1.close();
            return "<ERROR> Invalid expression format.";
        }

        // Checking if the values/operators are correct
        try {
            Integer.parseInt(val1);
        } catch (NumberFormatException e) {
            console1.close();
            return "<ERROR> Invalid value: " + val1;
        }
        value1 = Integer.parseInt(val1);
        try {
            Integer.parseInt(val2);
        } catch (NumberFormatException e) {
            console1.close();
            return "<ERROR> Invalid value: " + val2;
        }
        value2 = Integer.parseInt(val2);
        if (!operator.equals("+") && !operator.equals("-") && !operator.equals("*") && !operator.equals("/")) {
            console1.close();
            return "<ERROR> Invalid operator encountered: " + operator;
        }
        // Checking for /0 error and also updating the answer.

        if (operator.equals("/") && value2 == 0) {
            console1.close();
            return "<ERROR> Cannot divide by zero.";
        }
        ans = updateAns(value1, operator, value2);

        // Continues with a while statement for eternity
        while (console1.hasNext()) {
            whileOperator = console1.next();
            if (console1.hasNext()) {
                whileVal = console1.next();
            } else {
                console1.close();
                return "<ERROR> Invalid expression format.";
            }
            if (!whileOperator.equals("+") && !whileOperator.equals("-") && !whileOperator.equals("*") && !whileOperator.equals("/")) {
                console1.close();
                return "<ERROR> Invalid operator encountered: " + whileOperator;
            }
            try {
                Integer.parseInt(whileVal);
            } catch (NumberFormatException e) {
                console1.close();
                return "<ERROR> Invalid value: " + whileVal;
            }
            whileValue = Integer.parseInt(whileVal);
            if (whileOperator.equals("/") && whileValue == 0) {
                console1.close();
                return "<ERROR> Cannot divide by zero.";
            }

            ans = updateAns(ans, whileOperator, whileValue);

        }

        console1.close();
        return "" + ans;
    }

    // Takes in the current answer, the current operator, and the current number, and determines what the new answer value should be.
    // e.g. updateAns(5, '-', 2).equals(3)
    public static int updateAns(int ans, String operator, int number) {
        if (operator.equals("+")) {
            ans += (number);
        } else if (operator.equals("-")) {
            ans -= (number);
        } else if (operator.equals("*")) {
            ans *= (number);
        } else if (operator.equals("/")) {
            ans /= (number);
        }
        return ans;
    }

    /*************************************************************************/
    /**
     * You may find this function useful in determining if a string value is a valid
     * integer or not. If you call Integer.parseInt() on a string that is not a
     * valid integer, a NumberFormatException will be thrown and your program will
     * terminate. In order to not have your program terminate on invalid integers,
     * call this function to determine if the string is a valid integer before
     * parsing it as an integer value.
     * 
     * @param strVal the string expression to test to see if it's an integer
     * @return true if string expression is an integer, false otherwise
     */
    public static boolean isValidNumber(String strVal) {
        try {
            Integer.parseInt(strVal);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * You may find the gcd() and lcm() functions helpful if you decide to do the
     * fraction values extra credit work. These functions implement Euclid's
     * algorithm for finding GCD (greatest common divisor) and LCM (least common
     * multiple).
     * 
     * @param a first number
     * @param b second number
     * @return greatest common divisor or least common multiple of a and b
     */
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
