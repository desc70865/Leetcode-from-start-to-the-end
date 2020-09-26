/* 
Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.

Example 1:

Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 

Note:

S string length is in [1, 10000].
C is a single character, and guaranteed to be in string S.
All letters in S and C are lowercase.
 */

class Solution {
    public int[] shortestToChar(String S, char C) {
        int N = S.length();
        int[] res = new int[N];
        char[] str = S.toCharArray();
        if (str[0] != C) res[0] = 10000;
        for (int i = 1; i < N; i++) {
            if (str[i] == C) res[i] = 0;
            else res[i] = res[i - 1] + 1;
        }
        for (int i = N - 2; i >= 0; i--) {
            res[i] = Math.min(res[i], res[i + 1] + 1);
        }
        return res;
    }
}