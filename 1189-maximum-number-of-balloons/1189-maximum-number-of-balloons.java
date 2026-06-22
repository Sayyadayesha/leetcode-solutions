class Solution {

    public int maxNumberOfBalloons(String text) {

        // Frequency array for a-z

        int[] freq = new int[26];

        // Count every character

        for (char ch : text.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Find how many balloons can be formed

        int b = freq['b' - 'a'];

        int a = freq['a' - 'a'];

        int l = freq['l' - 'a'] / 2; // need 2 l

        int o = freq['o' - 'a'] / 2; // need 2 o

        int n = freq['n' - 'a'];

        // Minimum among all required chars

        return Math.min(
                Math.min(b, a),
                Math.min(l, Math.min(o, n))
        );
    }
}