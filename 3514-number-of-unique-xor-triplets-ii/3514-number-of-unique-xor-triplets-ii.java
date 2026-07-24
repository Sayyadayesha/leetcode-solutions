class Solution {

    public int uniqueXorTriplets(int[] nums) {

        boolean[] present = new boolean[1501];

        for (int x : nums)
            present[x] = true;

        int distinct = 0;

        for (boolean b : present)
            if (b)
                distinct++;

        // If array size <=2,
        // only the distinct elements themselves are possible.
        if (nums.length <= 2)
            return distinct;

        boolean[] ans = new boolean[2048];

        for (int a = 1; a <= 1500; a++) {

            if (!present[a]) continue;

            for (int b = 1; b <= 1500; b++) {

                if (!present[b]) continue;

                for (int c = 1; c <= 1500; c++) {

                    if (!present[c]) continue;

                    ans[a ^ b ^ c] = true;
                }
            }
        }

        int count = 0;

        for (boolean b : ans)
            if (b) count++;

        return count;
    }
}