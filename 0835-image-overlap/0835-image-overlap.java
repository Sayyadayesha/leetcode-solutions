class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int ans = 0;

        // Try every possible row and column shift
        for (int dr = -(n - 1); dr <= n - 1; dr++) {
            for (int dc = -(n - 1); dc <= n - 1; dc++) {

                int overlap = 0;

                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {

                        int r2 = r + dr;
                        int c2 = c + dc;

                        // Check if translated position is inside img2
                        if (r2 >= 0 && r2 < n &&
                            c2 >= 0 && c2 < n) {

                            if (img1[r][c] == 1 && img2[r2][c2] == 1) {
                                overlap++;
                            }
                        }
                    }
                }

                ans = Math.max(ans, overlap);
            }
        }

        return ans;
    }
}