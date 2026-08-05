class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        
        int[] res = nums.clone();
        Arrays.sort(res);
        Map<Integer,Integer> idx = new HashMap<>();
        for(int i = 0;i<res.length;i++){
            idx.putIfAbsent(res[i],i);

        }
        for(int i = 0; i<nums.length;i++){
            res[i] = idx.get(nums[i]);
        }
        return res;
    }
}