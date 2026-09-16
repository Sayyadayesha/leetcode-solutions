class Solution {

    static final int MOD = 1000000007;

    public int numberOfSets(int n, int k) {

        long[][] dp = new long[n][k + 1];
        long[][] prefix = new long[n][k + 1];

        // 0 segments: one way for every prefix
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            prefix[i][0] = i + 1;
        }

        for (int i = 1; i < n; i++) {

            for (int j = 1; j <= k; j++) {

                // Don't use point i as the end of a segment
                dp[i][j] = dp[i - 1][j];

                // Choose a segment (p, i), p < i
                if (j == 1) {
                    dp[i][j] += i;
                } else {
                    dp[i][j] += prefix[i - 1][j - 1];
                }

                dp[i][j] %= MOD;

                // Build prefix sum
                prefix[i][j] =
                    (prefix[i - 1][j] + dp[i][j]) % MOD;
            }
        }

        return (int) dp[n - 1][k];
    }
}