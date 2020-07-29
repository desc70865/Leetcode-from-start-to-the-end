/* 
You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty sequences of letters you can make.

 

Example 1:

Input: "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
Example 2:

Input: "AAABBC"
Output: 188
 

Note:

1 <= tiles.length <= 7
tiles consists of uppercase English letters.
 */

class Solution {
    public int numTilePossibilities(String tiles) {
        int[] cnt = new int[26];
        int res = factorial(tiles.length());
        for (char c: tiles.toCharArray()) cnt[c - 'a']++;
        for (int n: cnt) res /= factorial(n);
        return res;
    }
    
    private int factorial(int n) {
        int res = 1;
        while (n > 1) res *= n--;
        return res;
    }
}



class Solution {
    private int sum = 0;
    private int[] list;
    
    public int numTilePossibilities(String tiles) {
        int len = tiles.length();
        list = new int[len + 1];
        int[] map = new int[26];
        
        for (int i = 0; i <= len; i++) list[i] = factorial(i);
        for (char c: tiles.toCharArray()) map[c - 'A']++;
        for (int i = 1; i <= len; i++) select(map, 0, i, list[i]);
        
        return sum;
    }
    
    private void select(int[] map,int i,int k,int div) {
        if (k == 0) {
            sum += div;
            return;
        }
        
        for (int j = i; j < 26; j++) {
            if (map[j] > 0) {
                for (int l = 1; l <= map[j] && l <= k; l++) {
                    select(map, j + 1, k - l, div / list[l]);
                }
            }
        }
    }
    
    private int factorial(int n) {
        int res = 1;
        while (n > 1) res *= n--;
        return res;
    }
}



class Solution {
    public int numTilePossibilities(String tiles) {
        int[] counts = new int[26];
        int[] cCount = new int[8];
        
        for (char c: tiles.toCharArray()) counts[c - 'A']++;
        for (int i: counts) cCount[i]++; // !
        
        return numPoss(cCount);
    }
    
    private int numPoss(int[] cCount) {
        int poss = 0;
        
        for (int i = 7; i > 0; i--) {
            if (cCount[i] != 0) { 
                cCount[i]--;
                cCount[i-1]++;
                poss += (1 + numPoss(cCount)) * (1 + cCount[i]);
                cCount[i]++;
                cCount[i-1]--;
            }
        }
        
        return poss;
    }
}