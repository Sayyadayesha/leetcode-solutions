class Solution {
    public long sumAndMultiply(int n) {
        long concat = 0;
        long sum = 0;

        String str = String.valueOf(n);

        for (char ch : str.toCharArray()) {
            if (ch != '0') {
                int digit = ch - '0';
                concat = concat * 10 + digit;
                sum += digit;
            }
        }

        return concat * sum;
    }
}