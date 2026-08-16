class Solution {
    public boolean stoneGameIX(int[] stones) {
        int c0 = 0, c1 = 0, c2 = 0;

        for (int stone : stones) {
            if (stone % 3 == 0) {
                c0++;
            } else if (stone % 3 == 1) {
                c1++;
            } else {
                c2++;
            }
        }

        // If there are an even number of 0-mod-3 stones,
        // they can be paired safely.
        if (c0 % 2 == 0) {
            return c1 > 0 && c2 > 0;
        }

        // If c0 is odd, Alice needs one side to have
        // at least two more stones than the other.
        return Math.abs(c1 - c2) > 2;
    }
}