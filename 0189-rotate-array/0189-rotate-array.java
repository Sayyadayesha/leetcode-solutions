class Solution {
    public void rotate(int[] nums, int k) {

        int n = nums.length;

        // Agar k n se bada hai to extra rotations ka koi fayda nahi
        k = k % n;

        // Step 1: Pura array reverse karo
        reverse(nums, 0, n - 1);

        // Step 2: Pehle k elements reverse karo
        reverse(nums, 0, k - 1);

        // Step 3: Baaki ke elements reverse karo
        reverse(nums, k, n - 1);
    }

    // Array reverse karne ka helper function
    public void reverse(int[] nums, int start, int end) {

        while (start < end) {

            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }
}