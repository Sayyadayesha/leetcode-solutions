
class Solution {

    public int maxLength(int[] nums) {

        int n = nums.length;
        int maxLen = 1;

        for (int i = 0; i < n; i++) {

            long product = 1;
            long gcd = nums[i];
            long lcm = nums[i];

            for (int j = i; j < n; j++) {

                product *= nums[j];

                gcd = gcd(gcd, nums[j]);

                lcm = (lcm / gcd(lcm, nums[j])) * nums[j];

                if (product == gcd * lcm) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}
