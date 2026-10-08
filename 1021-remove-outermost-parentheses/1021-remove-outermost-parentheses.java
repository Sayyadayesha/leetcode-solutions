class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Outer opening bracket ko skip karo
                if (depth > 0) {
                    result.append(ch);
                }
                depth++;
            } 
            else {
                depth--;

                // Outer closing bracket ko skip karo
                if (depth > 0) {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }
}