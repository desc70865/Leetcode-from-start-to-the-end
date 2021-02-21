/* 
Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
 

Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
 

Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
 */

// 转移状态 charAt i == j
// 状态转移 tmp += 2

    /**
    * Converts this string to a new character array.
    *
    * @return  a newly allocated character array whose length is the length
    *          of this string and whose contents are initialized to contain
    *          the character sequence represented by this string.
    */
    public char[] toCharArray() {
        // Cannot use Arrays.copyOf because of class initialization order issues
        char result[] = new char[value.length];
        System.arraycopy(value, 0, result, 0, value.length);
        return result;
    }

// compare charAt with toCharArray
// 单次遍历
// 多次随机访问

// attention
// subsequence is not subarray

class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null) return 0;
        int len = s.length();
        char[] chs = s.toCharArray();
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = len - 1; i >= 0; i--) {
            char cur = chs[i];
            int curMax = 0;
            for (int j = i + 1; j < len; j++) {
                int mem = dp[j];
                if (cur == chs[j]) {
                    dp[j] = curMax + 2;
                }
                curMax = Math.max(mem, curMax);
            }
        }
        for (int e: dp) max = Math.max(max, e);
        // System.out.println(Arrays.toString(dp));
        return max;
    }
}

// ith dp[j] -> dp[i][j]
// curMax: 维护 dp[i+1][k] k ∈ [i+1, j-1] 中的最大值
// chs[i] == chs[j] dp[j] = curMax + 2;

class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null) {
            return 0;
        }
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (chs[i] == chs[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}

// dp[i][j] longestPalindromeSubseq in [i, j]