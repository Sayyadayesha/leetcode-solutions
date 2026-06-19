class Solution {
    public int largestAltitude(int[] gain) {
        int curr = 0;
        int max = 0;
        for(int i = 0;i<= gain.length-1;i++){
            curr = curr + gain[i];
             if (curr > max) {
    max = curr;
}
        }
       
return max;
    }
}