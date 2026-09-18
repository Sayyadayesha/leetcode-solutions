import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if (first[c] == -1) {
                first[c] = i;
            }

            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try to create the smallest valid interval for each character
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            for (int i = left; i <= right; i++) {

                int curr = s.charAt(i) - 'a';

                // This character appeared before our left boundary.
                // Therefore we cannot create a valid substring starting here.
                if (first[curr] < left) {
                    valid = false;
                    break;
                }

                // We must include ALL occurrences of curr.
                right = Math.max(right, last[curr]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        /*
         * We want:
         * 1. Maximum number of non-overlapping intervals.
         * 2. Among those, minimum total length.
         *
         * Sort by ending position.
         */
        intervals.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }

            return Integer.compare(
                a[1] - a[0],
                b[1] - b[0]
            );
        });

        List<String> answer = new ArrayList<>();

        int prevEnd = -1;

        for (int[] interval : intervals) {

            int left = interval[0];
            int right = interval[1];

            if (left > prevEnd) {
                answer.add(s.substring(left, right + 1));
                prevEnd = right;
            }
        }

        return answer;
    }
}