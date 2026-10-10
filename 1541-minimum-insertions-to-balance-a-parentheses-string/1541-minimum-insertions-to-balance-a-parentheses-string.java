class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                // Check whether the next character is also ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Insert a ')' to complete the pair
                    insertions++;
                }

                if (open > 0) {
                    open--;
                } else {
                    // Insert a '(' to match this closing pair
                    insertions++;
                }
            }
        }

        // Each unmatched '(' needs two ')'
        insertions += open * 2;

        return insertions;
    }
}