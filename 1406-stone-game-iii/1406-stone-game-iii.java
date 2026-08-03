class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;

        int[] dp = new int[n + 1];

        // dp[n] = 0 by default

        for (int i = n - 1; i >= 0; i--) {
            int currSum = 0;
            dp[i] = Integer.MIN_VALUE;

            // Ek turn me 1,2 ya 3 stones le sakte hain
            for (int k = 0; k < 3 && i + k < n; k++) {
                currSum += stoneValue[i + k];

                // Current player ka advantage
                dp[i] = Math.max(dp[i], currSum - dp[i + k + 1]);
            }
        }

        if (dp[0] > 0)
            return "Alice";
        else if (dp[0] < 0)
            return "Bob";
        else
            return "Tie";
    }
}