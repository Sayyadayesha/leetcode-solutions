class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
      List<Integer> ans = new ArrayList<>();

        int n = nums.length;
        boolean[] seen = new boolean[n + 1];

        // Mark all numbers that appear
        for (int i = 0; i < nums.length; i++) {
            seen[nums[i]] = true;
        }

        // Find numbers that never appeared
        for (int num = 1; num <= n; num++) {
            if (!seen[num]) {
                ans.add(num);
            }
        }

        return ans;
}
}