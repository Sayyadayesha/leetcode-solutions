import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        HashMap<String, String> map = new HashMap<>();

        // Store knowledge in HashMap
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < s.length()) {

            // Normal character
            if (s.charAt(i) != '(') {
                result.append(s.charAt(i));
                i++;
            } 
            else {
                // Skip '('
                i++;

                StringBuilder key = new StringBuilder();

                // Read key until ')'
                while (s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }

                // Check key in HashMap
                if (map.containsKey(key.toString())) {
                    result.append(map.get(key.toString()));
                } else {
                    result.append("?");
                }

                // Skip ')'
                i++;
            }
        }

        return result.toString();
    }
}