class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        /*
         * dp[i] = number of characters from the END of word2
         * that can be matched as a subsequence in word1[i...n-1].
         */
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        boolean usedMismatch = false;

        while (i < n && j < m) {

            // Case 1: exact match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
                i++;
            }

            // Case 2: use our one allowed mismatch
            else if (!usedMismatch &&
                     dp[i + 1] >= m - j - 1) {

                ans[j] = i;
                j++;
                i++;
                usedMismatch = true;
            }

            // Otherwise skip word1[i]
            else {
                i++;
            }
        }

        // Couldn't construct the whole sequence
        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}