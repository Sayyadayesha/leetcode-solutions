import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] P = new long[n + 1];
        for (int i = 0; i < n; i++) {
            P[i + 1] = P[i] + nums[i];
        }

        // each block: {val, start, end}
        Deque<long[]> dq = new ArrayDeque<>();
        long cost = 0;
        int r = n - 1;
        long ans = 0;

        for (int l = n - 1; l >= 0; l--) {
            long v = nums[l];
            int end = l;

            // merge front blocks whose prefix max is <= nums[l]
            while (!dq.isEmpty() && dq.peekFirst()[0] <= v) {
                long[] b = dq.pollFirst();
                cost -= b[0] * (b[2] - b[1] + 1) - (P[(int) b[2] + 1] - P[(int) b[1]]);
                end = (int) b[2];
            }
            cost += v * (end - l + 1) - (P[end + 1] - P[l]);
            dq.addFirst(new long[]{v, l, end});

            // shrink the right end until the cost fits in k
            while (cost > k) {
                long[] back = dq.peekLast();
                cost -= back[0] - nums[r];
                back[2]--;
                if (back[2] < back[1]) {
                    dq.pollLast();
                }
                r--;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}