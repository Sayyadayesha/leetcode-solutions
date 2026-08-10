class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // No stones -> current player loses

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j * j <= i; j++) {

                int remaining = i - j * j;

                // If Bob gets a losing position,
                // Alice can win by removing j*j stones.
                if (!dp[remaining]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}