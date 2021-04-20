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
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[] dp = new int[len];
        for (int l = len - 1; l >= 0; l--) {
            dp[l] = 1;
            for (int r = l + 1, max = 0; r < len; r++) {
                int cur = dp[r];
                if (chs[l] == chs[r]) {
                    dp[r] = max + 2;
                }
                max = Math.max(cur, max);
            }
        }
        int ans = 0;
        for (int e: dp) ans = Math.max(ans, e);
        // System.out.println(Arrays.toString(dp));
        return ans;
    }
}

// ith dp[j] -> dp[i][j]
// max: 维护 dp[i + 1][k] k ∈ [i + 1, j - 1] 中的最大值
// chs[i] == chs[j] dp[j] = max + 2;

class Solution {
    public int longestPalindromeSubseq(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[][] dp = new int[len][len];
        for (int l = len - 1; l >= 0; l--) {
            dp[l][l] = 1;
            for (int r = l + 1; r < len; r++) {
                if (chs[l] == chs[r]) {
                    dp[l][r] = dp[l + 1][r - 1] + 2;
                } else {
                    dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
                }
            }
        }
        return dp[0][len - 1];
    }
}

// dp[i][j] longestPalindromeSubseq in [i, j]

class Solution {
    public int longestPalindromeSubseq(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[] dp = new int[len];
        for (int l = len - 1; l >= 0; l--) {
            dp[l] = 1;
            for (int r = l + 1, prev = 0; r < len; r++) {
                int cur = dp[r];
                dp[r] = chs[l] == chs[r] ? prev + 2 : Math.max(dp[r], dp[r - 1]);
                prev = cur;
            }
        }
        return dp[len - 1];
    }
}