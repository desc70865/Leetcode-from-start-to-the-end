/* 
You are given a list of strings of the same length words and a string target.

Your task is to form target using the given words under the following rules:

target should be formed from left to right.
To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
Repeat the process until you form the string target.
Notice that you can use multiple characters from the same string in words provided the conditions above are met.

Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: words = ["acca","bbbb","caca"], target = "aba"
Output: 6
Explanation: There are 6 ways to form target.
"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
Example 2:

Input: words = ["abba","baab"], target = "bab"
Output: 4
Explanation: There are 4 ways to form target.
"bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
"bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
"bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
"bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
Example 3:

Input: words = ["abcd"], target = "abcd"
Output: 1
Example 4:

Input: words = ["abab","baba","abba","baab"], target = "abba"
Output: 16
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 1000
All strings in words have the same length.
1 <= target.length <= 1000
words[i] and target contain only lowercase English letters.
 */

class Solution {
    int m, n;
    static final int MOD = 1000000007;
    Long[][] dp;
    public int numWays(String[] words, String target) {
        m = words.length;
        n = words[0].length();
        int[][] map = new int[n][26];
        for (String word: words) {
            for (int i = 0; i < n; i++) {
                map[i][word.charAt(i) - 97]++;
            }
        }
        dp = new Long[n][target.length()];
        return (int) (dfs(map, 0, 0, target.toCharArray()) % MOD);
    }
    
    private long dfs(int[][] map, int col, int idx, char[] target) {
        if (idx == target.length) return 1;
        if (col >= n) return 0;
        if (dp[col][idx] != null) return dp[col][idx];
        int rem = target.length - idx;
        long sum = 0;
        for (int j = col; j <= n - rem; j++) {
            int a = map[j][target[idx] - 97];
            if (a == 0) continue;
            long b = dfs(map, j + 1, idx + 1, target) % MOD;
            sum += b * a;
        }
        return dp[col][idx] = sum;
    }
}



class Solution {
    static final int MOD = 1_000_000_007;
    
    public int numWays(String[] words, String target) {
        int n = words.length;
        int m = words[0].length();
        int t = target.length();
        long[][] dp = new long[t + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            dp[t][i] = 1;
        }
        int[][] map = new int[26][m];
        for (int i = 0; i < n; i++) {
            for (int e = 0; e < m; e++) {
                map[words[i].charAt(e) - 'a'][e]++;
            }
        }
        for (int i = t - 1; i >= 0; i--) {
            int c = target.charAt(i) - 'a';
            for (int x = m - 1; x >= 0; x--) {
                dp[i][x] = dp[i][x + 1];
                dp[i][x] += dp[i + 1][x + 1] * map[c][x];
                dp[i][x] %= MOD;
            }
        }
        return (int) dp[0][0];
    }
}