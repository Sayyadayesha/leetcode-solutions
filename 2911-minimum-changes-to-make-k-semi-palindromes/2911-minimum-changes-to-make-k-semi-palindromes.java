
import java.util.*;

class Solution {

    public int minimumChanges(String s, int k) {

        int n = s.length();

        // Step 1: Precompute substring costs
        int[][] cost = new int[n][n];

        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n; r++) {

                int len = r - l + 1;
                int minCost = Integer.MAX_VALUE;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each group
                    for (int rem = 0; rem < d; rem++) {

                        int left = rem;
                        int right = len - d + rem;

                        while (left < right) {

                            if (s.charAt(l + left) !=
                                s.charAt(l + right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[l][r] = minCost;
            }
        }

        // Step 2: DP
        int INF = 1000000;

        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        // i = number of characters considered
        for (int i = 2; i <= n; i++) {

            // j = number of partitions
            for (int j = 1; j <= k; j++) {

                // p = starting index of last substring
                for (int p = 0; p <= i - 2; p++) {

                    if (dp[p][j - 1] == INF) {
                        continue;
                    }

                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[p][j - 1] + cost[p][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
