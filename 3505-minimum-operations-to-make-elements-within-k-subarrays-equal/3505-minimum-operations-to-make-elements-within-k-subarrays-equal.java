import java.util.*;

class Solution {
    static class MedianWindow {
        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        int lowSize = 0, highSize = 0;
        long lowSum = 0, highSum = 0;

        void addLow(int v) {
            low.merge(v, 1, Integer::sum);
            lowSize++;
            lowSum += v;
        }

        void addHigh(int v) {
            high.merge(v, 1, Integer::sum);
            highSize++;
            highSum += v;
        }

        int removeLowMax() {
            int v = low.lastKey();
            removeFrom(low, v);
            lowSize--;
            lowSum -= v;
            return v;
        }

        int removeHighMin() {
            int v = high.firstKey();
            removeFrom(high, v);
            highSize--;
            highSum -= v;
            return v;
        }

        void removeValue(int v) {
            if (low.containsKey(v)) {
                removeFrom(low, v);
                lowSize--;
                lowSum -= v;
            } else {
                removeFrom(high, v);
                highSize--;
                highSum -= v;
            }
            rebalance();
        }

        void add(int v) {
            if (low.isEmpty() || v <= low.lastKey()) {
                addLow(v);
            } else {
                addHigh(v);
            }
            rebalance();
        }

        void rebalance() {
            while (lowSize > highSize + 1) {
                addHigh(removeLowMax());
            }

            while (lowSize < highSize) {
                addLow(removeHighMin());
            }
        }

        void removeFrom(TreeMap<Integer, Integer> map, int v) {
            int count = map.get(v);
            if (count == 1) {
                map.remove(v);
            } else {
                map.put(v, count - 1);
            }
        }

        long cost() {
            long median = low.lastKey();
            return median * lowSize - lowSum
                 + highSum - median * highSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int windows = n - x + 1;
        long[] cost = new long[windows];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        for (int i = 0; i < windows; i++) {
            cost[i] = window.cost();

            if (i + x < n) {
                window.removeValue(nums[i]);
                window.add(nums[i + x]);
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        Arrays.fill(prev, 0L);

        for (int j = 1; j <= k; j++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);

            for (int i = 1; i <= n; i++) {
                cur[i] = cur[i - 1];

                if (i >= x && prev[i - x] != INF) {
                    cur[i] = Math.min(
                        cur[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            prev = cur;
        }

        return prev[n];
    }
}