import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();

        int n = nums.length;

        for (int i = 0; i <= n - k; i++) {

            // Stores distinct elements of the current window
            Set<Integer> set = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                set.add(nums[j]);
            }

            // Each element is present in this subarray
            // exactly once for counting purposes
            for (int x : set) {
                count.put(x, count.getOrDefault(x, 0) + 1);
            }
        }

        int answer = -1;

        // Find the largest element appearing in exactly one window
        for (int x : count.keySet()) {
            if (count.get(x) == 1) {
                answer = Math.max(answer, x);
            }
        }

        return answer;
    }
}