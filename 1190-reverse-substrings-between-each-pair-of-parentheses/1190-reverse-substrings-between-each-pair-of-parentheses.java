import java.util.*;

class Solution {
    public String reverseParentheses(String s) {

        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save the string before '('
                stack.push(current);

                // Start a new string inside brackets
                current = new StringBuilder();
            }

            else if (ch == ')') {
                // Reverse current substring
                current.reverse();

                // Get the string before '('
                StringBuilder previous = stack.pop();

                // Add reversed substring to it
                previous.append(current);

                current = previous;
            }

            else {
                current.append(ch);
            }
        }

        return current.toString();
    }
}