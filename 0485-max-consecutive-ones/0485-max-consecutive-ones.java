class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count = 0;
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 0, 1, 1, 1};
        int n = arr.length; // Initialize 'n'
        Solution sol = new Solution();
        System.out.println(sol.findMaxConsecutiveOnes(arr)); // Pass the correct argument
    }
}
