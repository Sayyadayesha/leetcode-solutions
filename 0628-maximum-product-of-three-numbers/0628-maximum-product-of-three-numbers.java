class Solution {
    public int maximumProduct(int[] nums) {

        // Sabse bade 3 numbers
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        // Sabse chhote 2 numbers
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {

            // Largest 3 update karo
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            }
            else if (num > max2) {
                max3 = max2;
                max2 = num;
            }
            else if (num > max3) {
                max3 = num;
            }

            // Smallest 2 update karo
            if (num < min1) {
                min2 = min1;
                min1 = num;
            }
            else if (num < min2) {
                min2 = num;
            }
        }

        int product1 = max1 * max2 * max3;
        int product2 = min1 * min2 * max1;

        return Math.max(product1, product2);
    }
}