class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // A palindrome can have at most one character
        // with an odd frequency.
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;

        // Characters available for the left half.
        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = count[i] / 2;
        }

        /*
         * Match target's left half as long as possible.
         */
        int matched = 0;
        int[] remaining = half.clone();

        while (matched < halfLen) {
            int x = target.charAt(matched) - 'a';

            if (remaining[x] == 0) {
                break;
            }

            remaining[x]--;
            matched++;
        }

        /*
         * First try to make the answer greater at the first
         * position where matching target failed.
         *
         * Example:
         * s = "bb", target = "aa"
         *
         * matched = 0
         * remaining has 'b'
         * b > a -> answer "bb"
         */
        if (matched < halfLen) {
            int x = target.charAt(matched) - 'a';

            for (int c = x + 1; c < 26; c++) {
                if (remaining[c] > 0) {
                    StringBuilder left = new StringBuilder();

                    // Equal prefix.
                    for (int i = 0; i < matched; i++) {
                        left.append(target.charAt(i));
                    }

                    // Make this position greater.
                    left.append((char) ('a' + c));
                    remaining[c]--;

                    // Smallest suffix.
                    appendSmallest(left, remaining);

                    return buildPalindrome(left, middle);
                }
            }
        }

        /*
         * If the entire left half matched, check the palindrome
         * with exactly the same left half.
         */
        if (matched == halfLen) {
            StringBuilder left = new StringBuilder();

            for (int i = 0; i < halfLen; i++) {
                left.append(target.charAt(i));
            }

            // Odd length: check middle character.
            if (n % 2 == 1) {
                int targetMid = target.charAt(halfLen) - 'a';

                if (middle > targetMid) {
                    return buildPalindrome(left, middle);
                }

                if (middle == targetMid) {
                    String candidate = buildPalindrome(left, middle);

                    if (candidate.compareTo(target) > 0) {
                        return candidate;
                    }
                }
            } else {
                // Even length.
                String candidate = buildPalindrome(left, -1);

                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
            }
        }

        /*
         * We need to backtrack.
         *
         * Find the rightmost position where we can replace
         * target[i] with a slightly larger available character.
         */
        remaining = half.clone();

        for (int i = 0; i < matched; i++) {
            remaining[target.charAt(i) - 'a']--;
        }

        for (int i = matched - 1; i >= 0; i--) {
            int x = target.charAt(i) - 'a';

            // Put target[i] back.
            remaining[x]++;

            // Try the smallest character greater than target[i].
            for (int c = x + 1; c < 26; c++) {
                if (remaining[c] > 0) {
                    StringBuilder left = new StringBuilder();

                    // Same prefix.
                    for (int j = 0; j < i; j++) {
                        left.append(target.charAt(j));
                    }

                    // Increase this position.
                    left.append((char) ('a' + c));
                    remaining[c]--;

                    // Smallest possible suffix.
                    appendSmallest(left, remaining);

                    return buildPalindrome(left, middle);
                }
            }
        }

        return "";
    }

    private void appendSmallest(StringBuilder sb, int[] count) {
        for (int c = 0; c < 26; c++) {
            while (count[c] > 0) {
                sb.append((char) ('a' + c));
                count[c]--;
            }
        }
    }

    private String buildPalindrome(StringBuilder left, int middle) {
        StringBuilder result = new StringBuilder();

        result.append(left);

        if (middle != -1) {
            result.append((char) ('a' + middle));
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            result.append(left.charAt(i));
        }

        return result.toString();
    }
}