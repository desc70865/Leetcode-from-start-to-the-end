/* 
In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:

Each group has exactly X cards.
All the cards in each group have the same integer.
 

Example 1:

Input: deck = [1,2,3,4,4,3,2,1]
Output: true
Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
Example 2:

Input: deck = [1,1,1,2,2,2,3,3]
Output: false´
Explanation: No possible partition.
Example 3:

Input: deck = [1]
Output: false
Explanation: No possible partition.
Example 4:

Input: deck = [1,1]
Output: true
Explanation: Possible partition [1,1].
Example 5:

Input: deck = [1,1,2,2,2,2]
Output: true
Explanation: Possible partition [1,1],[2,2],[2,2].
 

Constraints:

1 <= deck.length <= 10^4
0 <= deck[i] < 10^4
 */

class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length < 2) return false;
        for (int num: deck) map.put(num, map.getOrDefault(num, 0) + 1);
        int len = map.size(), p = 0;
        int[] cnt = new int[len];
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            cnt[p++] = entry.getValue();
        }
        int k = cnt[0];
        for (int i = 1; i < len; i++) {
            k = gcd(k, cnt[i]);
        }
        return k >= 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}



class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        int[] cnt = new int[10000];
        for (int c: deck) cnt[c]++;
        int g = -1;
        for (int i = 0; i < 10000; ++i) {
            if (cnt[i] ==  0) continue;
            if (g < 0) g = cnt[i];
            else g = gcd(g, cnt[i]);
        }
        return g >= 2;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}



class Solution {
    private static final int LEN = 53;
    public boolean hasGroupsSizeX(int[] deck) {
        int[] cnt = new int[LEN];
        for (int c: deck) cnt[c]++;
        int g = 0;
        for (int i = 0; i < LEN && g != 1; ++i) g = gcd(g, cnt[i]);
        return g != 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}