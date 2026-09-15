class Solution {
    public int maxPalindromes(String s, int k) {

        int n = s.length();

        boolean[][] pal = new boolean[n][n];

        // Find all palindromes
        for (int len = 1; len <= n; len++) {

            for (int l = 0; l + len - 1 < n; l++) {

                int r = l + len - 1;

                if (s.charAt(l) == s.charAt(r) &&
                    (len <= 2 || pal[l + 1][r - 1])) {

                    pal[l][r] = true;
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int r = 0; r < n; r++) {

            // Don't select a substring ending at r
            dp[r + 1] = dp[r];

            for (int l = 0; l <= r; l++) {

                int len = r - l + 1;

                if (len >= k && pal[l][r]) {

                    dp[r + 1] = Math.max(
                        dp[r + 1],
                        dp[l] + 1
                    );
                }
            }
        }

        return dp[n];
    }
}