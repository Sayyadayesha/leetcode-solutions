class Solution {

    static class State {
        long score;
        int[] ids;

        State(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> {
            int x = intervals.get(a).get(0);
            int y = intervals.get(b).get(0);

            if (x != y) {
                return Integer.compare(x, y);
            }

            return Integer.compare(a, b);
        });

        long[] start = new long[n];
        long[] end = new long[n];
        long[] weight = new long[n];
        int[] original = new int[n];

        for (int i = 0; i < n; i++) {

            int id = order[i];

            start[i] = intervals.get(id).get(0);
            end[i] = intervals.get(id).get(1);
            weight[i] = intervals.get(id).get(2);
            original[i] = id;
        }

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {

            int lo = i + 1;
            int hi = n;

            while (lo < hi) {

                int mid = lo + (hi - lo) / 2;

                if (start[mid] > end[i]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            next[i] = lo;
        }

        State[][] dp = new State[n + 1][5];

        // IMPORTANT: initialize k = 0 for every i
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(0, new int[0]);
        }

        // At the end, choosing up to any number of intervals gives 0
        for (int k = 1; k <= 4; k++) {
            dp[n][k] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                State skip = dp[i + 1][k];

                State nextState = dp[next[i]][k - 1];

                int[] takeIds = addAndSort(nextState.ids, original[i]);

                State take = new State(
                    weight[i] + nextState.score,
                    takeIds
                );

                if (take.score > skip.score) {

                    dp[i][k] = take;

                } else if (take.score < skip.score) {

                    dp[i][k] = skip;

                } else {

                    if (compare(take.ids, skip.ids) < 0) {
                        dp[i][k] = take;
                    } else {
                        dp[i][k] = skip;
                    }
                }
            }
        }

        return dp[0][4].ids;
    }

    static int[] addAndSort(int[] arr, int x) {

        int[] result = Arrays.copyOf(arr, arr.length + 1);

        result[arr.length] = x;

        Arrays.sort(result);

        return result;
    }

    static int compare(int[] a, int[] b) {

        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {

            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(a.length, b.length);
    }
}