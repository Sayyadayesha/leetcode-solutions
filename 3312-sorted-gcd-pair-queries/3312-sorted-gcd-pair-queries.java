class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int MAX = 0;
        for (int x : nums) MAX = Math.max(MAX, x);

        int[] freq = new int[MAX + 1];
        for (int x : nums) freq[x]++;

        long[] exact = new long[MAX + 1];

        // exact[d] = pairs with gcd exactly d
        for (int d = MAX; d >= 1; d--) {

            long cnt = 0;

            for (int m = d; m <= MAX; m += d)
                cnt += freq[m];

            long pairs = cnt * (cnt - 1) / 2;

            for (int m = d + d; m <= MAX; m += d)
                pairs -= exact[m];

            exact[d] = pairs;
        }

        long[] prefix = new long[MAX + 1];
        for (int i = 1; i <= MAX; i++)
            prefix[i] = prefix[i - 1] + exact[i];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1; // convert to 1-based order

            int l = 1, r = MAX;

            while (l < r) {
                int mid = (l + r) >>> 1;

                if (prefix[mid] >= k)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}