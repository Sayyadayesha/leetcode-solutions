public class Solution {

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int uniqueCount = 0;

        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[uniqueCount]) {
                uniqueCount++;
                nums[uniqueCount] = nums[j];
            }
        }

        return uniqueCount + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};

        System.out.print("Input: nums = [");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i < nums.length - 1) System.out.print(",");
        }
        System.out.println("]");

        int k = removeDuplicates(nums);

        System.out.print("Output: " + k + ", nums = [");
        for (int i = 0; i < nums.length; i++) {
            if (i < k)
                System.out.print(nums[i]);
            else
                System.out.print("_");

            if (i < nums.length - 1) System.out.print(",");
        }
        System.out.println("]");
    }
}
