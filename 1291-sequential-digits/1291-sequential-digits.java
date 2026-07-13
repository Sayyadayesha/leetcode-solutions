class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        String digits= "123456789";
        List <Integer> res = new ArrayList<>();
        int lowlen  = String.valueOf(low).length();
        int higlen = String.valueOf(high).length();
        for(int len = lowlen;len<= higlen;len++){
            for(int start = 0; start+len<=9;start++){
                int num = Integer.parseInt(digits.substring(start,start + len));
                if(num>= low && num<= high){
                    res.add(num);
                }
            }
        }
        return res;
    }
}