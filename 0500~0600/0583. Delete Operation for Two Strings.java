/* 
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
 */

class Solution {
    public int minDistance(String word1, String word2) {
        char[] c1 = word1.toCharArray(), c2 = word2.toCharArray();
        int m = c1.length, n = c2.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int last = 0;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (c1[i - 1] == c2[j - 1]) dp[j] = last + 1;
                else dp[j] = Math.max(dp[j], dp[j - 1]);
                last = temp;
            }
        }
        return m + n - 2 * dp[n];
    }
}