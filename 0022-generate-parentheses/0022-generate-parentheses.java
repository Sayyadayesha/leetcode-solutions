import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack("", 0, 0, n, result);

        return result;
    }

    void backtrack(String current, int open, int close,
                   int n, List<String> result) {

        // Base case
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // Add opening bracket
        if (open < n) {
            backtrack(current + "(", open + 1, close, n, result);
        }

        // Add closing bracket
        if (close < open) {
            backtrack(current + ")", open, close + 1, n, result);
        }
    }
}