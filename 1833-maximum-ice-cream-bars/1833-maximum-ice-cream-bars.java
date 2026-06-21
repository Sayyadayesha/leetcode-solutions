class Solution {

    public int maxIceCream(int[] costs, int coins) {

        // Sabse pehle maximum cost find karenge
        // Taaki frequency array ka size decide kar sakein

        int maxCost = 0;

        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        // Frequency array banaya
        // freq[i] batayega ki cost i wali ice cream kitni baar aayi hai

        int[] freq = new int[maxCost + 1];

        // Har cost ki frequency count kar lo

        for (int cost : costs) {
            freq[cost]++;
        }

        // Total kitni ice creams khareedi uska count

        int count = 0;

        // Ab smallest cost se start karenge
        

        for (int cost = 1; cost <= maxCost; cost++) {

            // Agar is cost ki koi ice cream hi nahi hai
            // To next cost par chale jao

            if (freq[cost] == 0) {
                continue;
            }

            // Coins se maximum kitni ice creams
            // Is cost wali khareed sakte hain

            int canBuy = coins / cost;

            // Lekin shop mein available quantity bhi dekhni hai
            // Isliye minimum lenge

            int buy = Math.min(canBuy, freq[cost]);

            // Answer update

            count += buy;

            // Coins kam kar do

            coins -= buy * cost;

            // Agar coins khatam ho gaye
            // To aur kuch nahi khareed sakte

            if (coins == 0) {
                break;
            }
        }

        return count;
    }
}