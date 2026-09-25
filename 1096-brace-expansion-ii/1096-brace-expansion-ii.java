import java.util.*;

class Solution {

    int index = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);

        List<String> answer = new ArrayList<>(result);

        Collections.sort(answer);

        return answer;
    }

    Set<String> parse(String s) {

        Set<String> result = new HashSet<>();

        while (index < s.length() && s.charAt(index) != '}') {

            Set<String> current;

            if (s.charAt(index) == '{') {
                index++; // skip {

                current = parse(s);

                index++; // skip }

            } else {
                current = new HashSet<>();
                current.add(String.valueOf(s.charAt(index)));
                index++;
            }

            if (result.isEmpty()) {
                result = current;
            } else {
                result = multiply(result, current);
            }

            if (index < s.length() && s.charAt(index) == ',') {
                index++;

                Set<String> next = parse(s);

                result.addAll(next);
                break;
            }
        }

        return result;
    }

    Set<String> multiply(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}