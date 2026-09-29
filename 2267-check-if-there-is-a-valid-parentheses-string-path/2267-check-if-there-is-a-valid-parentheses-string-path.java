class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Path length must be even for a valid parentheses string
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        boolean[][][] dp = new boolean[m][n][m + n];

        // Starting cell
        int startBalance = grid[0][0] == '(' ? 1 : -1;

        if (startBalance < 0) {
            return false;
        }

        dp[0][0][startBalance] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                for (int balance = 0; balance < m + n; balance++) {

                    if (!dp[i][j][balance]) {
                        continue;
                    }

                    // Move down
                    if (i + 1 < m) {

                        int newBalance;

                        if (grid[i + 1][j] == '(') {
                            newBalance = balance + 1;
                        } else {
                            newBalance = balance - 1;
                        }

                        if (newBalance >= 0) {
                            dp[i + 1][j][newBalance] = true;
                        }
                    }

                    // Move right
                    if (j + 1 < n) {

                        int newBalance;

                        if (grid[i][j + 1] == '(') {
                            newBalance = balance + 1;
                        } else {
                            newBalance = balance - 1;
                        }

                        if (newBalance >= 0) {
                            dp[i][j + 1][newBalance] = true;
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}