class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        
 StringBuilder ans = new StringBuilder();

        for(String word : words) {

            int sum = 0;

            for(int i = 0; i < word.length(); i++) {

                char ch = word.charAt(i);

                int index = ch - 'a';

                sum += weights[index];
            }

            int mod = sum % 26;

            char mapped = (char)('z' - mod);

            ans.append(mapped);
        }

        return ans.toString();
        
    }
}