class Solution {

    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        // up[v] = arrays ending at value v
        // whose last move was UP

        long[] up = new long[m + 1];

        // down[v] = arrays ending at value v
        // whose last move was DOWN

        long[] down = new long[m + 1];

        // Base Case: length = 2
        for (int a = 1; a <= m; a++) {
            for (int b = 1; b <= m; b++) {

                if (a < b) {
                    up[b] = (up[b] + 1) % MOD;
                }

                if (a > b) {
                    down[b] = (down[b] + 1) % MOD;
                }
            }
        }

        // Build lengths 3 ... n
        for (int len = 3; len <= n; len++) {

            long[] prefixUp = new long[m + 1];
            long[] prefixDown = new long[m + 1];

            // Prefix sums
            for (int i = 1; i <= m; i++) {
                prefixUp[i] =
                        (prefixUp[i - 1] + up[i]) % MOD;

                prefixDown[i] =
                        (prefixDown[i - 1] + down[i]) % MOD;
            }

            long[] newUp = new long[m + 1];
            long[] newDown = new long[m + 1];

            for (int v = 1; v <= m; v++) {

                // previous value smaller than v
                newUp[v] = prefixDown[v - 1];

                // previous value greater than v
                newDown[v] =
                        (prefixUp[m] - prefixUp[v] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        if (n == 2) {

            for (int i = 1; i <= m; i++) {
                ans = (ans + up[i]) % MOD;
                ans = (ans + down[i]) % MOD;
            }

            return (int) ans;
        }

        for (int i = 1; i <= m; i++) {
            ans = (ans + up[i]) % MOD;
            ans = (ans + down[i]) % MOD;
        }

        return (int) ans;
    }
}