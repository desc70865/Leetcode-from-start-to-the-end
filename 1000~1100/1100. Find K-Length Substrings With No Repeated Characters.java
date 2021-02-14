/* 
Given a string S, return the number of substrings of length K with no repeated characters.

 

Example 1:

Input: S = "havefunonleetcode", K = 5
Output: 6
Explanation: 
There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
Example 2:

Input: S = "home", K = 5
Output: 0
Explanation: 
Notice K can be larger than the length of S. In this case is not possible to find any substring.
 

Note:

1 <= S.length <= 10^4
All characters of S are lowercase English letters.
1 <= K <= 10^4
 */

class Solution {
    public int numKLenSubstrNoRepeats(String S, int K) {
        if (K > 26 || K > S.length()) {
            return 0;
        }
        int[] map = new int[128];
        char[] chs = S.toCharArray();
        int diff = 0;
        for (int i = 0; i < K; i++) {
            if (map[chs[i]]++ == 1) {
                diff++;
            }
        }
        int ans = diff == 0 ? 1 : 0;
        for (int i = K; i < chs.length; i++) {
            if (map[chs[i]]++ == 1) {
                diff++;
            }
            if (map[chs[i - K]]-- == 2) {
                diff--;
            }
            if (diff == 0) {
                ans++;
            }
        }
        return ans;
    }
}