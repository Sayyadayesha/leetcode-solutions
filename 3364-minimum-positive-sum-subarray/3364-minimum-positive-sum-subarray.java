class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // subarray nums[i..j], length = j - i + 1 must be in [l, r]
            for (int j = i + l - 1; j < n && j - i + 1 <= r; j++) {
                int sum = prefix[j + 1] - prefix[i];
                if (sum > 0 && sum < ans) {
                    ans = sum;
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}