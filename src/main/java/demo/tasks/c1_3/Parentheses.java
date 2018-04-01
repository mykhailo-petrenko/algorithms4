package demo.tasks.c1_3;

import data.structures.list.lifo.LinkedListStack;
import data.structures.list.lifo.Stack;

/**
 * Check parentheses balance using stack
 */
public class Parentheses {
    private static String open  = "({[<";
    private static String close = ")}]>";

    public static boolean isBalanced(String expression) {
        int n = expression.length();
        Stack<Character> symbols = new LinkedListStack<>();

        for (int i=0; i<n; i++) {
            char current = expression.charAt(i);

            if (isOpen(current)) {
                symbols.push(current);
            } else if (isCLose(current)) {
                if (symbols.isEmpty()) {
                    return false;
                }

                char opposite = symbols.pop();

                if (isMatch(opposite, current)) {
                    continue;
                }

                return false;
            }
        }

        return symbols.isEmpty();
    }

    private static boolean isOpen(char brace) {
        return open.indexOf(brace) != -1;
    }

    private static boolean isCLose(char brace) {
        return close.indexOf(brace) != -1;
    }

    private static boolean isMatch(char open, char close) {
        return Parentheses.open.indexOf(open) == Parentheses.close.indexOf(close);
    }
}
