class Solution {
    public int pivotIndex(int[] nums) {
        int rightsum = 0;
        for(int i =0; i<nums.length;i++){
            rightsum += nums[i];
        }
         int leftsum = 0;
        for(int i =0;i<nums.length;i++){
            if(leftsum == rightsum - nums[i]){
                return i;
            }
            leftsum+= nums[i];
            rightsum -= nums[i];
        }
        return -1;
    }
}