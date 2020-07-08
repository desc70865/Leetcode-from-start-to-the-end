/* 
You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:

Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
Example 2:

Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
 */

class Solution {
    public String getHint(String secret, String guess) {
        int[] d1 = new int[10];
        int[] d2 = new int[10];
        int bulls = 0;
        char[] s = secret.toCharArray();
        char[] g = guess.toCharArray();
        for (int i = 0; i < secret.length(); i++) {
            if (s[i] == g[i]) {
                bulls++;
            } else {
                d1[s[i] - '0']++;
                d2[g[i] - '0']++;
            }
        }
        int cows = 0;
        for (int i = 0; i < 10; i++) {
            if (d1[i] > 0 && d2[i] > 0) {
                cows += Math.min(d1[i], d2[i]);
            }
        }
        
        StringBuilder ret = new StringBuilder(Integer.toString(bulls));
        ret.append('A');
        ret.append(Integer.toString(cows));
        ret.append('B');
        return ret.toString();
    }
}



class Solution {
    public String getHint(String secret, String guess) {
        char[] s = secret.toCharArray();
        char[] g = guess.toCharArray();
        int bulls = 0, cows = 0;
        int[] numbers = new int[10];
        
        for (int i = 0; i < secret.length(); i++) {
            int S = s[i] - '0';
            int G = g[i] - '0';
            if (S == G) {
                bulls++;
            } else {
                if (numbers[S]++ < 0) {
                    cows++;
                }
                if (numbers[G]-- > 0) {
                    cows++;
                }
            }
        }
        
        StringBuilder ret = new StringBuilder(Integer.toString(bulls));
        ret.append('A');
        ret.append(Integer.toString(cows));
        ret.append('B');
        return ret.toString();
    }
}



class Solution {
    public String getHint(String secret, String guess) {
        char[] s = secret.toCharArray();
        char[] g = guess.toCharArray();
        int bulls = 0;
        int[][] index = new int[10][2];
        
        for (int i = 0; i < s.length; i++) {
            if (s[i] == g[i]) {
                bulls++;
            } else {
                index[s[i] - '0'][0]++;
                index[g[i] - '0'][1]++;
            }
        }
        
        int cows = 0;
        for (int j = 0; j < 10; j++) {
            if (index[j][0] > 0 && index[j][1] > 0) {
                cows += Math.min(index[j][0], index[j][1]);
            }
        }
        
        StringBuilder ret = new StringBuilder(Integer.toString(bulls));
        ret.append('A');
        ret.append(Integer.toString(cows));
        ret.append('B');
        return ret.toString();
    }
}