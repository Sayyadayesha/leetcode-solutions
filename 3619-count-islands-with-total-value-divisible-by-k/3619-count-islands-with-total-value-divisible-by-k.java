class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long[] sum = new long[1];
                    dfs(grid, i, j, sum);

                    if (sum[0] % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int i, int j, long[] sum) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return;
        }

        sum[0] += grid[i][j];
        grid[i][j] = 0;

        dfs(grid, i + 1, j, sum);
        dfs(grid, i - 1, j, sum);
        dfs(grid, i, j + 1, sum);
        dfs(grid, i, j - 1, sum);
    }
}