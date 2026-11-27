
class Solution {

    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // Count how many items have each factor
        int[] factorCount = new int[1501];

        for (int[] item : items) {
            factorCount[item[0]]++;
        }

        // freeCount[f] = number of other item types
        // whose factors are divisible by f
        int[] freeCount = new int[1501];

        for (int f = 1; f <= 1500; f++) {
            for (int multiple = f; multiple <= 1500; multiple += f) {
                freeCount[f] += factorCount[multiple];
            }
        }

        // dp[c] = maximum copies using exactly cost c
        int NEG = -1000000000;

        int[] dp = new int[budget + 1];

        for (int c = 1; c <= budget; c++) {
            dp[c] = NEG;
        }

        dp[0] = 0;

        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            // Exclude the current item itself
            int freeItems = freeCount[factor] - 1;

            // First purchase gives:
            // 1 purchased copy + free copies
            int firstValue = 1 + freeItems;

            // buy[c] = best result when current item
            // is purchased at least once
            int[] buy = new int[budget + 1];

            for (int c = 0; c <= budget; c++) {
                buy[c] = NEG;
            }

            for (int c = price; c <= budget; c++) {

                // Buy the first copy of this item
                int firstPurchase = dp[c - price] + firstValue;

                // Buy another copy of the same item
                int extraPurchase = buy[c - price] + 1;

                buy[c] = Math.max(firstPurchase, extraPurchase);
            }

            // Either skip this item type or buy it
            for (int c = 0; c <= budget; c++) {
                dp[c] = Math.max(dp[c], buy[c]);
            }
        }

        // We can spend at most budget
        int answer = 0;

        for (int c = 0; c <= budget; c++) {
            answer = Math.max(answer, dp[c]);
        }

        return answer;
    }
}
