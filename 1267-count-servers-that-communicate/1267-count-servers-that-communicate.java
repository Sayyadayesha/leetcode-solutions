class Solution {
    public int countServers(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[] colCount = new int[n];

        // Step 1: Count servers in each column
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    colCount[col]++;
                }
            }
        }

        int result = 0;

        // Step 2: Process each row
        for (int row = 0; row < m; row++) {

            int rowCount = 0;
            int singleServerCol = -1;

            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    rowCount++;
                    singleServerCol = col;
                }
            }

            // Case 1: Multiple servers in the same row
            if (rowCount > 1) {
                result += rowCount;
            }

            // Case 2: Exactly one server in the row
            else if (rowCount == 1) {
                if (colCount[singleServerCol] > 1) {
                    result++;
                }
            }
        }

        return result;
    }
}