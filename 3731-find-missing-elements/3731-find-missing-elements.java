import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            if (!set.contains(i)) {
                ans.add(i);
            }
        }

        return ans;   // <-- Missing line
    }
}