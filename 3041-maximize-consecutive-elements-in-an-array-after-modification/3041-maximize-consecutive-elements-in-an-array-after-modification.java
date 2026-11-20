
import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[1000002];
        int ans = 0;

        for (int x : nums) {
            // Store old values before updating
            int same = dp[x];
            int prev = dp[x - 1];

            // Option 1: Keep x unchanged
            dp[x] = Math.max(dp[x], prev + 1);

            // Option 2: Increase x by 1
            dp[x + 1] = Math.max(dp[x + 1], same + 1);

            ans = Math.max(ans, Math.max(dp[x], dp[x + 1]));
        }

        return ans;
    }
}
