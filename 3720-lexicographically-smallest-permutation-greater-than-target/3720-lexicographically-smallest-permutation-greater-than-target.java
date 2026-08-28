class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int bestPos = -1;
        int bestChar = -1;

        for (int i = 0; i < target.length(); i++) {
            int x = target.charAt(i) - 'a';

            // Check if we can make target[i] slightly larger.
            for (int c = x + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    bestPos = i;
                    bestChar = c;
                    break;
                }
            }

            // We cannot continue matching target.
            if (freq[x] == 0) {
                break;
            }

            // Use target[i] to continue the equal prefix.
            freq[x]--;
        }

        // No permutation is greater than target.
        if (bestPos == -1) {
            return "";
        }

        // Rebuild the character frequencies from scratch.
        freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Use the prefix equal to target.
        for (int i = 0; i < bestPos; i++) {
            freq[target.charAt(i) - 'a']--;
        }

        // Put the smallest character greater than target[bestPos].
        freq[bestChar]--;

        StringBuilder ans = new StringBuilder();

        // Equal prefix.
        ans.append(target, 0, bestPos);

        // Greater character.
        ans.append((char) ('a' + bestChar));

        // Smallest possible suffix.
        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                ans.append((char) ('a' + c));
                freq[c]--;
            }
        }

        return ans.toString();
    }
}