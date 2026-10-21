public class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Already sorted -> 0
        boolean sorted = true;
        for (int i = 0; i + 1 < n; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;

        // Length 2 and unsorted -> impossible
        if (n == 2) return -1;

        char mn = 'z', mx = 'a';
        int cntMn = 0, cntMx = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < mn) mn = c;
            if (c > mx) mx = c;
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == mn) cntMn++;
            if (c == mx) cntMx++;
        }

        // Min already at the front, or max already at the end -> 1
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) return 1;

        // Max is a lone character at the front and min is a lone character at the end -> 3
        if (s.charAt(0) == mx && s.charAt(n - 1) == mn && cntMx == 1 && cntMn == 1) {
            return 3;
        }

        // Otherwise 2
        return 2;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.minOperations("dog"));  // 1
        System.out.println(sol.minOperations("card")); // 2
        System.out.println(sol.minOperations("gf"));   // -1
        System.out.println(sol.minOperations("cba"));  // 3
    }
}