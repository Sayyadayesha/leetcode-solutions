class Solution {
    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        // First column ko alag se handle karne ke liye
        int col0 = 1;

        // Step 1: Rows aur columns ko mark karo
        for (int i = 0; i < m; i++) {

            // Agar first column me 0 hai
            if (matrix[i][0] == 0) {
                col0 = 0;
            }

            for (int j = 1; j < n; j++) {

                if (matrix[i][j] == 0) {

                    // Row mark
                    matrix[i][0] = 0;

                    // Column mark
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 2: Marked rows aur columns ko 0 bana do
        for (int i = 1; i < m; i++) {

            for (int j = 1; j < n; j++) {

                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 3: First row ko handle karo
        if (matrix[0][0] == 0) {

            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 4: First column ko handle karo
        if (col0 == 0) {

            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}