
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];

        // Count frequency of each factor
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Count items whose factors are divisible by each factor
        int[] divisibleCount = new int[n + 1];

        for (int factor = 1; factor <= n; factor++) {
            int count = 0;

            for (int multiple = factor; multiple <= n; multiple += factor) {
                count += freq[multiple];
            }

            divisibleCount[factor] = count;
        }

        // Find minimum price
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            minPrice = Math.min(minPrice, item[1]);
        }

        // Sort items by price
        Arrays.sort(items, (a, b) -> Integer.compare(a[1], b[1]));

        long remainingBudget = budget;
        long bonusBought = 0;

        // A bonus purchase costing >= 2 * minPrice
        // is never better than buying normal copies.
        long limit = 2L * minPrice;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            if (price >= limit) {
                break;
            }

            // Number of eligible free-item types
            int bonusCount = divisibleCount[factor] - 1;

            if (bonusCount <= 0) {
                continue;
            }

            // Buy as many bonus copies as possible
            long affordable = remainingBudget / price;
            long take = Math.min((long) bonusCount, affordable);

            bonusBought += take;
            remainingBudget -= take * price;
        }

        // Each bonus purchase gives:
        // 1 purchased copy + 1 free copy
        long answer = 2L * bonusBought;

        // Spend remaining budget on cheapest normal copies
        answer += remainingBudget / minPrice;

        return (int) answer;
    }
}
