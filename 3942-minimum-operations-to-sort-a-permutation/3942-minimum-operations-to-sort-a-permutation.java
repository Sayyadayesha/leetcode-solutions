class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        boolean sorted = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) { sorted = false; break; }
        }
        if (sorted) return 0;

        int k = nums[0];
        long best = Long.MAX_VALUE;

        // Cyclic increasing: nums[i] = (k + i) % n
        boolean inc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (k + i) % n) { inc = false; break; }
        }
        if (inc) {
            // rotate left (n-k) times, or reverse + rotate k times + reverse
            best = Math.min(best, Math.min(n - k, k + 2));
        }

        // Cyclic decreasing: nums[i] = ((k - i) % n + n) % n
        boolean dec = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != ((k - i) % n + n) % n) { dec = false; break; }
        }
        if (dec) {
            // reverse then rotate left, or rotate left then reverse
            best = Math.min(best, 1 + (n - k - 1) % n);
            best = Math.min(best, (k + 1) % n + 1);
        }

        return best == Long.MAX_VALUE ? -1 : (int) best;
    }
}