class Solution {
    public int strStr(String haystack, String needle) {

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {

            String part = haystack.substring(i, i + needle.length());

            if (part.equals(needle)) {
                return i;
            }
        }

        return -1;
    }
}