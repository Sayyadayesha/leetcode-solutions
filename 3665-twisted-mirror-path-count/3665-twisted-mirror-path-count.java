class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;
        long[][] R = new long[m][n];
        long[][] D = new long[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    // start: 1 way, can go right or down
                    if (n > 1) R[0][1] = (R[0][1] + 1) % MOD;
                    if (m > 1) D[1][0] = (D[1][0] + 1) % MOD;
                    continue;
                }

                if (grid[i][j] == 0) {
                    long t = (R[i][j] + D[i][j]) % MOD;
                    if (i == m - 1 && j == n - 1) return (int) t;
                    if (j + 1 < n) R[i][j + 1] = (R[i][j + 1] + t) % MOD;
                    if (i + 1 < m) D[i + 1][j] = (D[i + 1][j] + t) % MOD;
                } else {
                    // arrived moving right into mirror -> deflected down
                    if (i + 1 < m) D[i + 1][j] = (D[i + 1][j] + R[i][j]) % MOD;
                    // arrived moving down into mirror -> deflected right
                    if (j + 1 < n) R[i][j + 1] = (R[i][j + 1] + D[i][j]) % MOD;
                }
            }
        }
        return 0;
    }
}