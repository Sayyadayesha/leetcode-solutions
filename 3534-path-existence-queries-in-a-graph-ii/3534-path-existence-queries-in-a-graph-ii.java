import java.util.*;

class Solution {
    static class Pair {
        int val, idx;
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a.val, b.val));

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[arr[i].idx] = i;
        }

        // next[i] = farthest index reachable in one step
        int[] next = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            while (r + 1 < n && arr[r + 1].val - arr[i].val <= maxDiff) {
                r++;
            }
            next[i] = r;
        }

        int LOG = 17;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int a = pos[queries[i][0]];
            int b = pos[queries[i][1]];

            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }

            if (a == b) {
                ans[i] = 0;
                continue;
            }

            // impossible if even moving greedily can't leave current position
            if (next[a] == a) {
                ans[i] = -1;
                continue;
            }

            int cur = a;
            int steps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < b) {
                    cur = up[k][cur];
                    steps += 1 << k;
                }
            }

            cur = next[cur];
            steps++;

            ans[i] = (cur >= b) ? steps : -1;
        }

        return ans;
    }
}