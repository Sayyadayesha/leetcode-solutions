class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];

        // Count frequency of each character
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        StringBuilder mid = new StringBuilder();

        // Build left half and middle character
        for (int i = 0; i < 26; i++) {
            int count = freq[i];

            // Add half of the occurrences to the left half
            for (int j = 0; j < count / 2; j++) {
                left.append((char) ('a' + i));
            }

            // If odd frequency, this is the middle character
            if (count % 2 == 1) {
                mid.append((char) ('a' + i));
            }
        }

        // Right half is reverse of left half
        StringBuilder right = new StringBuilder(left).reverse();

        return left.toString() + mid.toString() + right.toString();
    }
}