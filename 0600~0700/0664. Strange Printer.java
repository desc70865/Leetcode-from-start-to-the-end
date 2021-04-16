/* 
There is a strange printer with the following two special requirements:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

Example 1:
Input: "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:
Input: "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
Hint: Length of the given string will not exceed 100.
 */

class Solution {
    int[][] dp;

    public int strangePrinter(String s) {
        int len = s.length();
        dp = new int[len][len];
        return dfs(s.toCharArray(), 0, len - 1);
    }

    public int dfs(char[] chs, int l, int r) {
        if (l > r) return 0;
        if (dp[l][r] > 0) return dp[l][r];
        int ans = dfs(chs, l + 1, r) + 1;
        for (int m = l + 1; m <= r; m++) {
            if (chs[m] == chs[l]) {
                ans = Math.min(ans, dfs(chs, l, m - 1) + dfs(chs, m + 1, r));
            }
        }
        dp[l][r] = ans;
        return dp[l][r];
    }
}



class Solution {
    public int strangePrinter(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        if (len == 0) return 0;
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int m = 2; m <= len; m++) {
            for (int i = 0; i + m - 1 < len; i++) {
                int j = i + m - 1;
                dp[i][j] = dp[i + 1][j] + 1;
                for (int k = i + 1; k <= j; k++) {
                    if (chs[i] == chs[k]) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k - 1] + dp[k + 1][j]);
                    }
                }
            }
        }
        return dp[0][len - 1];
    }
}



// @param size: amount of different character intervals

class Solution {
    public int strangePrinter(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        if (len <= 1) {
            return len;
        }
        int[] cur = new int[26];
        int[] next = new int[len];
        Arrays.fill(next, -1);
        Arrays.fill(cur, -1);
        cur[chs[0] - 'a'] = 0;
        int size = 1;
        for (int i = 1; i < len; i++) {
            if (chs[i - 1] == chs[i]) continue;
            int idx = chs[i] - 'a';
            if (cur[idx] != -1) {
                next[cur[idx]] = size;
            }
            cur[idx] = size++;
        }
        // 将 chs[] 中的信息压缩至 next[] 中
        int[][] dp = new int[size][size];
        for (int l = size - 1; l >= 0; l--) {
            Arrays.fill(dp[l], Integer.MAX_VALUE);
            for (int m = l; m < size; m++) {
                if (m == l) {
                    dp[l][m] = 1;
                } else {
                    dp[l][m] = Math.min(dp[l][m], dp[l][m - 1] + 1);
                }
                for (int r = next[m]; r != -1; r = next[r]) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][m] + dp[m + 1][r - 1]);
                }
            }
        }
        return dp[0][size - 1];
    }
}